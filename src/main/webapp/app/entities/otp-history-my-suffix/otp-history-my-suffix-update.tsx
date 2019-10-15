import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IMemberAccountMySuffix } from 'app/shared/model/member-account-my-suffix.model';
import { getEntities as getMemberAccounts } from 'app/entities/member-account-my-suffix/member-account-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './otp-history-my-suffix.reducer';
import { IOtpHistoryMySuffix } from 'app/shared/model/otp-history-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOtpHistoryMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IOtpHistoryMySuffixUpdateState {
  isNew: boolean;
  memberAccountId: string;
}

export class OtpHistoryMySuffixUpdate extends React.Component<IOtpHistoryMySuffixUpdateProps, IOtpHistoryMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      memberAccountId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getMemberAccounts();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.expiredDate = convertDateTimeToServer(values.expiredDate);

    if (errors.length === 0) {
      const { otpHistoryEntity } = this.props;
      const entity = {
        ...otpHistoryEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/otp-history-my-suffix');
  };

  render() {
    const { otpHistoryEntity, memberAccounts, loading, updating } = this.props;
    const { isNew } = this.state;

    const { requestData, requestDataContentType } = otpHistoryEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.otpHistory.home.createOrEditLabel">Create or edit a OtpHistory</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : otpHistoryEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="otp-history-my-suffix-id">ID</Label>
                    <AvInput id="otp-history-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="createdByLabel" for="otp-history-my-suffix-createdBy">
                    Created By
                  </Label>
                  <AvField id="otp-history-my-suffix-createdBy" type="string" className="form-control" name="createdBy" />
                </AvGroup>
                <AvGroup>
                  <Label id="createdDateLabel" for="otp-history-my-suffix-createdDate">
                    Created Date
                  </Label>
                  <AvInput
                    id="otp-history-my-suffix-createdDate"
                    type="datetime-local"
                    className="form-control"
                    name="createdDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.otpHistoryEntity.createdDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="expiredDateLabel" for="otp-history-my-suffix-expiredDate">
                    Expired Date
                  </Label>
                  <AvInput
                    id="otp-history-my-suffix-expiredDate"
                    type="datetime-local"
                    className="form-control"
                    name="expiredDate"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.otpHistoryEntity.expiredDate)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="memberEmailLabel" for="otp-history-my-suffix-memberEmail">
                    Member Email
                  </Label>
                  <AvField id="otp-history-my-suffix-memberEmail" type="text" name="memberEmail" />
                </AvGroup>
                <AvGroup>
                  <Label id="phoneNumberLabel" for="otp-history-my-suffix-phoneNumber">
                    Phone Number
                  </Label>
                  <AvField id="otp-history-my-suffix-phoneNumber" type="text" name="phoneNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="procTypeLabel" for="otp-history-my-suffix-procType">
                    Proc Type
                  </Label>
                  <AvField id="otp-history-my-suffix-procType" type="string" className="form-control" name="procType" />
                </AvGroup>
                <AvGroup>
                  <Label id="tokenTextLabel" for="otp-history-my-suffix-tokenText">
                    Token Text
                  </Label>
                  <AvField id="otp-history-my-suffix-tokenText" type="text" name="tokenText" />
                </AvGroup>
                <AvGroup>
                  <Label id="tokenLabel" for="otp-history-my-suffix-token">
                    Token
                  </Label>
                  <AvField id="otp-history-my-suffix-token" type="text" name="token" />
                </AvGroup>
                <AvGroup>
                  <Label id="retryCountLabel" for="otp-history-my-suffix-retryCount">
                    Retry Count
                  </Label>
                  <AvField id="otp-history-my-suffix-retryCount" type="string" className="form-control" name="retryCount" />
                </AvGroup>
                <AvGroup>
                  <Label id="retryMaxLabel" for="otp-history-my-suffix-retryMax">
                    Retry Max
                  </Label>
                  <AvField id="otp-history-my-suffix-retryMax" type="string" className="form-control" name="retryMax" />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="requestDataLabel" for="requestData">
                      Request Data
                    </Label>
                    <br />
                    {requestData ? (
                      <div>
                        <a onClick={openFile(requestDataContentType, requestData)}>Open</a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {requestDataContentType}, {byteSize(requestData)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('requestData')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_requestData" type="file" onChange={this.onBlobChange(false, 'requestData')} />
                    <AvInput type="hidden" name="requestData" value={requestData} />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label for="otp-history-my-suffix-memberAccount">Member Account</Label>
                  <AvInput id="otp-history-my-suffix-memberAccount" type="select" className="form-control" name="memberAccountId">
                    <option value="" key="0" />
                    {memberAccounts
                      ? memberAccounts.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/otp-history-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  memberAccounts: storeState.memberAccount.entities,
  otpHistoryEntity: storeState.otpHistory.entity,
  loading: storeState.otpHistory.loading,
  updating: storeState.otpHistory.updating,
  updateSuccess: storeState.otpHistory.updateSuccess
});

const mapDispatchToProps = {
  getMemberAccounts,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OtpHistoryMySuffixUpdate);
