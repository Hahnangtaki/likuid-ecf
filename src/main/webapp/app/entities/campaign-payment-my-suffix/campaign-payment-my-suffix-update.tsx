import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFundRaiserBankMySuffix } from 'app/shared/model/fund-raiser-bank-my-suffix.model';
import { getEntities as getFundRaiserBanks } from 'app/entities/fund-raiser-bank-my-suffix/fund-raiser-bank-my-suffix.reducer';
import { ICampaignMySuffix } from 'app/shared/model/campaign-my-suffix.model';
import { getEntities as getCampaigns } from 'app/entities/campaign-my-suffix/campaign-my-suffix.reducer';
import { ICompanyBankMySuffix } from 'app/shared/model/company-bank-my-suffix.model';
import { getEntities as getCompanyBanks } from 'app/entities/company-bank-my-suffix/company-bank-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './campaign-payment-my-suffix.reducer';
import { ICampaignPaymentMySuffix } from 'app/shared/model/campaign-payment-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICampaignPaymentMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICampaignPaymentMySuffixUpdateState {
  isNew: boolean;
  fundRaiserBankId: string;
  campaignId: string;
  companyBankId: string;
}

export class CampaignPaymentMySuffixUpdate extends React.Component<
  ICampaignPaymentMySuffixUpdateProps,
  ICampaignPaymentMySuffixUpdateState
> {
  constructor(props) {
    super(props);
    this.state = {
      fundRaiserBankId: '0',
      campaignId: '0',
      companyBankId: '0',
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

    this.props.getFundRaiserBanks();
    this.props.getCampaigns();
    this.props.getCompanyBanks();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { campaignPaymentEntity } = this.props;
      const entity = {
        ...campaignPaymentEntity,
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
    this.props.history.push('/entity/campaign-payment-my-suffix');
  };

  render() {
    const { campaignPaymentEntity, fundRaiserBanks, campaigns, companyBanks, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.campaignPayment.home.createOrEditLabel">Create or edit a CampaignPayment</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : campaignPaymentEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="campaign-payment-my-suffix-id">ID</Label>
                    <AvInput id="campaign-payment-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="paymentCodeLabel" for="campaign-payment-my-suffix-paymentCode">
                    Payment Code
                  </Label>
                  <AvField
                    id="campaign-payment-my-suffix-paymentCode"
                    type="text"
                    name="paymentCode"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="paymentDescLabel" for="campaign-payment-my-suffix-paymentDesc">
                    Payment Desc
                  </Label>
                  <AvField
                    id="campaign-payment-my-suffix-paymentDesc"
                    type="text"
                    name="paymentDesc"
                    validate={{
                      maxLength: { value: 200, errorMessage: 'This field cannot be longer than 200 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="amountLabel" for="campaign-payment-my-suffix-amount">
                    Amount
                  </Label>
                  <AvField id="campaign-payment-my-suffix-amount" type="string" className="form-control" name="amount" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="campaign-payment-my-suffix-status">
                    Status
                  </Label>
                  <AvField
                    id="campaign-payment-my-suffix-status"
                    type="text"
                    name="status"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="campaign-payment-my-suffix-fundRaiserBank">Fund Raiser Bank</Label>
                  <AvInput id="campaign-payment-my-suffix-fundRaiserBank" type="select" className="form-control" name="fundRaiserBankId">
                    <option value="" key="0" />
                    {fundRaiserBanks
                      ? fundRaiserBanks.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="campaign-payment-my-suffix-campaign">Campaign</Label>
                  <AvInput id="campaign-payment-my-suffix-campaign" type="select" className="form-control" name="campaignId">
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
                <AvGroup>
                  <Label for="campaign-payment-my-suffix-companyBank">Company Bank</Label>
                  <AvInput id="campaign-payment-my-suffix-companyBank" type="select" className="form-control" name="companyBankId">
                    <option value="" key="0" />
                    {companyBanks
                      ? companyBanks.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/campaign-payment-my-suffix" replace color="info">
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
  fundRaiserBanks: storeState.fundRaiserBank.entities,
  campaigns: storeState.campaign.entities,
  companyBanks: storeState.companyBank.entities,
  campaignPaymentEntity: storeState.campaignPayment.entity,
  loading: storeState.campaignPayment.loading,
  updating: storeState.campaignPayment.updating,
  updateSuccess: storeState.campaignPayment.updateSuccess
});

const mapDispatchToProps = {
  getFundRaiserBanks,
  getCampaigns,
  getCompanyBanks,
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
)(CampaignPaymentMySuffixUpdate);
