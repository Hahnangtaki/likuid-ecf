import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './campaign-payment-my-suffix.reducer';
import { ICampaignPaymentMySuffix } from 'app/shared/model/campaign-payment-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICampaignPaymentMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CampaignPaymentMySuffixDetail extends React.Component<ICampaignPaymentMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { campaignPaymentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            CampaignPayment [<b>{campaignPaymentEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="paymentCode">Payment Code</span>
            </dt>
            <dd>{campaignPaymentEntity.paymentCode}</dd>
            <dt>
              <span id="paymentDesc">Payment Desc</span>
            </dt>
            <dd>{campaignPaymentEntity.paymentDesc}</dd>
            <dt>
              <span id="amount">Amount</span>
            </dt>
            <dd>{campaignPaymentEntity.amount}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{campaignPaymentEntity.status}</dd>
            <dt>Fund Raiser Bank</dt>
            <dd>{campaignPaymentEntity.fundRaiserBankId ? campaignPaymentEntity.fundRaiserBankId : ''}</dd>
            <dt>Campaign</dt>
            <dd>{campaignPaymentEntity.campaignId ? campaignPaymentEntity.campaignId : ''}</dd>
            <dt>Company Bank</dt>
            <dd>{campaignPaymentEntity.companyBankId ? campaignPaymentEntity.companyBankId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/campaign-payment-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/campaign-payment-my-suffix/${campaignPaymentEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ campaignPayment }: IRootState) => ({
  campaignPaymentEntity: campaignPayment.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignPaymentMySuffixDetail);
