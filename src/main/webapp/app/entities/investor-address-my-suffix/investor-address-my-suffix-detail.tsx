import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './investor-address-my-suffix.reducer';
import { IInvestorAddressMySuffix } from 'app/shared/model/investor-address-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorAddressMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class InvestorAddressMySuffixDetail extends React.Component<IInvestorAddressMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { investorAddressEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            InvestorAddress [<b>{investorAddressEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="addressType">Address Type</span>
            </dt>
            <dd>{investorAddressEntity.addressType}</dd>
            <dt>
              <span id="address1">Address 1</span>
            </dt>
            <dd>{investorAddressEntity.address1}</dd>
            <dt>
              <span id="address2">Address 2</span>
            </dt>
            <dd>{investorAddressEntity.address2}</dd>
            <dt>
              <span id="address3">Address 3</span>
            </dt>
            <dd>{investorAddressEntity.address3}</dd>
            <dt>
              <span id="postalCode">Postal Code</span>
            </dt>
            <dd>{investorAddressEntity.postalCode}</dd>
            <dt>
              <span id="phone">Phone</span>
            </dt>
            <dd>{investorAddressEntity.phone}</dd>
            <dt>
              <span id="mobilePhone">Mobile Phone</span>
            </dt>
            <dd>{investorAddressEntity.mobilePhone}</dd>
            <dt>
              <span id="email">Email</span>
            </dt>
            <dd>{investorAddressEntity.email}</dd>
            <dt>
              <span id="fax">Fax</span>
            </dt>
            <dd>{investorAddressEntity.fax}</dd>
            <dt>City</dt>
            <dd>{investorAddressEntity.cityId ? investorAddressEntity.cityId : ''}</dd>
            <dt>Province</dt>
            <dd>{investorAddressEntity.provinceId ? investorAddressEntity.provinceId : ''}</dd>
            <dt>Country</dt>
            <dd>{investorAddressEntity.countryId ? investorAddressEntity.countryId : ''}</dd>
            <dt>Investor</dt>
            <dd>{investorAddressEntity.investorId ? investorAddressEntity.investorId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/investor-address-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/investor-address-my-suffix/${investorAddressEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ investorAddress }: IRootState) => ({
  investorAddressEntity: investorAddress.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorAddressMySuffixDetail);
