import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './company-bank-my-suffix.reducer';
import { ICompanyBankMySuffix } from 'app/shared/model/company-bank-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICompanyBankMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CompanyBankMySuffixDetail extends React.Component<ICompanyBankMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { companyBankEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            CompanyBank [<b>{companyBankEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="bankAccountNo">Bank Account No</span>
            </dt>
            <dd>{companyBankEntity.bankAccountNo}</dd>
            <dt>
              <span id="bankAccountName">Bank Account Name</span>
            </dt>
            <dd>{companyBankEntity.bankAccountName}</dd>
            <dt>
              <span id="bankBranch">Bank Branch</span>
            </dt>
            <dd>{companyBankEntity.bankBranch}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{companyBankEntity.status}</dd>
            <dt>Bank</dt>
            <dd>{companyBankEntity.bankId ? companyBankEntity.bankId : ''}</dd>
            <dt>Currency</dt>
            <dd>{companyBankEntity.currencyId ? companyBankEntity.currencyId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/company-bank-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/company-bank-my-suffix/${companyBankEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ companyBank }: IRootState) => ({
  companyBankEntity: companyBank.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CompanyBankMySuffixDetail);
