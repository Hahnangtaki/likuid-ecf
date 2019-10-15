import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './province-my-suffix.reducer';
import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProvinceMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ProvinceMySuffixDetail extends React.Component<IProvinceMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { provinceEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Province [<b>{provinceEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="provinceCode">Province Code</span>
            </dt>
            <dd>{provinceEntity.provinceCode}</dd>
            <dt>
              <span id="provinceName">Province Name</span>
            </dt>
            <dd>{provinceEntity.provinceName}</dd>
            <dt>Country</dt>
            <dd>{provinceEntity.countryId ? provinceEntity.countryId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/province-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/province-my-suffix/${provinceEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ province }: IRootState) => ({
  provinceEntity: province.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProvinceMySuffixDetail);
