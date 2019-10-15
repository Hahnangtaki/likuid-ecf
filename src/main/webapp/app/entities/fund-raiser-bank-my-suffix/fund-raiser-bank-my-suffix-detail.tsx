import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './fund-raiser-bank-my-suffix.reducer';
import { IFundRaiserBankMySuffix } from 'app/shared/model/fund-raiser-bank-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFundRaiserBankMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FundRaiserBankMySuffixDetail extends React.Component<IFundRaiserBankMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { fundRaiserBankEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            FundRaiserBank [<b>{fundRaiserBankEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="bankAccountNo">Bank Account No</span>
            </dt>
            <dd>{fundRaiserBankEntity.bankAccountNo}</dd>
            <dt>
              <span id="bankAccountName">Bank Account Name</span>
            </dt>
            <dd>{fundRaiserBankEntity.bankAccountName}</dd>
            <dt>
              <span id="bankBranch">Bank Branch</span>
            </dt>
            <dd>{fundRaiserBankEntity.bankBranch}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{fundRaiserBankEntity.status}</dd>
            <dt>Fund Raiser</dt>
            <dd>{fundRaiserBankEntity.fundRaiserId ? fundRaiserBankEntity.fundRaiserId : ''}</dd>
            <dt>Bank</dt>
            <dd>{fundRaiserBankEntity.bankId ? fundRaiserBankEntity.bankId : ''}</dd>
            <dt>Currency</dt>
            <dd>{fundRaiserBankEntity.currencyId ? fundRaiserBankEntity.currencyId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/fund-raiser-bank-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/fund-raiser-bank-my-suffix/${fundRaiserBankEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ fundRaiserBank }: IRootState) => ({
  fundRaiserBankEntity: fundRaiserBank.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FundRaiserBankMySuffixDetail);
