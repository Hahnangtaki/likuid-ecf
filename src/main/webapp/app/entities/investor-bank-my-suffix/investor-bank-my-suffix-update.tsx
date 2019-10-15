import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBankMySuffix } from 'app/shared/model/bank-my-suffix.model';
import { getEntities as getBanks } from 'app/entities/bank-my-suffix/bank-my-suffix.reducer';
import { ICurrencyMySuffix } from 'app/shared/model/currency-my-suffix.model';
import { getEntities as getCurrencies } from 'app/entities/currency-my-suffix/currency-my-suffix.reducer';
import { IInvestorMySuffix } from 'app/shared/model/investor-my-suffix.model';
import { getEntities as getInvestors } from 'app/entities/investor-my-suffix/investor-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './investor-bank-my-suffix.reducer';
import { IInvestorBankMySuffix } from 'app/shared/model/investor-bank-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInvestorBankMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IInvestorBankMySuffixUpdateState {
  isNew: boolean;
  bankId: string;
  currencyId: string;
  investorId: string;
}

export class InvestorBankMySuffixUpdate extends React.Component<IInvestorBankMySuffixUpdateProps, IInvestorBankMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      bankId: '0',
      currencyId: '0',
      investorId: '0',
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

    this.props.getBanks();
    this.props.getCurrencies();
    this.props.getInvestors();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { investorBankEntity } = this.props;
      const entity = {
        ...investorBankEntity,
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
    this.props.history.push('/entity/investor-bank-my-suffix');
  };

  render() {
    const { investorBankEntity, banks, currencies, investors, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.investorBank.home.createOrEditLabel">Create or edit a InvestorBank</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : investorBankEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="investor-bank-my-suffix-id">ID</Label>
                    <AvInput id="investor-bank-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="bankAccountNoLabel" for="investor-bank-my-suffix-bankAccountNo">
                    Bank Account No
                  </Label>
                  <AvField
                    id="investor-bank-my-suffix-bankAccountNo"
                    type="text"
                    name="bankAccountNo"
                    validate={{
                      maxLength: { value: 60, errorMessage: 'This field cannot be longer than 60 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bankAccountNameLabel" for="investor-bank-my-suffix-bankAccountName">
                    Bank Account Name
                  </Label>
                  <AvField
                    id="investor-bank-my-suffix-bankAccountName"
                    type="text"
                    name="bankAccountName"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bankBranchLabel" for="investor-bank-my-suffix-bankBranch">
                    Bank Branch
                  </Label>
                  <AvField
                    id="investor-bank-my-suffix-bankBranch"
                    type="text"
                    name="bankBranch"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="investor-bank-my-suffix-status">
                    Status
                  </Label>
                  <AvField
                    id="investor-bank-my-suffix-status"
                    type="text"
                    name="status"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="investor-bank-my-suffix-bank">Bank</Label>
                  <AvInput id="investor-bank-my-suffix-bank" type="select" className="form-control" name="bankId">
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
                  <Label for="investor-bank-my-suffix-currency">Currency</Label>
                  <AvInput id="investor-bank-my-suffix-currency" type="select" className="form-control" name="currencyId">
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
                <AvGroup>
                  <Label for="investor-bank-my-suffix-investor">Investor</Label>
                  <AvInput id="investor-bank-my-suffix-investor" type="select" className="form-control" name="investorId">
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
                <Button tag={Link} id="cancel-save" to="/entity/investor-bank-my-suffix" replace color="info">
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
  banks: storeState.bank.entities,
  currencies: storeState.currency.entities,
  investors: storeState.investor.entities,
  investorBankEntity: storeState.investorBank.entity,
  loading: storeState.investorBank.loading,
  updating: storeState.investorBank.updating,
  updateSuccess: storeState.investorBank.updateSuccess
});

const mapDispatchToProps = {
  getBanks,
  getCurrencies,
  getInvestors,
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
)(InvestorBankMySuffixUpdate);
