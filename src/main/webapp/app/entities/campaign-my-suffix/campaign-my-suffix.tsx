import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './campaign-my-suffix.reducer';
import { ICampaignMySuffix } from 'app/shared/model/campaign-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICampaignMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class CampaignMySuffix extends React.Component<ICampaignMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { campaignList, match } = this.props;
    return (
      <div>
        <h2 id="campaign-my-suffix-heading">
          Campaigns
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Campaign
          </Link>
        </h2>
        <div className="table-responsive">
          {campaignList && campaignList.length > 0 ? (
            <Table responsive aria-describedby="campaign-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Campaign Code</th>
                  <th>Campaign Name</th>
                  <th>Campaign Desc</th>
                  <th>Investment Type</th>
                  <th>Offer Date</th>
                  <th>Deadline Date</th>
                  <th>Price</th>
                  <th>Fund Target</th>
                  <th>Fund Raised</th>
                  <th>Fund Paid</th>
                  <th>Qty Target</th>
                  <th>Qty Raised</th>
                  <th>Est Roi Min</th>
                  <th>Est Roi Max</th>
                  <th>Prospectus File</th>
                  <th>Status</th>
                  <th>Currency</th>
                  <th>Fund Raiser</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {campaignList.map((campaign, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${campaign.id}`} color="link" size="sm">
                        {campaign.id}
                      </Button>
                    </td>
                    <td>{campaign.campaignCode}</td>
                    <td>{campaign.campaignName}</td>
                    <td>{campaign.campaignDesc}</td>
                    <td>{campaign.investmentType}</td>
                    <td>
                      <TextFormat type="date" value={campaign.offerDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={campaign.deadlineDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{campaign.price}</td>
                    <td>{campaign.fundTarget}</td>
                    <td>{campaign.fundRaised}</td>
                    <td>{campaign.fundPaid}</td>
                    <td>{campaign.qtyTarget}</td>
                    <td>{campaign.qtyRaised}</td>
                    <td>{campaign.estRoiMin}</td>
                    <td>{campaign.estRoiMax}</td>
                    <td>
                      {campaign.prospectusFile ? (
                        <div>
                          <a onClick={openFile(campaign.prospectusFileContentType, campaign.prospectusFile)}>Open &nbsp;</a>
                          <span>
                            {campaign.prospectusFileContentType}, {byteSize(campaign.prospectusFile)}
                          </span>
                        </div>
                      ) : null}
                    </td>
                    <td>{campaign.status}</td>
                    <td>
                      {campaign.currencyId ? <Link to={`currency-my-suffix/${campaign.currencyId}`}>{campaign.currencyId}</Link> : ''}
                    </td>
                    <td>
                      {campaign.fundRaiserId ? (
                        <Link to={`fund-raiser-my-suffix/${campaign.fundRaiserId}`}>{campaign.fundRaiserId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${campaign.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${campaign.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${campaign.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Campaigns found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ campaign }: IRootState) => ({
  campaignList: campaign.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignMySuffix);
