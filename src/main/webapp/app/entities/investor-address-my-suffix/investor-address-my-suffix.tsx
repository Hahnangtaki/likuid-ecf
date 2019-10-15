import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './investor-address-my-suffix.reducer';
import { IInvestorAddressMySuffix } from 'app/shared/model/investor-address-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorAddressMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class InvestorAddressMySuffix extends React.Component<IInvestorAddressMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { investorAddressList, match } = this.props;
    return (
      <div>
        <h2 id="investor-address-my-suffix-heading">
          Investor Addresses
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Investor Address
          </Link>
        </h2>
        <div className="table-responsive">
          {investorAddressList && investorAddressList.length > 0 ? (
            <Table responsive aria-describedby="investor-address-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Address Type</th>
                  <th>Address 1</th>
                  <th>Address 2</th>
                  <th>Address 3</th>
                  <th>Postal Code</th>
                  <th>Phone</th>
                  <th>Mobile Phone</th>
                  <th>Email</th>
                  <th>Fax</th>
                  <th>City</th>
                  <th>Province</th>
                  <th>Country</th>
                  <th>Investor</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {investorAddressList.map((investorAddress, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${investorAddress.id}`} color="link" size="sm">
                        {investorAddress.id}
                      </Button>
                    </td>
                    <td>{investorAddress.addressType}</td>
                    <td>{investorAddress.address1}</td>
                    <td>{investorAddress.address2}</td>
                    <td>{investorAddress.address3}</td>
                    <td>{investorAddress.postalCode}</td>
                    <td>{investorAddress.phone}</td>
                    <td>{investorAddress.mobilePhone}</td>
                    <td>{investorAddress.email}</td>
                    <td>{investorAddress.fax}</td>
                    <td>
                      {investorAddress.cityId ? <Link to={`city-my-suffix/${investorAddress.cityId}`}>{investorAddress.cityId}</Link> : ''}
                    </td>
                    <td>
                      {investorAddress.provinceId ? (
                        <Link to={`province-my-suffix/${investorAddress.provinceId}`}>{investorAddress.provinceId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {investorAddress.countryId ? (
                        <Link to={`country-my-suffix/${investorAddress.countryId}`}>{investorAddress.countryId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {investorAddress.investorId ? (
                        <Link to={`investor-my-suffix/${investorAddress.investorId}`}>{investorAddress.investorId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${investorAddress.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${investorAddress.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${investorAddress.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Investor Addresses found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ investorAddress }: IRootState) => ({
  investorAddressList: investorAddress.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorAddressMySuffix);
