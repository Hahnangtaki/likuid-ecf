import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './fund-raiser-bank-my-suffix.reducer';
import { IFundRaiserBankMySuffix } from 'app/shared/model/fund-raiser-bank-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFundRaiserBankMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class FundRaiserBankMySuffix extends React.Component<IFundRaiserBankMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { fundRaiserBankList, match } = this.props;
    return (
      <div>
        <h2 id="fund-raiser-bank-my-suffix-heading">
          Fund Raiser Banks
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Fund Raiser Bank
          </Link>
        </h2>
        <div className="table-responsive">
          {fundRaiserBankList && fundRaiserBankList.length > 0 ? (
            <Table responsive aria-describedby="fund-raiser-bank-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Bank Account No</th>
                  <th>Bank Account Name</th>
                  <th>Bank Branch</th>
                  <th>Status</th>
                  <th>Fund Raiser</th>
                  <th>Bank</th>
                  <th>Currency</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {fundRaiserBankList.map((fundRaiserBank, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${fundRaiserBank.id}`} color="link" size="sm">
                        {fundRaiserBank.id}
                      </Button>
                    </td>
                    <td>{fundRaiserBank.bankAccountNo}</td>
                    <td>{fundRaiserBank.bankAccountName}</td>
                    <td>{fundRaiserBank.bankBranch}</td>
                    <td>{fundRaiserBank.status}</td>
                    <td>
                      {fundRaiserBank.fundRaiserId ? (
                        <Link to={`fund-raiser-my-suffix/${fundRaiserBank.fundRaiserId}`}>{fundRaiserBank.fundRaiserId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {fundRaiserBank.bankId ? <Link to={`bank-my-suffix/${fundRaiserBank.bankId}`}>{fundRaiserBank.bankId}</Link> : ''}
                    </td>
                    <td>
                      {fundRaiserBank.currencyId ? (
                        <Link to={`currency-my-suffix/${fundRaiserBank.currencyId}`}>{fundRaiserBank.currencyId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${fundRaiserBank.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${fundRaiserBank.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${fundRaiserBank.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Fund Raiser Banks found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ fundRaiserBank }: IRootState) => ({
  fundRaiserBankList: fundRaiserBank.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FundRaiserBankMySuffix);
