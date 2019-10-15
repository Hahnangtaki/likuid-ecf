import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tax-my-suffix.reducer';
import { ITaxMySuffix } from 'app/shared/model/tax-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITaxMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TaxMySuffixDetail extends React.Component<ITaxMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { taxEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Tax [<b>{taxEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="taxCode">Tax Code</span>
            </dt>
            <dd>{taxEntity.taxCode}</dd>
            <dt>
              <span id="shortDesc">Short Desc</span>
            </dt>
            <dd>{taxEntity.shortDesc}</dd>
            <dt>
              <span id="longDesc">Long Desc</span>
            </dt>
            <dd>{taxEntity.longDesc}</dd>
          </dl>
          <Button tag={Link} to="/entity/tax-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/tax-my-suffix/${taxEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ tax }: IRootState) => ({
  taxEntity: tax.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TaxMySuffixDetail);
