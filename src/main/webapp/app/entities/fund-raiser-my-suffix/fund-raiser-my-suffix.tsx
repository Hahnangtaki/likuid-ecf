import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './fund-raiser-my-suffix.reducer';
import { IFundRaiserMySuffix } from 'app/shared/model/fund-raiser-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFundRaiserMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class FundRaiserMySuffix extends React.Component<IFundRaiserMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { fundRaiserList, match } = this.props;
    return (
      <div>
        <h2 id="fund-raiser-my-suffix-heading">
          Fund Raisers
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Fund Raiser
          </Link>
        </h2>
        <div className="table-responsive">
          {fundRaiserList && fundRaiserList.length > 0 ? (
            <Table responsive aria-describedby="fund-raiser-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Company Name</th>
                  <th>Year Founded</th>
                  <th>Website</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {fundRaiserList.map((fundRaiser, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${fundRaiser.id}`} color="link" size="sm">
                        {fundRaiser.id}
                      </Button>
                    </td>
                    <td>{fundRaiser.companyName}</td>
                    <td>{fundRaiser.yearFounded}</td>
                    <td>{fundRaiser.website}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${fundRaiser.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${fundRaiser.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${fundRaiser.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Fund Raisers found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ fundRaiser }: IRootState) => ({
  fundRaiserList: fundRaiser.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FundRaiserMySuffix);
