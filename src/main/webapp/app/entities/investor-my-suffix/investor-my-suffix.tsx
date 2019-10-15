import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './investor-my-suffix.reducer';
import { IInvestorMySuffix } from 'app/shared/model/investor-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class InvestorMySuffix extends React.Component<IInvestorMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { investorList, match } = this.props;
    return (
      <div>
        <h2 id="investor-my-suffix-heading">
          Investors
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Investor
          </Link>
        </h2>
        <div className="table-responsive">
          {investorList && investorList.length > 0 ? (
            <Table responsive aria-describedby="investor-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Investor Code</th>
                  <th>Investor Name</th>
                  <th>Investor Type</th>
                  <th>Investor Institution</th>
                  <th>Investor Individu</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {investorList.map((investor, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${investor.id}`} color="link" size="sm">
                        {investor.id}
                      </Button>
                    </td>
                    <td>{investor.investorCode}</td>
                    <td>{investor.investorName}</td>
                    <td>{investor.investorType}</td>
                    <td>
                      {investor.investorInstitutionId ? (
                        <Link to={`investor-institution-my-suffix/${investor.investorInstitutionId}`}>
                          {investor.investorInstitutionId}
                        </Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {investor.investorIndividuId ? (
                        <Link to={`investor-individu-my-suffix/${investor.investorIndividuId}`}>{investor.investorIndividuId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${investor.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${investor.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${investor.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Investors found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ investor }: IRootState) => ({
  investorList: investor.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorMySuffix);
