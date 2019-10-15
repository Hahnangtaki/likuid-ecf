import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './member-account-my-suffix.reducer';
import { IMemberAccountMySuffix } from 'app/shared/model/member-account-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMemberAccountMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IMemberAccountMySuffixUpdateState {
  isNew: boolean;
}

export class MemberAccountMySuffixUpdate extends React.Component<IMemberAccountMySuffixUpdateProps, IMemberAccountMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { memberAccountEntity } = this.props;
      const entity = {
        ...memberAccountEntity,
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
    this.props.history.push('/entity/member-account-my-suffix');
  };

  render() {
    const { memberAccountEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.memberAccount.home.createOrEditLabel">Create or edit a MemberAccount</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : memberAccountEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="member-account-my-suffix-id">ID</Label>
                    <AvInput id="member-account-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="memberEmailLabel" for="member-account-my-suffix-memberEmail">
                    Member Email
                  </Label>
                  <AvField id="member-account-my-suffix-memberEmail" type="text" name="memberEmail" />
                </AvGroup>
                <AvGroup>
                  <Label id="phoneNumberLabel" for="member-account-my-suffix-phoneNumber">
                    Phone Number
                  </Label>
                  <AvField id="member-account-my-suffix-phoneNumber" type="text" name="phoneNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="memberSinceLabel" for="member-account-my-suffix-memberSince">
                    Member Since
                  </Label>
                  <AvField id="member-account-my-suffix-memberSince" type="date" className="form-control" name="memberSince" />
                </AvGroup>
                <AvGroup>
                  <Label id="memberStatusLabel" for="member-account-my-suffix-memberStatus">
                    Member Status
                  </Label>
                  <AvField id="member-account-my-suffix-memberStatus" type="string" className="form-control" name="memberStatus" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/member-account-my-suffix" replace color="info">
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
  memberAccountEntity: storeState.memberAccount.entity,
  loading: storeState.memberAccount.loading,
  updating: storeState.memberAccount.updating,
  updateSuccess: storeState.memberAccount.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(MemberAccountMySuffixUpdate);
