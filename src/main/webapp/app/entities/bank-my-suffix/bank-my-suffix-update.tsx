import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './bank-my-suffix.reducer';
import { IBankMySuffix } from 'app/shared/model/bank-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBankMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IBankMySuffixUpdateState {
  isNew: boolean;
}

export class BankMySuffixUpdate extends React.Component<IBankMySuffixUpdateProps, IBankMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { bankEntity } = this.props;
      const entity = {
        ...bankEntity,
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
    this.props.history.push('/entity/bank-my-suffix');
  };

  render() {
    const { bankEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.bank.home.createOrEditLabel">Create or edit a Bank</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : bankEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="bank-my-suffix-id">ID</Label>
                    <AvInput id="bank-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="bankCodeLabel" for="bank-my-suffix-bankCode">
                    Bank Code
                  </Label>
                  <AvField
                    id="bank-my-suffix-bankCode"
                    type="text"
                    name="bankCode"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bankNameLabel" for="bank-my-suffix-bankName">
                    Bank Name
                  </Label>
                  <AvField
                    id="bank-my-suffix-bankName"
                    type="text"
                    name="bankName"
                    validate={{
                      maxLength: { value: 60, errorMessage: 'This field cannot be longer than 60 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="initialNameLabel" for="bank-my-suffix-initialName">
                    Initial Name
                  </Label>
                  <AvField
                    id="bank-my-suffix-initialName"
                    type="text"
                    name="initialName"
                    validate={{
                      maxLength: { value: 60, errorMessage: 'This field cannot be longer than 60 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="biCodeLabel" for="bank-my-suffix-biCode">
                    Bi Code
                  </Label>
                  <AvField
                    id="bank-my-suffix-biCode"
                    type="text"
                    name="biCode"
                    validate={{
                      maxLength: { value: 3, errorMessage: 'This field cannot be longer than 3 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="swiftCodeLabel" for="bank-my-suffix-swiftCode">
                    Swift Code
                  </Label>
                  <AvField
                    id="bank-my-suffix-swiftCode"
                    type="text"
                    name="swiftCode"
                    validate={{
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/bank-my-suffix" replace color="info">
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
  bankEntity: storeState.bank.entity,
  loading: storeState.bank.loading,
  updating: storeState.bank.updating,
  updateSuccess: storeState.bank.updateSuccess
});

const mapDispatchToProps = {
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
)(BankMySuffixUpdate);
