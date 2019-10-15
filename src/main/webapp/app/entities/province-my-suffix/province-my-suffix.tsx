import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './province-my-suffix.reducer';
import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProvinceMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ProvinceMySuffix extends React.Component<IProvinceMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { provinceList, match } = this.props;
    return (
      <div>
        <h2 id="province-my-suffix-heading">
          Provinces
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Province
          </Link>
        </h2>
        <div className="table-responsive">
          {provinceList && provinceList.length > 0 ? (
            <Table responsive aria-describedby="province-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Province Code</th>
                  <th>Province Name</th>
                  <th>Country</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {provinceList.map((province, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${province.id}`} color="link" size="sm">
                        {province.id}
                      </Button>
                    </td>
                    <td>{province.provinceCode}</td>
                    <td>{province.provinceName}</td>
                    <td>{province.countryId ? <Link to={`country-my-suffix/${province.countryId}`}>{province.countryId}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${province.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${province.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${province.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Provinces found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ province }: IRootState) => ({
  provinceList: province.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProvinceMySuffix);
