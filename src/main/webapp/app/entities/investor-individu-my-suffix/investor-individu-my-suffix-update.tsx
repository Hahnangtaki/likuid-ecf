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
import { ITaxMySuffix } from 'app/shared/model/tax-my-suffix.model';
import { getEntities as getTaxes } from 'app/entities/tax-my-suffix/tax-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './investor-individu-my-suffix.reducer';
import { IInvestorIndividuMySuffix } from 'app/shared/model/investor-individu-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInvestorIndividuMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IInvestorIndividuMySuffixUpdateState {
  isNew: boolean;
  investorId: string;
  taxId: string;
}

export class InvestorIndividuMySuffixUpdate extends React.Component<
  IInvestorIndividuMySuffixUpdateProps,
  IInvestorIndividuMySuffixUpdateState
> {
  constructor(props) {
    super(props);
    this.state = {
      investorId: '0',
      taxId: '0',
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
    this.props.getTaxes();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { investorIndividuEntity } = this.props;
      const entity = {
        ...investorIndividuEntity,
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
    this.props.history.push('/entity/investor-individu-my-suffix');
  };

  render() {
    const { investorIndividuEntity, investors, taxes, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.investorIndividu.home.createOrEditLabel">Create or edit a InvestorIndividu</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : investorIndividuEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="investor-individu-my-suffix-id">ID</Label>
                    <AvInput id="investor-individu-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="sidLabel" for="investor-individu-my-suffix-sid">
                    Sid
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-sid"
                    type="text"
                    name="sid"
                    validate={{
                      maxLength: { value: 15, errorMessage: 'This field cannot be longer than 15 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="firstNameLabel" for="investor-individu-my-suffix-firstName">
                    First Name
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-firstName"
                    type="text"
                    name="firstName"
                    validate={{
                      maxLength: { value: 35, errorMessage: 'This field cannot be longer than 35 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="middleNameLabel" for="investor-individu-my-suffix-middleName">
                    Middle Name
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-middleName"
                    type="text"
                    name="middleName"
                    validate={{
                      maxLength: { value: 35, errorMessage: 'This field cannot be longer than 35 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lastNameLabel" for="investor-individu-my-suffix-lastName">
                    Last Name
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-lastName"
                    type="text"
                    name="lastName"
                    validate={{
                      maxLength: { value: 35, errorMessage: 'This field cannot be longer than 35 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nationalityIdLabel" for="investor-individu-my-suffix-nationalityId">
                    Nationality Id
                  </Label>
                  <AvField id="investor-individu-my-suffix-nationalityId" type="string" className="form-control" name="nationalityId" />
                </AvGroup>
                <AvGroup>
                  <Label id="ktpLabel" for="investor-individu-my-suffix-ktp">
                    Ktp
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-ktp"
                    type="text"
                    name="ktp"
                    validate={{
                      maxLength: { value: 25, errorMessage: 'This field cannot be longer than 25 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ktpExpDateLabel" for="investor-individu-my-suffix-ktpExpDate">
                    Ktp Exp Date
                  </Label>
                  <AvField id="investor-individu-my-suffix-ktpExpDate" type="date" className="form-control" name="ktpExpDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="npwpLabel" for="investor-individu-my-suffix-npwp">
                    Npwp
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-npwp"
                    type="text"
                    name="npwp"
                    validate={{
                      maxLength: { value: 15, errorMessage: 'This field cannot be longer than 15 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="npwpRegDateLabel" for="investor-individu-my-suffix-npwpRegDate">
                    Npwp Reg Date
                  </Label>
                  <AvField id="investor-individu-my-suffix-npwpRegDate" type="date" className="form-control" name="npwpRegDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="passportLabel" for="investor-individu-my-suffix-passport">
                    Passport
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-passport"
                    type="text"
                    name="passport"
                    validate={{
                      maxLength: { value: 25, errorMessage: 'This field cannot be longer than 25 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="passportExpDateLabel" for="investor-individu-my-suffix-passportExpDate">
                    Passport Exp Date
                  </Label>
                  <AvField id="investor-individu-my-suffix-passportExpDate" type="date" className="form-control" name="passportExpDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="kitasLabel" for="investor-individu-my-suffix-kitas">
                    Kitas
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-kitas"
                    type="text"
                    name="kitas"
                    validate={{
                      maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kitasExpDateLabel" for="investor-individu-my-suffix-kitasExpDate">
                    Kitas Exp Date
                  </Label>
                  <AvField id="investor-individu-my-suffix-kitasExpDate" type="date" className="form-control" name="kitasExpDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="birthPlaceLabel" for="investor-individu-my-suffix-birthPlace">
                    Birth Place
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-birthPlace"
                    type="text"
                    name="birthPlace"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="birthDateLabel" for="investor-individu-my-suffix-birthDate">
                    Birth Date
                  </Label>
                  <AvField id="investor-individu-my-suffix-birthDate" type="date" className="form-control" name="birthDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="sexLabel" for="investor-individu-my-suffix-sex">
                    Sex
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-sex"
                    type="text"
                    name="sex"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="maritalStatusLabel" for="investor-individu-my-suffix-maritalStatus">
                    Marital Status
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-maritalStatus"
                    type="text"
                    name="maritalStatus"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="spouseNameLabel" for="investor-individu-my-suffix-spouseName">
                    Spouse Name
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-spouseName"
                    type="text"
                    name="spouseName"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="heirLabel" for="investor-individu-my-suffix-heir">
                    Heir
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-heir"
                    type="text"
                    name="heir"
                    validate={{
                      maxLength: { value: 120, errorMessage: 'This field cannot be longer than 120 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="heirRalationLabel" for="investor-individu-my-suffix-heirRalation">
                    Heir Ralation
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-heirRalation"
                    type="text"
                    name="heirRalation"
                    validate={{
                      maxLength: { value: 120, errorMessage: 'This field cannot be longer than 120 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="educationBackgroundLabel" for="investor-individu-my-suffix-educationBackground">
                    Education Background
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-educationBackground"
                    type="text"
                    name="educationBackground"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="occupationLabel" for="investor-individu-my-suffix-occupation">
                    Occupation
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-occupation"
                    type="text"
                    name="occupation"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="natureOfBusinessLabel" for="investor-individu-my-suffix-natureOfBusiness">
                    Nature Of Business
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-natureOfBusiness"
                    type="text"
                    name="natureOfBusiness"
                    validate={{
                      maxLength: { value: 120, errorMessage: 'This field cannot be longer than 120 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="annualIncomeLabel" for="investor-individu-my-suffix-annualIncome">
                    Annual Income
                  </Label>
                  <AvField id="investor-individu-my-suffix-annualIncome" type="string" className="form-control" name="annualIncome" />
                </AvGroup>
                <AvGroup>
                  <Label id="fundSourceLabel" for="investor-individu-my-suffix-fundSource">
                    Fund Source
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-fundSource"
                    type="text"
                    name="fundSource"
                    validate={{
                      maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fundSourceTextLabel" for="investor-individu-my-suffix-fundSourceText">
                    Fund Source Text
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-fundSourceText"
                    type="text"
                    name="fundSourceText"
                    validate={{
                      maxLength: { value: 120, errorMessage: 'This field cannot be longer than 120 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="investor-individu-my-suffix-description">
                    Description
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="investmentObjectiveLabel" for="investor-individu-my-suffix-investmentObjective">
                    Investment Objective
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-investmentObjective"
                    type="text"
                    name="investmentObjective"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="motherMaidenNameLabel" for="investor-individu-my-suffix-motherMaidenName">
                    Mother Maiden Name
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-motherMaidenName"
                    type="text"
                    name="motherMaidenName"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="directSidLabel" for="investor-individu-my-suffix-directSid">
                    Direct Sid
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-directSid"
                    type="text"
                    name="directSid"
                    validate={{
                      maxLength: { value: 15, errorMessage: 'This field cannot be longer than 15 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="assetOwnerLabel" for="investor-individu-my-suffix-assetOwner">
                    Asset Owner
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-assetOwner"
                    type="text"
                    name="assetOwner"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="authPersonKtpRegDateLabel" for="investor-individu-my-suffix-authPersonKtpRegDate">
                    Auth Person Ktp Reg Date
                  </Label>
                  <AvField
                    id="investor-individu-my-suffix-authPersonKtpRegDate"
                    type="date"
                    className="form-control"
                    name="authPersonKtpRegDate"
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="investor-individu-my-suffix-tax">Tax</Label>
                  <AvInput id="investor-individu-my-suffix-tax" type="select" className="form-control" name="taxId">
                    <option value="" key="0" />
                    {taxes
                      ? taxes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/investor-individu-my-suffix" replace color="info">
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
  taxes: storeState.tax.entities,
  investorIndividuEntity: storeState.investorIndividu.entity,
  loading: storeState.investorIndividu.loading,
  updating: storeState.investorIndividu.updating,
  updateSuccess: storeState.investorIndividu.updateSuccess
});

const mapDispatchToProps = {
  getInvestors,
  getTaxes,
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
)(InvestorIndividuMySuffixUpdate);
