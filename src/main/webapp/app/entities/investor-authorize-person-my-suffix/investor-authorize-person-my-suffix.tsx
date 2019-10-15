import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './investor-authorize-person-my-suffix.reducer';
import { IInvestorAuthorizePersonMySuffix } from 'app/shared/model/investor-authorize-person-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorAuthorizePersonMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class InvestorAuthorizePersonMySuffix extends React.Component<IInvestorAuthorizePersonMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { investorAuthorizePersonList, match } = this.props;
    return (
      <div>
        <h2 id="investor-authorize-person-my-suffix-heading">
          Investor Authorize People
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Investor Authorize Person
          </Link>
        </h2>
        <div className="table-responsive">
          {investorAuthorizePersonList && investorAuthorizePersonList.length > 0 ? (
            <Table responsive aria-describedby="investor-authorize-person-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>First Name</th>
                  <th>Middle Name</th>
                  <th>Last Name</th>
                  <th>Position</th>
                  <th>Ktp</th>
                  <th>Ktp Exp Date</th>
                  <th>Npwp</th>
                  <th>Npwp Reg Date</th>
                  <th>Passport</th>
                  <th>Passport Exp Date</th>
                  <th>Kitas</th>
                  <th>Kitas Exp Date</th>
                  <th>Investor</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {investorAuthorizePersonList.map((investorAuthorizePerson, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${investorAuthorizePerson.id}`} color="link" size="sm">
                        {investorAuthorizePerson.id}
                      </Button>
                    </td>
                    <td>{investorAuthorizePerson.firstName}</td>
                    <td>{investorAuthorizePerson.middleName}</td>
                    <td>{investorAuthorizePerson.lastName}</td>
                    <td>{investorAuthorizePerson.position}</td>
                    <td>{investorAuthorizePerson.ktp}</td>
                    <td>
                      <TextFormat type="date" value={investorAuthorizePerson.ktpExpDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{investorAuthorizePerson.npwp}</td>
                    <td>
                      <TextFormat type="date" value={investorAuthorizePerson.npwpRegDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{investorAuthorizePerson.passport}</td>
                    <td>
                      <TextFormat type="date" value={investorAuthorizePerson.passportExpDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{investorAuthorizePerson.kitas}</td>
                    <td>
                      <TextFormat type="date" value={investorAuthorizePerson.kitasExpDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>
                      {investorAuthorizePerson.investorId ? (
                        <Link to={`investor-my-suffix/${investorAuthorizePerson.investorId}`}>{investorAuthorizePerson.investorId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${investorAuthorizePerson.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${investorAuthorizePerson.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${investorAuthorizePerson.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Investor Authorize People found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ investorAuthorizePerson }: IRootState) => ({
  investorAuthorizePersonList: investorAuthorizePerson.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorAuthorizePersonMySuffix);
