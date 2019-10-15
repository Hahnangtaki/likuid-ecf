import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IInvestorMySuffix } from 'app/shared/model/investor-my-suffix.model';
import { getEntities as getInvestors } from 'app/entities/investor-my-suffix/investor-my-suffix.reducer';
import { ICampaignMySuffix } from 'app/shared/model/campaign-my-suffix.model';
import { getEntities as getCampaigns } from 'app/entities/campaign-my-suffix/campaign-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './campaign-transaction-my-suffix.reducer';
import { ICampaignTransactionMySuffix } from 'app/shared/model/campaign-transaction-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICampaignTransactionMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICampaignTransactionMySuffixUpdateState {
  isNew: boolean;
  investorId: string;
  campaignId: string;
}

export class CampaignTransactionMySuffixUpdate extends React.Component<
  ICampaignTransactionMySuffixUpdateProps,
  ICampaignTransactionMySuffixUpdateState
> {
  constructor(props) {
    super(props);
    this.state = {
      investorId: '0',
      campaignId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getInvestors();
    this.props.getCampaigns();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { campaignTransactionEntity } = this.props;
      const entity = {
        ...campaignTransactionEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/campaign-transaction-my-suffix');
  };

  render() {
    const { campaignTransactionEntity, investors, campaigns, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.campaignTransaction.home.createOrEditLabel">Create or edit a CampaignTransaction</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : campaignTransactionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="campaign-transaction-my-suffix-id">ID</Label>
                    <AvInput id="campaign-transaction-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="transCodeLabel" for="campaign-transaction-my-suffix-transCode">
                    Trans Code
                  </Label>
                  <AvField
                    id="campaign-transaction-my-suffix-transCode"
                    type="text"
                    name="transCode"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="transDescLabel" for="campaign-transaction-my-suffix-transDesc">
                    Trans Desc
                  </Label>
                  <AvField
                    id="campaign-transaction-my-suffix-transDesc"
                    type="text"
                    name="transDesc"
                    validate={{
                      maxLength: { value: 200, errorMessage: 'This field cannot be longer than 200 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="transDateLabel" for="campaign-transaction-my-suffix-transDate">
                    Trans Date
                  </Label>
                  <AvField id="campaign-transaction-my-suffix-transDate" type="date" className="form-control" name="transDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="transTypeLabel" for="campaign-transaction-my-suffix-transType">
                    Trans Type
                  </Label>
                  <AvField
                    id="campaign-transaction-my-suffix-transType"
                    type="text"
                    name="transType"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="qtyLabel" for="campaign-transaction-my-suffix-qty">
                    Qty
                  </Label>
                  <AvField id="campaign-transaction-my-suffix-qty" type="string" className="form-control" name="qty" />
                </AvGroup>
                <AvGroup>
                  <Label id="amountLabel" for="campaign-transaction-my-suffix-amount">
                    Amount
                  </Label>
                  <AvField id="campaign-transaction-my-suffix-amount" type="string" className="form-control" name="amount" />
                </AvGroup>
                <AvGroup>
                  <Label id="uniqueChargesLabel" for="campaign-transaction-my-suffix-uniqueCharges">
                    Unique Charges
                  </Label>
                  <AvField id="campaign-transaction-my-suffix-uniqueCharges" type="string" className="form-control" name="uniqueCharges" />
                </AvGroup>
                <AvGroup>
                  <Label id="netAmountLabel" for="campaign-transaction-my-suffix-netAmount">
                    Net Amount
                  </Label>
                  <AvField id="campaign-transaction-my-suffix-netAmount" type="string" className="form-control" name="netAmount" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="campaign-transaction-my-suffix-status">
                    Status
                  </Label>
                  <AvField
                    id="campaign-transaction-my-suffix-status"
                    type="text"
                    name="status"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="campaign-transaction-my-suffix-investor">Investor</Label>
                  <AvInput id="campaign-transaction-my-suffix-investor" type="select" className="form-control" name="investorId">
                    <option value="" key="0" />
                    {investors
                      ? investors.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="campaign-transaction-my-suffix-campaign">Campaign</Label>
                  <AvInput id="campaign-transaction-my-suffix-campaign" type="select" className="form-control" name="campaignId">
                    <option value="" key="0" />
                    {campaigns
                      ? campaigns.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/campaign-transaction-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  investors: storeState.investor.entities,
  campaigns: storeState.campaign.entities,
  campaignTransactionEntity: storeState.campaignTransaction.entity,
  loading: storeState.campaignTransaction.loading,
  updating: storeState.campaignTransaction.updating,
  updateSuccess: storeState.campaignTransaction.updateSuccess
});

const mapDispatchToProps = {
  getInvestors,
  getCampaigns,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignTransactionMySuffixUpdate);
