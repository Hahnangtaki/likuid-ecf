import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './otp-history-my-suffix.reducer';
import { IOtpHistoryMySuffix } from 'app/shared/model/otp-history-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOtpHistoryMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class OtpHistoryMySuffixDetail extends React.Component<IOtpHistoryMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { otpHistoryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            OtpHistory [<b>{otpHistoryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="createdBy">Created By</span>
            </dt>
            <dd>{otpHistoryEntity.createdBy}</dd>
            <dt>
              <span id="createdDate">Created Date</span>
            </dt>
            <dd>
              <TextFormat value={otpHistoryEntity.createdDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="expiredDate">Expired Date</span>
            </dt>
            <dd>
              <TextFormat value={otpHistoryEntity.expiredDate} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="memberEmail">Member Email</span>
            </dt>
            <dd>{otpHistoryEntity.memberEmail}</dd>
            <dt>
              <span id="phoneNumber">Phone Number</span>
            </dt>
            <dd>{otpHistoryEntity.phoneNumber}</dd>
            <dt>
              <span id="procType">Proc Type</span>
            </dt>
            <dd>{otpHistoryEntity.procType}</dd>
            <dt>
              <span id="tokenText">Token Text</span>
            </dt>
            <dd>{otpHistoryEntity.tokenText}</dd>
            <dt>
              <span id="token">Token</span>
            </dt>
            <dd>{otpHistoryEntity.token}</dd>
            <dt>
              <span id="retryCount">Retry Count</span>
            </dt>
            <dd>{otpHistoryEntity.retryCount}</dd>
            <dt>
              <span id="retryMax">Retry Max</span>
            </dt>
            <dd>{otpHistoryEntity.retryMax}</dd>
            <dt>
              <span id="requestData">Request Data</span>
            </dt>
            <dd>
              {otpHistoryEntity.requestData ? (
                <div>
                  <a onClick={openFile(otpHistoryEntity.requestDataContentType, otpHistoryEntity.requestData)}>Open&nbsp;</a>
                  <span>
                    {otpHistoryEntity.requestDataContentType}, {byteSize(otpHistoryEntity.requestData)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>Member Account</dt>
            <dd>{otpHistoryEntity.memberAccountId ? otpHistoryEntity.memberAccountId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/otp-history-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/otp-history-my-suffix/${otpHistoryEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ otpHistory }: IRootState) => ({
  otpHistoryEntity: otpHistory.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OtpHistoryMySuffixDetail);
