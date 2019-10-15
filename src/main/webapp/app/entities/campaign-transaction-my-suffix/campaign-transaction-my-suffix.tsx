import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './campaign-transaction-my-suffix.reducer';
import { ICampaignTransactionMySuffix } from 'app/shared/model/campaign-transaction-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICampaignTransactionMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class CampaignTransactionMySuffix extends React.Component<ICampaignTransactionMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { campaignTransactionList, match } = this.props;
    return (
      <div>
        <h2 id="campaign-transaction-my-suffix-heading">
          Campaign Transactions
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Campaign Transaction
          </Link>
        </h2>
        <div className="table-responsive">
          {campaignTransactionList && campaignTransactionList.length > 0 ? (
            <Table responsive aria-describedby="campaign-transaction-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Trans Code</th>
                  <th>Trans Desc</th>
                  <th>Trans Date</th>
                  <th>Trans Type</th>
                  <th>Qty</th>
                  <th>Amount</th>
                  <th>Unique Charges</th>
                  <th>Net Amount</th>
                  <th>Status</th>
                  <th>Investor</th>
                  <th>Campaign</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {campaignTransactionList.map((campaignTransaction, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${campaignTransaction.id}`} color="link" size="sm">
                        {campaignTransaction.id}
                      </Button>
                    </td>
                    <td>{campaignTransaction.transCode}</td>
                    <td>{campaignTransaction.transDesc}</td>
                    <td>
                      <TextFormat type="date" value={campaignTransaction.transDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{campaignTransaction.transType}</td>
                    <td>{campaignTransaction.qty}</td>
                    <td>{campaignTransaction.amount}</td>
                    <td>{campaignTransaction.uniqueCharges}</td>
                    <td>{campaignTransaction.netAmount}</td>
                    <td>{campaignTransaction.status}</td>
                    <td>
                      {campaignTransaction.investorId ? (
                        <Link to={`investor-my-suffix/${campaignTransaction.investorId}`}>{campaignTransaction.investorId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {campaignTransaction.campaignId ? (
                        <Link to={`campaign-my-suffix/${campaignTransaction.campaignId}`}>{campaignTransaction.campaignId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${campaignTransaction.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${campaignTransaction.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${campaignTransaction.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Campaign Transactions found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ campaignTransaction }: IRootState) => ({
  campaignTransactionList: campaignTransaction.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignTransactionMySuffix);
