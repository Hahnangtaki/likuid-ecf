import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './tax-my-suffix.reducer';
import { ITaxMySuffix } from 'app/shared/model/tax-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITaxMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITaxMySuffixUpdateState {
  isNew: boolean;
}

export class TaxMySuffixUpdate extends React.Component<ITaxMySuffixUpdateProps, ITaxMySuffixUpdateState> {
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
      const { taxEntity } = this.props;
      const entity = {
        ...taxEntity,
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
    this.props.history.push('/entity/tax-my-suffix');
  };

  render() {
    const { taxEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.tax.home.createOrEditLabel">Create or edit a Tax</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : taxEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="tax-my-suffix-id">ID</Label>
                    <AvInput id="tax-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="taxCodeLabel" for="tax-my-suffix-taxCode">
                    Tax Code
                  </Label>
                  <AvField
                    id="tax-my-suffix-taxCode"
                    type="text"
                    name="taxCode"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="shortDescLabel" for="tax-my-suffix-shortDesc">
                    Short Desc
                  </Label>
                  <AvField
                    id="tax-my-suffix-shortDesc"
                    type="text"
                    name="shortDesc"
                    validate={{
                      maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="longDescLabel" for="tax-my-suffix-longDesc">
                    Long Desc
                  </Label>
                  <AvField
                    id="tax-my-suffix-longDesc"
                    type="text"
                    name="longDesc"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/tax-my-suffix" replace color="info">
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
  taxEntity: storeState.tax.entity,
  loading: storeState.tax.loading,
  updating: storeState.tax.updating,
  updateSuccess: storeState.tax.updateSuccess
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
)(TaxMySuffixUpdate);
