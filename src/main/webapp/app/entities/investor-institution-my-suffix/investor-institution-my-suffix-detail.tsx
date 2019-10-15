import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './investor-institution-my-suffix.reducer';
import { IInvestorInstitutionMySuffix } from 'app/shared/model/investor-institution-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorInstitutionMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class InvestorInstitutionMySuffixDetail extends React.Component<IInvestorInstitutionMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { investorInstitutionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            InvestorInstitution [<b>{investorInstitutionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="sid">Sid</span>
            </dt>
            <dd>{investorInstitutionEntity.sid}</dd>
            <dt>
              <span id="companyName">Company Name</span>
            </dt>
            <dd>{investorInstitutionEntity.companyName}</dd>
            <dt>
              <span id="bicCode">Bic Code</span>
            </dt>
            <dd>{investorInstitutionEntity.bicCode}</dd>
            <dt>
              <span id="legalDomicileId">Legal Domicile Id</span>
            </dt>
            <dd>{investorInstitutionEntity.legalDomicileId}</dd>
            <dt>
              <span id="npwp">Npwp</span>
            </dt>
            <dd>{investorInstitutionEntity.npwp}</dd>
            <dt>
              <span id="npwpRegDate">Npwp Reg Date</span>
            </dt>
            <dd>
              <TextFormat value={investorInstitutionEntity.npwpRegDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="skd">Skd</span>
            </dt>
            <dd>{investorInstitutionEntity.skd}</dd>
            <dt>
              <span id="skdExpDate">Skd Exp Date</span>
            </dt>
            <dd>
              <TextFormat value={investorInstitutionEntity.skdExpDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="companyEstablishPlce">Company Establish Plce</span>
            </dt>
            <dd>{investorInstitutionEntity.companyEstablishPlce}</dd>
            <dt>
              <span id="companyEstablishDate">Company Establish Date</span>
            </dt>
            <dd>
              <TextFormat value={investorInstitutionEntity.companyEstablishDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="businessType">Business Type</span>
            </dt>
            <dd>{investorInstitutionEntity.businessType}</dd>
            <dt>
              <span id="companyChracteristic">Company Chracteristic</span>
            </dt>
            <dd>{investorInstitutionEntity.companyChracteristic}</dd>
            <dt>
              <span id="fundSource">Fund Source</span>
            </dt>
            <dd>{investorInstitutionEntity.fundSource}</dd>
            <dt>
              <span id="fundSourceText">Fund Source Text</span>
            </dt>
            <dd>{investorInstitutionEntity.fundSourceText}</dd>
            <dt>
              <span id="articleAssociation">Article Association</span>
            </dt>
            <dd>{investorInstitutionEntity.articleAssociation}</dd>
            <dt>
              <span id="bussinessRegNo">Bussiness Reg No</span>
            </dt>
            <dd>{investorInstitutionEntity.bussinessRegNo}</dd>
            <dt>
              <span id="financialAsset1">Financial Asset 1</span>
            </dt>
            <dd>{investorInstitutionEntity.financialAsset1}</dd>
            <dt>
              <span id="financialAsset2">Financial Asset 2</span>
            </dt>
            <dd>{investorInstitutionEntity.financialAsset2}</dd>
            <dt>
              <span id="financialAsset3">Financial Asset 3</span>
            </dt>
            <dd>{investorInstitutionEntity.financialAsset3}</dd>
            <dt>
              <span id="operatingProfit1">Operating Profit 1</span>
            </dt>
            <dd>{investorInstitutionEntity.operatingProfit1}</dd>
            <dt>
              <span id="operatingProfit2">Operating Profit 2</span>
            </dt>
            <dd>{investorInstitutionEntity.operatingProfit2}</dd>
            <dt>
              <span id="operatingProfit3">Operating Profit 3</span>
            </dt>
            <dd>{investorInstitutionEntity.operatingProfit3}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{investorInstitutionEntity.description}</dd>
            <dt>
              <span id="investmentObjective">Investment Objective</span>
            </dt>
            <dd>{investorInstitutionEntity.investmentObjective}</dd>
            <dt>
              <span id="directSid">Direct Sid</span>
            </dt>
            <dd>{investorInstitutionEntity.directSid}</dd>
            <dt>
              <span id="assetOwner">Asset Owner</span>
            </dt>
            <dd>{investorInstitutionEntity.assetOwner}</dd>
            <dt>
              <span id="supDocType">Sup Doc Type</span>
            </dt>
            <dd>{investorInstitutionEntity.supDocType}</dd>
            <dt>
              <span id="supDocExpDate">Sup Doc Exp Date</span>
            </dt>
            <dd>
              <TextFormat value={investorInstitutionEntity.supDocExpDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>Tax</dt>
            <dd>{investorInstitutionEntity.taxId ? investorInstitutionEntity.taxId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/investor-institution-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/investor-institution-my-suffix/${investorInstitutionEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ investorInstitution }: IRootState) => ({
  investorInstitutionEntity: investorInstitution.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorInstitutionMySuffixDetail);
