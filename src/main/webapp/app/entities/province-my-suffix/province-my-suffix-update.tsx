import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICountryMySuffix } from 'app/shared/model/country-my-suffix.model';
import { getEntities as getCountries } from 'app/entities/country-my-suffix/country-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './province-my-suffix.reducer';
import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProvinceMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IProvinceMySuffixUpdateState {
  isNew: boolean;
  countryId: string;
}

export class ProvinceMySuffixUpdate extends React.Component<IProvinceMySuffixUpdateProps, IProvinceMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      countryId: '0',
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

    this.props.getCountries();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { provinceEntity } = this.props;
      const entity = {
        ...provinceEntity,
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
    this.props.history.push('/entity/province-my-suffix');
  };

  render() {
    const { provinceEntity, countries, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.province.home.createOrEditLabel">Create or edit a Province</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : provinceEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="province-my-suffix-id">ID</Label>
                    <AvInput id="province-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="provinceCodeLabel" for="province-my-suffix-provinceCode">
                    Province Code
                  </Label>
                  <AvField
                    id="province-my-suffix-provinceCode"
                    type="text"
                    name="provinceCode"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="provinceNameLabel" for="province-my-suffix-provinceName">
                    Province Name
                  </Label>
                  <AvField
                    id="province-my-suffix-provinceName"
                    type="text"
                    name="provinceName"
                    validate={{
                      maxLength: { value: 60, errorMessage: 'This field cannot be longer than 60 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="province-my-suffix-country">Country</Label>
                  <AvInput id="province-my-suffix-country" type="select" className="form-control" name="countryId">
                    <option value="" key="0" />
                    {countries
                      ? countries.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/province-my-suffix" replace color="info">
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
  countries: storeState.country.entities,
  provinceEntity: storeState.province.entity,
  loading: storeState.province.loading,
  updating: storeState.province.updating,
  updateSuccess: storeState.province.updateSuccess
});

const mapDispatchToProps = {
  getCountries,
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
)(ProvinceMySuffixUpdate);
