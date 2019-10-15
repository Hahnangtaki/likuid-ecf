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
import { getEntity, updateEntity, createEntity, reset } from './investor-authorize-person-my-suffix.reducer';
import { IInvestorAuthorizePersonMySuffix } from 'app/shared/model/investor-authorize-person-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInvestorAuthorizePersonMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IInvestorAuthorizePersonMySuffixUpdateState {
  isNew: boolean;
  investorId: string;
}

export class InvestorAuthorizePersonMySuffixUpdate extends React.Component<
  IInvestorAuthorizePersonMySuffixUpdateProps,
  IInvestorAuthorizePersonMySuffixUpdateState
> {
  constructor(props) {
    super(props);
    this.state = {
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

    this.props.getInvestors();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { investorAuthorizePersonEntity } = this.props;
      const entity = {
        ...investorAuthorizePersonEntity,
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
    this.props.history.push('/entity/investor-authorize-person-my-suffix');
  };

  render() {
    const { investorAuthorizePersonEntity, investors, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.investorAuthorizePerson.home.createOrEditLabel">Create or edit a InvestorAuthorizePerson</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : investorAuthorizePersonEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="investor-authorize-person-my-suffix-id">ID</Label>
                    <AvInput id="investor-authorize-person-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="firstNameLabel" for="investor-authorize-person-my-suffix-firstName">
                    First Name
                  </Label>
                  <AvField
                    id="investor-authorize-person-my-suffix-firstName"
                    type="text"
                    name="firstName"
                    validate={{
                      maxLength: { value: 35, errorMessage: 'This field cannot be longer than 35 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="middleNameLabel" for="investor-authorize-person-my-suffix-middleName">
                    Middle Name
                  </Label>
                  <AvField
                    id="investor-authorize-person-my-suffix-middleName"
                    type="text"
                    name="middleName"
                    validate={{
                      maxLength: { value: 35, errorMessage: 'This field cannot be longer than 35 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lastNameLabel" for="investor-authorize-person-my-suffix-lastName">
                    Last Name
                  </Label>
                  <AvField
                    id="investor-authorize-person-my-suffix-lastName"
                    type="text"
                    name="lastName"
                    validate={{
                      maxLength: { value: 35, errorMessage: 'This field cannot be longer than 35 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="positionLabel" for="investor-authorize-person-my-suffix-position">
                    Position
                  </Label>
                  <AvField
                    id="investor-authorize-person-my-suffix-position"
                    type="text"
                    name="position"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ktpLabel" for="investor-authorize-person-my-suffix-ktp">
                    Ktp
                  </Label>
                  <AvField
                    id="investor-authorize-person-my-suffix-ktp"
                    type="text"
                    name="ktp"
                    validate={{
                      maxLength: { value: 25, errorMessage: 'This field cannot be longer than 25 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ktpExpDateLabel" for="investor-authorize-person-my-suffix-ktpExpDate">
                    Ktp Exp Date
                  </Label>
                  <AvField id="investor-authorize-person-my-suffix-ktpExpDate" type="date" className="form-control" name="ktpExpDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="npwpLabel" for="investor-authorize-person-my-suffix-npwp">
                    Npwp
                  </Label>
                  <AvField
                    id="investor-authorize-person-my-suffix-npwp"
                    type="text"
                    name="npwp"
                    validate={{
                      maxLength: { value: 15, errorMessage: 'This field cannot be longer than 15 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="npwpRegDateLabel" for="investor-authorize-person-my-suffix-npwpRegDate">
                    Npwp Reg Date
                  </Label>
                  <AvField id="investor-authorize-person-my-suffix-npwpRegDate" type="date" className="form-control" name="npwpRegDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="passportLabel" for="investor-authorize-person-my-suffix-passport">
                    Passport
                  </Label>
                  <AvField
                    id="investor-authorize-person-my-suffix-passport"
                    type="text"
                    name="passport"
                    validate={{
                      maxLength: { value: 25, errorMessage: 'This field cannot be longer than 25 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="passportExpDateLabel" for="investor-authorize-person-my-suffix-passportExpDate">
                    Passport Exp Date
                  </Label>
                  <AvField
                    id="investor-authorize-person-my-suffix-passportExpDate"
                    type="date"
                    className="form-control"
                    name="passportExpDate"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kitasLabel" for="investor-authorize-person-my-suffix-kitas">
                    Kitas
                  </Label>
                  <AvField
                    id="investor-authorize-person-my-suffix-kitas"
                    type="text"
                    name="kitas"
                    validate={{
                      maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kitasExpDateLabel" for="investor-authorize-person-my-suffix-kitasExpDate">
                    Kitas Exp Date
                  </Label>
                  <AvField id="investor-authorize-person-my-suffix-kitasExpDate" type="date" className="form-control" name="kitasExpDate" />
                </AvGroup>
                <AvGroup>
                  <Label for="investor-authorize-person-my-suffix-investor">Investor</Label>
                  <AvInput id="investor-authorize-person-my-suffix-investor" type="select" className="form-control" name="investorId">
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
                <Button tag={Link} id="cancel-save" to="/entity/investor-authorize-person-my-suffix" replace color="info">
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
  investorAuthorizePersonEntity: storeState.investorAuthorizePerson.entity,
  loading: storeState.investorAuthorizePerson.loading,
  updating: storeState.investorAuthorizePerson.updating,
  updateSuccess: storeState.investorAuthorizePerson.updateSuccess
});

const mapDispatchToProps = {
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
)(InvestorAuthorizePersonMySuffixUpdate);
