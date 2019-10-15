import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './investor-institution-my-suffix.reducer';
import { IInvestorInstitutionMySuffix } from 'app/shared/model/investor-institution-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorInstitutionMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class InvestorInstitutionMySuffix extends React.Component<IInvestorInstitutionMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { investorInstitutionList, match } = this.props;
    return (
      <div>
        <h2 id="investor-institution-my-suffix-heading">
          Investor Institutions
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Investor Institution
          </Link>
        </h2>
        <div className="table-responsive">
          {investorInstitutionList && investorInstitutionList.length > 0 ? (
            <Table responsive aria-describedby="investor-institution-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Sid</th>
                  <th>Company Name</th>
                  <th>Bic Code</th>
                  <th>Legal Domicile Id</th>
                  <th>Npwp</th>
                  <th>Npwp Reg Date</th>
                  <th>Skd</th>
                  <th>Skd Exp Date</th>
                  <th>Company Establish Plce</th>
                  <th>Company Establish Date</th>
                  <th>Business Type</th>
                  <th>Company Chracteristic</th>
                  <th>Fund Source</th>
                  <th>Fund Source Text</th>
                  <th>Article Association</th>
                  <th>Bussiness Reg No</th>
                  <th>Financial Asset 1</th>
                  <th>Financial Asset 2</th>
                  <th>Financial Asset 3</th>
                  <th>Operating Profit 1</th>
                  <th>Operating Profit 2</th>
                  <th>Operating Profit 3</th>
                  <th>Description</th>
                  <th>Investment Objective</th>
                  <th>Direct Sid</th>
                  <th>Asset Owner</th>
                  <th>Sup Doc Type</th>
                  <th>Sup Doc Exp Date</th>
                  <th>Tax</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {investorInstitutionList.map((investorInstitution, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${investorInstitution.id}`} color="link" size="sm">
                        {investorInstitution.id}
                      </Button>
                    </td>
                    <td>{investorInstitution.sid}</td>
                    <td>{investorInstitution.companyName}</td>
                    <td>{investorInstitution.bicCode}</td>
                    <td>{investorInstitution.legalDomicileId}</td>
                    <td>{investorInstitution.npwp}</td>
                    <td>
                      <TextFormat type="date" value={investorInstitution.npwpRegDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{investorInstitution.skd}</td>
                    <td>
                      <TextFormat type="date" value={investorInstitution.skdExpDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{investorInstitution.companyEstablishPlce}</td>
                    <td>
                      <TextFormat type="date" value={investorInstitution.companyEstablishDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{investorInstitution.businessType}</td>
                    <td>{investorInstitution.companyChracteristic}</td>
                    <td>{investorInstitution.fundSource}</td>
                    <td>{investorInstitution.fundSourceText}</td>
                    <td>{investorInstitution.articleAssociation}</td>
                    <td>{investorInstitution.bussinessRegNo}</td>
                    <td>{investorInstitution.financialAsset1}</td>
                    <td>{investorInstitution.financialAsset2}</td>
                    <td>{investorInstitution.financialAsset3}</td>
                    <td>{investorInstitution.operatingProfit1}</td>
                    <td>{investorInstitution.operatingProfit2}</td>
                    <td>{investorInstitution.operatingProfit3}</td>
                    <td>{investorInstitution.description}</td>
                    <td>{investorInstitution.investmentObjective}</td>
                    <td>{investorInstitution.directSid}</td>
                    <td>{investorInstitution.assetOwner}</td>
                    <td>{investorInstitution.supDocType}</td>
                    <td>
                      <TextFormat type="date" value={investorInstitution.supDocExpDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>
                      {investorInstitution.taxId ? (
                        <Link to={`tax-my-suffix/${investorInstitution.taxId}`}>{investorInstitution.taxId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${investorInstitution.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${investorInstitution.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${investorInstitution.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Investor Institutions found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ investorInstitution }: IRootState) => ({
  investorInstitutionList: investorInstitution.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorInstitutionMySuffix);
