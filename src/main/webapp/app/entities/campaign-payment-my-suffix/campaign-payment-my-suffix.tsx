import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './campaign-payment-my-suffix.reducer';
import { ICampaignPaymentMySuffix } from 'app/shared/model/campaign-payment-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICampaignPaymentMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class CampaignPaymentMySuffix extends React.Component<ICampaignPaymentMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { campaignPaymentList, match } = this.props;
    return (
      <div>
        <h2 id="campaign-payment-my-suffix-heading">
          Campaign Payments
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Campaign Payment
          </Link>
        </h2>
        <div className="table-responsive">
          {campaignPaymentList && campaignPaymentList.length > 0 ? (
            <Table responsive aria-describedby="campaign-payment-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Payment Code</th>
                  <th>Payment Desc</th>
                  <th>Amount</th>
                  <th>Status</th>
                  <th>Fund Raiser Bank</th>
                  <th>Campaign</th>
                  <th>Company Bank</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {campaignPaymentList.map((campaignPayment, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${campaignPayment.id}`} color="link" size="sm">
                        {campaignPayment.id}
                      </Button>
                    </td>
                    <td>{campaignPayment.paymentCode}</td>
                    <td>{campaignPayment.paymentDesc}</td>
                    <td>{campaignPayment.amount}</td>
                    <td>{campaignPayment.status}</td>
                    <td>
                      {campaignPayment.fundRaiserBankId ? (
                        <Link to={`fund-raiser-bank-my-suffix/${campaignPayment.fundRaiserBankId}`}>
                          {campaignPayment.fundRaiserBankId}
                        </Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {campaignPayment.campaignId ? (
                        <Link to={`campaign-my-suffix/${campaignPayment.campaignId}`}>{campaignPayment.campaignId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {campaignPayment.companyBankId ? (
                        <Link to={`company-bank-my-suffix/${campaignPayment.companyBankId}`}>{campaignPayment.companyBankId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${campaignPayment.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${campaignPayment.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${campaignPayment.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Campaign Payments found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ campaignPayment }: IRootState) => ({
  campaignPaymentList: campaignPayment.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignPaymentMySuffix);
