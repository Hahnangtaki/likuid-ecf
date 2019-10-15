import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './investor-individu-my-suffix.reducer';
import { IInvestorIndividuMySuffix } from 'app/shared/model/investor-individu-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorIndividuMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class InvestorIndividuMySuffix extends React.Component<IInvestorIndividuMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { investorIndividuList, match } = this.props;
    return (
      <div>
        <h2 id="investor-individu-my-suffix-heading">
          Investor Individus
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Investor Individu
          </Link>
        </h2>
        <div className="table-responsive">
          {investorIndividuList && investorIndividuList.length > 0 ? (
            <Table responsive aria-describedby="investor-individu-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Sid</th>
                  <th>First Name</th>
                  <th>Middle Name</th>
                  <th>Last Name</th>
                  <th>Nationality Id</th>
                  <th>Ktp</th>
                  <th>Ktp Exp Date</th>
                  <th>Npwp</th>
                  <th>Npwp Reg Date</th>
                  <th>Passport</th>
                  <th>Passport Exp Date</th>
                  <th>Kitas</th>
                  <th>Kitas Exp Date</th>
                  <th>Birth Place</th>
                  <th>Birth Date</th>
                  <th>Sex</th>
                  <th>Marital Status</th>
                  <th>Spouse Name</th>
                  <th>Heir</th>
                  <th>Heir Ralation</th>
                  <th>Education Background</th>
                  <th>Occupation</th>
                  <th>Nature Of Business</th>
                  <th>Annual Income</th>
                  <th>Fund Source</th>
                  <th>Fund Source Text</th>
                  <th>Description</th>
                  <th>Investment Objective</th>
                  <th>Mother Maiden Name</th>
                  <th>Direct Sid</th>
                  <th>Asset Owner</th>
                  <th>Auth Person Ktp Reg Date</th>
                  <th>Tax</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {investorIndividuList.map((investorIndividu, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${investorIndividu.id}`} color="link" size="sm">
                        {investorIndividu.id}
                      </Button>
                    </td>
                    <td>{investorIndividu.sid}</td>
                    <td>{investorIndividu.firstName}</td>
                    <td>{investorIndividu.middleName}</td>
                    <td>{investorIndividu.lastName}</td>
                    <td>{investorIndividu.nationalityId}</td>
                    <td>{investorIndividu.ktp}</td>
                    <td>
                      <TextFormat type="date" value={investorIndividu.ktpExpDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{investorIndividu.npwp}</td>
                    <td>
                      <TextFormat type="date" value={investorIndividu.npwpRegDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{investorIndividu.passport}</td>
                    <td>
                      <TextFormat type="date" value={investorIndividu.passportExpDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{investorIndividu.kitas}</td>
                    <td>
                      <TextFormat type="date" value={investorIndividu.kitasExpDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{investorIndividu.birthPlace}</td>
                    <td>
                      <TextFormat type="date" value={investorIndividu.birthDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{investorIndividu.sex}</td>
                    <td>{investorIndividu.maritalStatus}</td>
                    <td>{investorIndividu.spouseName}</td>
                    <td>{investorIndividu.heir}</td>
                    <td>{investorIndividu.heirRalation}</td>
                    <td>{investorIndividu.educationBackground}</td>
                    <td>{investorIndividu.occupation}</td>
                    <td>{investorIndividu.natureOfBusiness}</td>
                    <td>{investorIndividu.annualIncome}</td>
                    <td>{investorIndividu.fundSource}</td>
                    <td>{investorIndividu.fundSourceText}</td>
                    <td>{investorIndividu.description}</td>
                    <td>{investorIndividu.investmentObjective}</td>
                    <td>{investorIndividu.motherMaidenName}</td>
                    <td>{investorIndividu.directSid}</td>
                    <td>{investorIndividu.assetOwner}</td>
                    <td>
                      <TextFormat type="date" value={investorIndividu.authPersonKtpRegDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>
                      {investorIndividu.taxId ? <Link to={`tax-my-suffix/${investorIndividu.taxId}`}>{investorIndividu.taxId}</Link> : ''}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${investorIndividu.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${investorIndividu.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${investorIndividu.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Investor Individus found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ investorIndividu }: IRootState) => ({
  investorIndividuList: investorIndividu.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorIndividuMySuffix);
