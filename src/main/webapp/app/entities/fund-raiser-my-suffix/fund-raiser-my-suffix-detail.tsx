import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './fund-raiser-my-suffix.reducer';
import { IFundRaiserMySuffix } from 'app/shared/model/fund-raiser-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFundRaiserMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FundRaiserMySuffixDetail extends React.Component<IFundRaiserMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { fundRaiserEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            FundRaiser [<b>{fundRaiserEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="companyName">Company Name</span>
            </dt>
            <dd>{fundRaiserEntity.companyName}</dd>
            <dt>
              <span id="yearFounded">Year Founded</span>
            </dt>
            <dd>{fundRaiserEntity.yearFounded}</dd>
            <dt>
              <span id="website">Website</span>
            </dt>
            <dd>{fundRaiserEntity.website}</dd>
          </dl>
          <Button tag={Link} to="/entity/fund-raiser-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/fund-raiser-my-suffix/${fundRaiserEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ fundRaiser }: IRootState) => ({
  fundRaiserEntity: fundRaiser.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FundRaiserMySuffixDetail);
