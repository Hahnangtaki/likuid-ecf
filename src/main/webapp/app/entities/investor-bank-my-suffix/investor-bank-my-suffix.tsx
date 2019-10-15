import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './investor-bank-my-suffix.reducer';
import { IInvestorBankMySuffix } from 'app/shared/model/investor-bank-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorBankMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class InvestorBankMySuffix extends React.Component<IInvestorBankMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { investorBankList, match } = this.props;
    return (
      <div>
        <h2 id="investor-bank-my-suffix-heading">
          Investor Banks
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Investor Bank
          </Link>
        </h2>
        <div className="table-responsive">
          {investorBankList && investorBankList.length > 0 ? (
            <Table responsive aria-describedby="investor-bank-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Bank Account No</th>
                  <th>Bank Account Name</th>
                  <th>Bank Branch</th>
                  <th>Status</th>
                  <th>Bank</th>
                  <th>Currency</th>
                  <th>Investor</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {investorBankList.map((investorBank, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${investorBank.id}`} color="link" size="sm">
                        {investorBank.id}
                      </Button>
                    </td>
                    <td>{investorBank.bankAccountNo}</td>
                    <td>{investorBank.bankAccountName}</td>
                    <td>{investorBank.bankBranch}</td>
                    <td>{investorBank.status}</td>
                    <td>{investorBank.bankId ? <Link to={`bank-my-suffix/${investorBank.bankId}`}>{investorBank.bankId}</Link> : ''}</td>
                    <td>
                      {investorBank.currencyId ? (
                        <Link to={`currency-my-suffix/${investorBank.currencyId}`}>{investorBank.currencyId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {investorBank.investorId ? (
                        <Link to={`investor-my-suffix/${investorBank.investorId}`}>{investorBank.investorId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${investorBank.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${investorBank.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${investorBank.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Investor Banks found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ investorBank }: IRootState) => ({
  investorBankList: investorBank.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorBankMySuffix);
