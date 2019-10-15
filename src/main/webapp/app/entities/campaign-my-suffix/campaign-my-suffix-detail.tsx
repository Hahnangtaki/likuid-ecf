import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './campaign-my-suffix.reducer';
import { ICampaignMySuffix } from 'app/shared/model/campaign-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICampaignMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CampaignMySuffixDetail extends React.Component<ICampaignMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { campaignEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Campaign [<b>{campaignEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="campaignCode">Campaign Code</span>
            </dt>
            <dd>{campaignEntity.campaignCode}</dd>
            <dt>
              <span id="campaignName">Campaign Name</span>
            </dt>
            <dd>{campaignEntity.campaignName}</dd>
            <dt>
              <span id="campaignDesc">Campaign Desc</span>
            </dt>
            <dd>{campaignEntity.campaignDesc}</dd>
            <dt>
              <span id="investmentType">Investment Type</span>
            </dt>
            <dd>{campaignEntity.investmentType}</dd>
            <dt>
              <span id="offerDate">Offer Date</span>
            </dt>
            <dd>
              <TextFormat value={campaignEntity.offerDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="deadlineDate">Deadline Date</span>
            </dt>
            <dd>
              <TextFormat value={campaignEntity.deadlineDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="price">Price</span>
            </dt>
            <dd>{campaignEntity.price}</dd>
            <dt>
              <span id="fundTarget">Fund Target</span>
            </dt>
            <dd>{campaignEntity.fundTarget}</dd>
            <dt>
              <span id="fundRaised">Fund Raised</span>
            </dt>
            <dd>{campaignEntity.fundRaised}</dd>
            <dt>
              <span id="fundPaid">Fund Paid</span>
            </dt>
            <dd>{campaignEntity.fundPaid}</dd>
            <dt>
              <span id="qtyTarget">Qty Target</span>
            </dt>
            <dd>{campaignEntity.qtyTarget}</dd>
            <dt>
              <span id="qtyRaised">Qty Raised</span>
            </dt>
            <dd>{campaignEntity.qtyRaised}</dd>
            <dt>
              <span id="estRoiMin">Est Roi Min</span>
            </dt>
            <dd>{campaignEntity.estRoiMin}</dd>
            <dt>
              <span id="estRoiMax">Est Roi Max</span>
            </dt>
            <dd>{campaignEntity.estRoiMax}</dd>
            <dt>
              <span id="prospectusFile">Prospectus File</span>
            </dt>
            <dd>
              {campaignEntity.prospectusFile ? (
                <div>
                  <a onClick={openFile(campaignEntity.prospectusFileContentType, campaignEntity.prospectusFile)}>Open&nbsp;</a>
                  <span>
                    {campaignEntity.prospectusFileContentType}, {byteSize(campaignEntity.prospectusFile)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{campaignEntity.status}</dd>
            <dt>Currency</dt>
            <dd>{campaignEntity.currencyId ? campaignEntity.currencyId : ''}</dd>
            <dt>Fund Raiser</dt>
            <dd>{campaignEntity.fundRaiserId ? campaignEntity.fundRaiserId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/campaign-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/campaign-my-suffix/${campaignEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ campaign }: IRootState) => ({
  campaignEntity: campaign.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignMySuffixDetail);
