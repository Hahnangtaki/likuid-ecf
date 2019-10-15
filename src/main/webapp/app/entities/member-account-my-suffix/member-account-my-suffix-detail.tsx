import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './member-account-my-suffix.reducer';
import { IMemberAccountMySuffix } from 'app/shared/model/member-account-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMemberAccountMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class MemberAccountMySuffixDetail extends React.Component<IMemberAccountMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { memberAccountEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            MemberAccount [<b>{memberAccountEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="memberEmail">Member Email</span>
            </dt>
            <dd>{memberAccountEntity.memberEmail}</dd>
            <dt>
              <span id="phoneNumber">Phone Number</span>
            </dt>
            <dd>{memberAccountEntity.phoneNumber}</dd>
            <dt>
              <span id="memberSince">Member Since</span>
            </dt>
            <dd>
              <TextFormat value={memberAccountEntity.memberSince} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="memberStatus">Member Status</span>
            </dt>
            <dd>{memberAccountEntity.memberStatus}</dd>
          </dl>
          <Button tag={Link} to="/entity/member-account-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/member-account-my-suffix/${memberAccountEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ memberAccount }: IRootState) => ({
  memberAccountEntity: memberAccount.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(MemberAccountMySuffixDetail);
