import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './member-account-my-suffix.reducer';
import { IMemberAccountMySuffix } from 'app/shared/model/member-account-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMemberAccountMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class MemberAccountMySuffix extends React.Component<IMemberAccountMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { memberAccountList, match } = this.props;
    return (
      <div>
        <h2 id="member-account-my-suffix-heading">
          Member Accounts
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Member Account
          </Link>
        </h2>
        <div className="table-responsive">
          {memberAccountList && memberAccountList.length > 0 ? (
            <Table responsive aria-describedby="member-account-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Member Email</th>
                  <th>Phone Number</th>
                  <th>Member Since</th>
                  <th>Member Status</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {memberAccountList.map((memberAccount, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${memberAccount.id}`} color="link" size="sm">
                        {memberAccount.id}
                      </Button>
                    </td>
                    <td>{memberAccount.memberEmail}</td>
                    <td>{memberAccount.phoneNumber}</td>
                    <td>
                      <TextFormat type="date" value={memberAccount.memberSince} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{memberAccount.memberStatus}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${memberAccount.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${memberAccount.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${memberAccount.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Member Accounts found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ memberAccount }: IRootState) => ({
  memberAccountList: memberAccount.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(MemberAccountMySuffix);
