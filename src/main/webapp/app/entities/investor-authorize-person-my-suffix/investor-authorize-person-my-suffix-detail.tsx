import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './investor-authorize-person-my-suffix.reducer';
import { IInvestorAuthorizePersonMySuffix } from 'app/shared/model/investor-authorize-person-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorAuthorizePersonMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class InvestorAuthorizePersonMySuffixDetail extends React.Component<IInvestorAuthorizePersonMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { investorAuthorizePersonEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            InvestorAuthorizePerson [<b>{investorAuthorizePersonEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="firstName">First Name</span>
            </dt>
            <dd>{investorAuthorizePersonEntity.firstName}</dd>
            <dt>
              <span id="middleName">Middle Name</span>
            </dt>
            <dd>{investorAuthorizePersonEntity.middleName}</dd>
            <dt>
              <span id="lastName">Last Name</span>
            </dt>
            <dd>{investorAuthorizePersonEntity.lastName}</dd>
            <dt>
              <span id="position">Position</span>
            </dt>
            <dd>{investorAuthorizePersonEntity.position}</dd>
            <dt>
              <span id="ktp">Ktp</span>
            </dt>
            <dd>{investorAuthorizePersonEntity.ktp}</dd>
            <dt>
              <span id="ktpExpDate">Ktp Exp Date</span>
            </dt>
            <dd>
              <TextFormat value={investorAuthorizePersonEntity.ktpExpDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="npwp">Npwp</span>
            </dt>
            <dd>{investorAuthorizePersonEntity.npwp}</dd>
            <dt>
              <span id="npwpRegDate">Npwp Reg Date</span>
            </dt>
            <dd>
              <TextFormat value={investorAuthorizePersonEntity.npwpRegDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="passport">Passport</span>
            </dt>
            <dd>{investorAuthorizePersonEntity.passport}</dd>
            <dt>
              <span id="passportExpDate">Passport Exp Date</span>
            </dt>
            <dd>
              <TextFormat value={investorAuthorizePersonEntity.passportExpDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="kitas">Kitas</span>
            </dt>
            <dd>{investorAuthorizePersonEntity.kitas}</dd>
            <dt>
              <span id="kitasExpDate">Kitas Exp Date</span>
            </dt>
            <dd>
              <TextFormat value={investorAuthorizePersonEntity.kitasExpDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>Investor</dt>
            <dd>{investorAuthorizePersonEntity.investorId ? investorAuthorizePersonEntity.investorId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/investor-authorize-person-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button
            tag={Link}
            to={`/entity/investor-authorize-person-my-suffix/${investorAuthorizePersonEntity.id}/edit`}
            replace
            color="primary"
          >
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ investorAuthorizePerson }: IRootState) => ({
  investorAuthorizePersonEntity: investorAuthorizePerson.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorAuthorizePersonMySuffixDetail);
