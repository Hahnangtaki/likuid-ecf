import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './investor-individu-my-suffix.reducer';
import { IInvestorIndividuMySuffix } from 'app/shared/model/investor-individu-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorIndividuMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class InvestorIndividuMySuffixDetail extends React.Component<IInvestorIndividuMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { investorIndividuEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            InvestorIndividu [<b>{investorIndividuEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="sid">Sid</span>
            </dt>
            <dd>{investorIndividuEntity.sid}</dd>
            <dt>
              <span id="firstName">First Name</span>
            </dt>
            <dd>{investorIndividuEntity.firstName}</dd>
            <dt>
              <span id="middleName">Middle Name</span>
            </dt>
            <dd>{investorIndividuEntity.middleName}</dd>
            <dt>
              <span id="lastName">Last Name</span>
            </dt>
            <dd>{investorIndividuEntity.lastName}</dd>
            <dt>
              <span id="nationalityId">Nationality Id</span>
            </dt>
            <dd>{investorIndividuEntity.nationalityId}</dd>
            <dt>
              <span id="ktp">Ktp</span>
            </dt>
            <dd>{investorIndividuEntity.ktp}</dd>
            <dt>
              <span id="ktpExpDate">Ktp Exp Date</span>
            </dt>
            <dd>
              <TextFormat value={investorIndividuEntity.ktpExpDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="npwp">Npwp</span>
            </dt>
            <dd>{investorIndividuEntity.npwp}</dd>
            <dt>
              <span id="npwpRegDate">Npwp Reg Date</span>
            </dt>
            <dd>
              <TextFormat value={investorIndividuEntity.npwpRegDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="passport">Passport</span>
            </dt>
            <dd>{investorIndividuEntity.passport}</dd>
            <dt>
              <span id="passportExpDate">Passport Exp Date</span>
            </dt>
            <dd>
              <TextFormat value={investorIndividuEntity.passportExpDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="kitas">Kitas</span>
            </dt>
            <dd>{investorIndividuEntity.kitas}</dd>
            <dt>
              <span id="kitasExpDate">Kitas Exp Date</span>
            </dt>
            <dd>
              <TextFormat value={investorIndividuEntity.kitasExpDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="birthPlace">Birth Place</span>
            </dt>
            <dd>{investorIndividuEntity.birthPlace}</dd>
            <dt>
              <span id="birthDate">Birth Date</span>
            </dt>
            <dd>
              <TextFormat value={investorIndividuEntity.birthDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="sex">Sex</span>
            </dt>
            <dd>{investorIndividuEntity.sex}</dd>
            <dt>
              <span id="maritalStatus">Marital Status</span>
            </dt>
            <dd>{investorIndividuEntity.maritalStatus}</dd>
            <dt>
              <span id="spouseName">Spouse Name</span>
            </dt>
            <dd>{investorIndividuEntity.spouseName}</dd>
            <dt>
              <span id="heir">Heir</span>
            </dt>
            <dd>{investorIndividuEntity.heir}</dd>
            <dt>
              <span id="heirRalation">Heir Ralation</span>
            </dt>
            <dd>{investorIndividuEntity.heirRalation}</dd>
            <dt>
              <span id="educationBackground">Education Background</span>
            </dt>
            <dd>{investorIndividuEntity.educationBackground}</dd>
            <dt>
              <span id="occupation">Occupation</span>
            </dt>
            <dd>{investorIndividuEntity.occupation}</dd>
            <dt>
              <span id="natureOfBusiness">Nature Of Business</span>
            </dt>
            <dd>{investorIndividuEntity.natureOfBusiness}</dd>
            <dt>
              <span id="annualIncome">Annual Income</span>
            </dt>
            <dd>{investorIndividuEntity.annualIncome}</dd>
            <dt>
              <span id="fundSource">Fund Source</span>
            </dt>
            <dd>{investorIndividuEntity.fundSource}</dd>
            <dt>
              <span id="fundSourceText">Fund Source Text</span>
            </dt>
            <dd>{investorIndividuEntity.fundSourceText}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{investorIndividuEntity.description}</dd>
            <dt>
              <span id="investmentObjective">Investment Objective</span>
            </dt>
            <dd>{investorIndividuEntity.investmentObjective}</dd>
            <dt>
              <span id="motherMaidenName">Mother Maiden Name</span>
            </dt>
            <dd>{investorIndividuEntity.motherMaidenName}</dd>
            <dt>
              <span id="directSid">Direct Sid</span>
            </dt>
            <dd>{investorIndividuEntity.directSid}</dd>
            <dt>
              <span id="assetOwner">Asset Owner</span>
            </dt>
            <dd>{investorIndividuEntity.assetOwner}</dd>
            <dt>
              <span id="authPersonKtpRegDate">Auth Person Ktp Reg Date</span>
            </dt>
            <dd>
              <TextFormat value={investorIndividuEntity.authPersonKtpRegDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>Tax</dt>
            <dd>{investorIndividuEntity.taxId ? investorIndividuEntity.taxId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/investor-individu-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/investor-individu-my-suffix/${investorIndividuEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ investorIndividu }: IRootState) => ({
  investorIndividuEntity: investorIndividu.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorIndividuMySuffixDetail);
