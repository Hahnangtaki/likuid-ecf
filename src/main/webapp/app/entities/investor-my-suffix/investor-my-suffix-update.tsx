import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IInvestorInstitutionMySuffix } from 'app/shared/model/investor-institution-my-suffix.model';
import { getEntities as getInvestorInstitutions } from 'app/entities/investor-institution-my-suffix/investor-institution-my-suffix.reducer';
import { IInvestorIndividuMySuffix } from 'app/shared/model/investor-individu-my-suffix.model';
import { getEntities as getInvestorIndividus } from 'app/entities/investor-individu-my-suffix/investor-individu-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './investor-my-suffix.reducer';
import { IInvestorMySuffix } from 'app/shared/model/investor-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInvestorMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IInvestorMySuffixUpdateState {
  isNew: boolean;
  investorInstitutionId: string;
  investorIndividuId: string;
}

export class InvestorMySuffixUpdate extends React.Component<IInvestorMySuffixUpdateProps, IInvestorMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      investorInstitutionId: '0',
      investorIndividuId: '0',
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

    this.props.getInvestorInstitutions();
    this.props.getInvestorIndividus();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { investorEntity } = this.props;
      const entity = {
        ...investorEntity,
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
    this.props.history.push('/entity/investor-my-suffix');
  };

  render() {
    const { investorEntity, investorInstitutions, investorIndividus, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.investor.home.createOrEditLabel">Create or edit a Investor</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : investorEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="investor-my-suffix-id">ID</Label>
                    <AvInput id="investor-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="investorCodeLabel" for="investor-my-suffix-investorCode">
                    Investor Code
                  </Label>
                  <AvField
                    id="investor-my-suffix-investorCode"
                    type="text"
                    name="investorCode"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="investorNameLabel" for="investor-my-suffix-investorName">
                    Investor Name
                  </Label>
                  <AvField
                    id="investor-my-suffix-investorName"
                    type="text"
                    name="investorName"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="investorTypeLabel" for="investor-my-suffix-investorType">
                    Investor Type
                  </Label>
                  <AvField
                    id="investor-my-suffix-investorType"
                    type="text"
                    name="investorType"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="investor-my-suffix-investorInstitution">Investor Institution</Label>
                  <AvInput id="investor-my-suffix-investorInstitution" type="select" className="form-control" name="investorInstitutionId">
                    <option value="" key="0" />
                    {investorInstitutions
                      ? investorInstitutions.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="investor-my-suffix-investorIndividu">Investor Individu</Label>
                  <AvInput id="investor-my-suffix-investorIndividu" type="select" className="form-control" name="investorIndividuId">
                    <option value="" key="0" />
                    {investorIndividus
                      ? investorIndividus.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/investor-my-suffix" replace color="info">
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
  investorInstitutions: storeState.investorInstitution.entities,
  investorIndividus: storeState.investorIndividu.entities,
  investorEntity: storeState.investor.entity,
  loading: storeState.investor.loading,
  updating: storeState.investor.updating,
  updateSuccess: storeState.investor.updateSuccess
});

const mapDispatchToProps = {
  getInvestorInstitutions,
  getInvestorIndividus,
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
)(InvestorMySuffixUpdate);
