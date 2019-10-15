import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './investor-bank-my-suffix.reducer';
import { IInvestorBankMySuffix } from 'app/shared/model/investor-bank-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvestorBankMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class InvestorBankMySuffixDetail extends React.Component<IInvestorBankMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { investorBankEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            InvestorBank [<b>{investorBankEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="bankAccountNo">Bank Account No</span>
            </dt>
            <dd>{investorBankEntity.bankAccountNo}</dd>
            <dt>
              <span id="bankAccountName">Bank Account Name</span>
            </dt>
            <dd>{investorBankEntity.bankAccountName}</dd>
            <dt>
              <span id="bankBranch">Bank Branch</span>
            </dt>
            <dd>{investorBankEntity.bankBranch}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{investorBankEntity.status}</dd>
            <dt>Bank</dt>
            <dd>{investorBankEntity.bankId ? investorBankEntity.bankId : ''}</dd>
            <dt>Currency</dt>
            <dd>{investorBankEntity.currencyId ? investorBankEntity.currencyId : ''}</dd>
            <dt>Investor</dt>
            <dd>{investorBankEntity.investorId ? investorBankEntity.investorId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/investor-bank-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/investor-bank-my-suffix/${investorBankEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ investorBank }: IRootState) => ({
  investorBankEntity: investorBank.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvestorBankMySuffixDetail);
