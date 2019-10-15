import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './otp-history-my-suffix.reducer';
import { IOtpHistoryMySuffix } from 'app/shared/model/otp-history-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOtpHistoryMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class OtpHistoryMySuffix extends React.Component<IOtpHistoryMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { otpHistoryList, match } = this.props;
    return (
      <div>
        <h2 id="otp-history-my-suffix-heading">
          Otp Histories
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Otp History
          </Link>
        </h2>
        <div className="table-responsive">
          {otpHistoryList && otpHistoryList.length > 0 ? (
            <Table responsive aria-describedby="otp-history-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Created By</th>
                  <th>Created Date</th>
                  <th>Expired Date</th>
                  <th>Member Email</th>
                  <th>Phone Number</th>
                  <th>Proc Type</th>
                  <th>Token Text</th>
                  <th>Token</th>
                  <th>Retry Count</th>
                  <th>Retry Max</th>
                  <th>Request Data</th>
                  <th>Member Account</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {otpHistoryList.map((otpHistory, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${otpHistory.id}`} color="link" size="sm">
                        {otpHistory.id}
                      </Button>
                    </td>
                    <td>{otpHistory.createdBy}</td>
                    <td>
                      <TextFormat type="date" value={otpHistory.createdDate} format={APP_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={otpHistory.expiredDate} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{otpHistory.memberEmail}</td>
                    <td>{otpHistory.phoneNumber}</td>
                    <td>{otpHistory.procType}</td>
                    <td>{otpHistory.tokenText}</td>
                    <td>{otpHistory.token}</td>
                    <td>{otpHistory.retryCount}</td>
                    <td>{otpHistory.retryMax}</td>
                    <td>
                      {otpHistory.requestData ? (
                        <div>
                          <a onClick={openFile(otpHistory.requestDataContentType, otpHistory.requestData)}>Open &nbsp;</a>
                          <span>
                            {otpHistory.requestDataContentType}, {byteSize(otpHistory.requestData)}
                          </span>
                        </div>
                      ) : null}
                    </td>
                    <td>
                      {otpHistory.memberAccountId ? (
                        <Link to={`member-account-my-suffix/${otpHistory.memberAccountId}`}>{otpHistory.memberAccountId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${otpHistory.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${otpHistory.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${otpHistory.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Otp Histories found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ otpHistory }: IRootState) => ({
  otpHistoryList: otpHistory.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OtpHistoryMySuffix);
