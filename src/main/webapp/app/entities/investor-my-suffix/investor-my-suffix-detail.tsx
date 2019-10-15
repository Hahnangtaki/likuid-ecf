import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './investor-my-suffix.reducer';
import { IInvestorMySuffix } from 'app/shared/model/investor-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class InvestorMySuffixDetail extends React.Component<IInvestorMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { investorEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Investor [<b>{investorEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="investorCode">Investor Code</span>
            </dt>
            <dd>{investorEntity.investorCode}</dd>
            <dt>
              <span id="investorName">Investor Name</span>
            </dt>
            <dd>{investorEntity.investorName}</dd>
            <dt>
              <span id="investorType">Investor Type</span>
            </dt>
            <dd>{investorEntity.investorType}</dd>
            <dt>Investor Institution</dt>
            <dd>{investorEntity.investorInstitutionId ? investorEntity.investorInstitutionId : ''}</dd>
            <dt>Investor Individu</dt>
            <dd>{investorEntity.investorIndividuId ? investorEntity.investorIndividuId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/investor-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/investor-my-suffix/${investorEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ investor }: IRootState) => ({
  investorEntity: investor.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorMySuffixDetail);
