import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICityMySuffix } from 'app/shared/model/city-my-suffix.model';
import { getEntities as getCities } from 'app/entities/city-my-suffix/city-my-suffix.reducer';
import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
import { getEntities as getProvinces } from 'app/entities/province-my-suffix/province-my-suffix.reducer';
import { ICountryMySuffix } from 'app/shared/model/country-my-suffix.model';
import { getEntities as getCountries } from 'app/entities/country-my-suffix/country-my-suffix.reducer';
import { IInvestorMySuffix } from 'app/shared/model/investor-my-suffix.model';
import { getEntities as getInvestors } from 'app/entities/investor-my-suffix/investor-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './investor-address-my-suffix.reducer';
import { IInvestorAddressMySuffix } from 'app/shared/model/investor-address-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInvestorAddressMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IInvestorAddressMySuffixUpdateState {
  isNew: boolean;
  cityId: string;
  provinceId: string;
  countryId: string;
  investorId: string;
}

export class InvestorAddressMySuffixUpdate extends React.Component<
  IInvestorAddressMySuffixUpdateProps,
  IInvestorAddressMySuffixUpdateState
> {
  constructor(props) {
    super(props);
    this.state = {
      cityId: '0',
      provinceId: '0',
      countryId: '0',
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

    this.props.getCities();
    this.props.getProvinces();
    this.props.getCountries();
    this.props.getInvestors();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { investorAddressEntity } = this.props;
      const entity = {
        ...investorAddressEntity,
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
    this.props.history.push('/entity/investor-address-my-suffix');
  };

  render() {
    const { investorAddressEntity, cities, provinces, countries, investors, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.investorAddress.home.createOrEditLabel">Create or edit a InvestorAddress</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : investorAddressEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="investor-address-my-suffix-id">ID</Label>
                    <AvInput id="investor-address-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="addressTypeLabel" for="investor-address-my-suffix-addressType">
                    Address Type
                  </Label>
                  <AvField
                    id="investor-address-my-suffix-addressType"
                    type="text"
                    name="addressType"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="address1Label" for="investor-address-my-suffix-address1">
                    Address 1
                  </Label>
                  <AvField
                    id="investor-address-my-suffix-address1"
                    type="text"
                    name="address1"
                    validate={{
                      maxLength: { value: 60, errorMessage: 'This field cannot be longer than 60 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="address2Label" for="investor-address-my-suffix-address2">
                    Address 2
                  </Label>
                  <AvField
                    id="investor-address-my-suffix-address2"
                    type="text"
                    name="address2"
                    validate={{
                      maxLength: { value: 60, errorMessage: 'This field cannot be longer than 60 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="address3Label" for="investor-address-my-suffix-address3">
                    Address 3
                  </Label>
                  <AvField
                    id="investor-address-my-suffix-address3"
                    type="text"
                    name="address3"
                    validate={{
                      maxLength: { value: 60, errorMessage: 'This field cannot be longer than 60 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="postalCodeLabel" for="investor-address-my-suffix-postalCode">
                    Postal Code
                  </Label>
                  <AvField
                    id="investor-address-my-suffix-postalCode"
                    type="text"
                    name="postalCode"
                    validate={{
                      maxLength: { value: 5, errorMessage: 'This field cannot be longer than 5 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="phoneLabel" for="investor-address-my-suffix-phone">
                    Phone
                  </Label>
                  <AvField
                    id="investor-address-my-suffix-phone"
                    type="text"
                    name="phone"
                    validate={{
                      maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="mobilePhoneLabel" for="investor-address-my-suffix-mobilePhone">
                    Mobile Phone
                  </Label>
                  <AvField
                    id="investor-address-my-suffix-mobilePhone"
                    type="text"
                    name="mobilePhone"
                    validate={{
                      maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="investor-address-my-suffix-email">
                    Email
                  </Label>
                  <AvField
                    id="investor-address-my-suffix-email"
                    type="text"
                    name="email"
                    validate={{
                      maxLength: { value: 256, errorMessage: 'This field cannot be longer than 256 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="faxLabel" for="investor-address-my-suffix-fax">
                    Fax
                  </Label>
                  <AvField
                    id="investor-address-my-suffix-fax"
                    type="text"
                    name="fax"
                    validate={{
                      maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="investor-address-my-suffix-city">City</Label>
                  <AvInput id="investor-address-my-suffix-city" type="select" className="form-control" name="cityId">
                    <option value="" key="0" />
                    {cities
                      ? cities.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="investor-address-my-suffix-province">Province</Label>
                  <AvInput id="investor-address-my-suffix-province" type="select" className="form-control" name="provinceId">
                    <option value="" key="0" />
                    {provinces
                      ? provinces.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="investor-address-my-suffix-country">Country</Label>
                  <AvInput id="investor-address-my-suffix-country" type="select" className="form-control" name="countryId">
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
                <AvGroup>
                  <Label for="investor-address-my-suffix-investor">Investor</Label>
                  <AvInput id="investor-address-my-suffix-investor" type="select" className="form-control" name="investorId">
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
                <Button tag={Link} id="cancel-save" to="/entity/investor-address-my-suffix" replace color="info">
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
  cities: storeState.city.entities,
  provinces: storeState.province.entities,
  countries: storeState.country.entities,
  investors: storeState.investor.entities,
  investorAddressEntity: storeState.investorAddress.entity,
  loading: storeState.investorAddress.loading,
  updating: storeState.investorAddress.updating,
  updateSuccess: storeState.investorAddress.updateSuccess
});

const mapDispatchToProps = {
  getCities,
  getProvinces,
  getCountries,
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
)(InvestorAddressMySuffixUpdate);
