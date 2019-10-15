import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './campaign-investor-my-suffix.reducer';
import { ICampaignInvestorMySuffix } from 'app/shared/model/campaign-investor-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICampaignInvestorMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class CampaignInvestorMySuffix extends React.Component<ICampaignInvestorMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { campaignInvestorList, match } = this.props;
    return (
      <div>
        <h2 id="campaign-investor-my-suffix-heading">
          Campaign Investors
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Campaign Investor
          </Link>
        </h2>
        <div className="table-responsive">
          {campaignInvestorList && campaignInvestorList.length > 0 ? (
            <Table responsive aria-describedby="campaign-investor-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Qty</th>
                  <th>Fund Amount</th>
                  <th>Investor</th>
                  <th>Campaign</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {campaignInvestorList.map((campaignInvestor, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${campaignInvestor.id}`} color="link" size="sm">
                        {campaignInvestor.id}
                      </Button>
                    </td>
                    <td>{campaignInvestor.qty}</td>
                    <td>{campaignInvestor.fundAmount}</td>
                    <td>
                      {campaignInvestor.investorId ? (
                        <Link to={`investor-my-suffix/${campaignInvestor.investorId}`}>{campaignInvestor.investorId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {campaignInvestor.campaignId ? (
                        <Link to={`campaign-my-suffix/${campaignInvestor.campaignId}`}>{campaignInvestor.campaignId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${campaignInvestor.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${campaignInvestor.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${campaignInvestor.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Campaign Investors found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ campaignInvestor }: IRootState) => ({
  campaignInvestorList: campaignInvestor.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignInvestorMySuffix);
