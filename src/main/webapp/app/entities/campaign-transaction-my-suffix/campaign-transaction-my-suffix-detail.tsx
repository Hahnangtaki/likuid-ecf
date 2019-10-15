import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './campaign-transaction-my-suffix.reducer';
import { ICampaignTransactionMySuffix } from 'app/shared/model/campaign-transaction-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICampaignTransactionMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CampaignTransactionMySuffixDetail extends React.Component<ICampaignTransactionMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { campaignTransactionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            CampaignTransaction [<b>{campaignTransactionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="transCode">Trans Code</span>
            </dt>
            <dd>{campaignTransactionEntity.transCode}</dd>
            <dt>
              <span id="transDesc">Trans Desc</span>
            </dt>
            <dd>{campaignTransactionEntity.transDesc}</dd>
            <dt>
              <span id="transDate">Trans Date</span>
            </dt>
            <dd>
              <TextFormat value={campaignTransactionEntity.transDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="transType">Trans Type</span>
            </dt>
            <dd>{campaignTransactionEntity.transType}</dd>
            <dt>
              <span id="qty">Qty</span>
            </dt>
            <dd>{campaignTransactionEntity.qty}</dd>
            <dt>
              <span id="amount">Amount</span>
            </dt>
            <dd>{campaignTransactionEntity.amount}</dd>
            <dt>
              <span id="uniqueCharges">Unique Charges</span>
            </dt>
            <dd>{campaignTransactionEntity.uniqueCharges}</dd>
            <dt>
              <span id="netAmount">Net Amount</span>
            </dt>
            <dd>{campaignTransactionEntity.netAmount}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{campaignTransactionEntity.status}</dd>
            <dt>Investor</dt>
            <dd>{campaignTransactionEntity.investorId ? campaignTransactionEntity.investorId : ''}</dd>
            <dt>Campaign</dt>
            <dd>{campaignTransactionEntity.campaignId ? campaignTransactionEntity.campaignId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/campaign-transaction-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/campaign-transaction-my-suffix/${campaignTransactionEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ campaignTransaction }: IRootState) => ({
  campaignTransactionEntity: campaignTransaction.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignTransactionMySuffixDetail);
