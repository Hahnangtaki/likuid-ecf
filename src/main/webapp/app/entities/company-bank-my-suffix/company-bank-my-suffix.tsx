import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './company-bank-my-suffix.reducer';
import { ICompanyBankMySuffix } from 'app/shared/model/company-bank-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICompanyBankMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class CompanyBankMySuffix extends React.Component<ICompanyBankMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { companyBankList, match } = this.props;
    return (
      <div>
        <h2 id="company-bank-my-suffix-heading">
          Company Banks
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Company Bank
          </Link>
        </h2>
        <div className="table-responsive">
          {companyBankList && companyBankList.length > 0 ? (
            <Table responsive aria-describedby="company-bank-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Bank Account No</th>
                  <th>Bank Account Name</th>
                  <th>Bank Branch</th>
                  <th>Status</th>
                  <th>Bank</th>
                  <th>Currency</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {companyBankList.map((companyBank, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${companyBank.id}`} color="link" size="sm">
                        {companyBank.id}
                      </Button>
                    </td>
                    <td>{companyBank.bankAccountNo}</td>
                    <td>{companyBank.bankAccountName}</td>
                    <td>{companyBank.bankBranch}</td>
                    <td>{companyBank.status}</td>
                    <td>{companyBank.bankId ? <Link to={`bank-my-suffix/${companyBank.bankId}`}>{companyBank.bankId}</Link> : ''}</td>
                    <td>
                      {companyBank.currencyId ? (
                        <Link to={`currency-my-suffix/${companyBank.currencyId}`}>{companyBank.currencyId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${companyBank.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${companyBank.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${companyBank.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Company Banks found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ companyBank }: IRootState) => ({
  companyBankList: companyBank.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CompanyBankMySuffix);
