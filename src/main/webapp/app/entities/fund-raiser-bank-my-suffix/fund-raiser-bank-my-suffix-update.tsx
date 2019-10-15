import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFundRaiserMySuffix } from 'app/shared/model/fund-raiser-my-suffix.model';
import { getEntities as getFundRaisers } from 'app/entities/fund-raiser-my-suffix/fund-raiser-my-suffix.reducer';
import { IBankMySuffix } from 'app/shared/model/bank-my-suffix.model';
import { getEntities as getBanks } from 'app/entities/bank-my-suffix/bank-my-suffix.reducer';
import { ICurrencyMySuffix } from 'app/shared/model/currency-my-suffix.model';
import { getEntities as getCurrencies } from 'app/entities/currency-my-suffix/currency-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './fund-raiser-bank-my-suffix.reducer';
import { IFundRaiserBankMySuffix } from 'app/shared/model/fund-raiser-bank-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFundRaiserBankMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFundRaiserBankMySuffixUpdateState {
  isNew: boolean;
  fundRaiserId: string;
  bankId: string;
  currencyId: string;
}

export class FundRaiserBankMySuffixUpdate extends React.Component<IFundRaiserBankMySuffixUpdateProps, IFundRaiserBankMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      fundRaiserId: '0',
      bankId: '0',
      currencyId: '0',
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

    this.props.getFundRaisers();
    this.props.getBanks();
    this.props.getCurrencies();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { fundRaiserBankEntity } = this.props;
      const entity = {
        ...fundRaiserBankEntity,
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
    this.props.history.push('/entity/fund-raiser-bank-my-suffix');
  };

  render() {
    const { fundRaiserBankEntity, fundRaisers, banks, currencies, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.fundRaiserBank.home.createOrEditLabel">Create or edit a FundRaiserBank</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : fundRaiserBankEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="fund-raiser-bank-my-suffix-id">ID</Label>
                    <AvInput id="fund-raiser-bank-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="bankAccountNoLabel" for="fund-raiser-bank-my-suffix-bankAccountNo">
                    Bank Account No
                  </Label>
                  <AvField
                    id="fund-raiser-bank-my-suffix-bankAccountNo"
                    type="text"
                    name="bankAccountNo"
                    validate={{
                      maxLength: { value: 60, errorMessage: 'This field cannot be longer than 60 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bankAccountNameLabel" for="fund-raiser-bank-my-suffix-bankAccountName">
                    Bank Account Name
                  </Label>
                  <AvField
                    id="fund-raiser-bank-my-suffix-bankAccountName"
                    type="text"
                    name="bankAccountName"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bankBranchLabel" for="fund-raiser-bank-my-suffix-bankBranch">
                    Bank Branch
                  </Label>
                  <AvField
                    id="fund-raiser-bank-my-suffix-bankBranch"
                    type="text"
                    name="bankBranch"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="fund-raiser-bank-my-suffix-status">
                    Status
                  </Label>
                  <AvField
                    id="fund-raiser-bank-my-suffix-status"
                    type="text"
                    name="status"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="fund-raiser-bank-my-suffix-fundRaiser">Fund Raiser</Label>
                  <AvInput id="fund-raiser-bank-my-suffix-fundRaiser" type="select" className="form-control" name="fundRaiserId">
                    <option value="" key="0" />
                    {fundRaisers
                      ? fundRaisers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="fund-raiser-bank-my-suffix-bank">Bank</Label>
                  <AvInput id="fund-raiser-bank-my-suffix-bank" type="select" className="form-control" name="bankId">
                    <option value="" key="0" />
                    {banks
                      ? banks.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="fund-raiser-bank-my-suffix-currency">Currency</Label>
                  <AvInput id="fund-raiser-bank-my-suffix-currency" type="select" className="form-control" name="currencyId">
                    <option value="" key="0" />
                    {currencies
                      ? currencies.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/fund-raiser-bank-my-suffix" replace color="info">
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
  fundRaisers: storeState.fundRaiser.entities,
  banks: storeState.bank.entities,
  currencies: storeState.currency.entities,
  fundRaiserBankEntity: storeState.fundRaiserBank.entity,
  loading: storeState.fundRaiserBank.loading,
  updating: storeState.fundRaiserBank.updating,
  updateSuccess: storeState.fundRaiserBank.updateSuccess
});

const mapDispatchToProps = {
  getFundRaisers,
  getBanks,
  getCurrencies,
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
)(FundRaiserBankMySuffixUpdate);
