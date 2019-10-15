import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './campaign-category-my-suffix.reducer';
import { ICampaignCategoryMySuffix } from 'app/shared/model/campaign-category-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICampaignCategoryMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CampaignCategoryMySuffixDetail extends React.Component<ICampaignCategoryMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { campaignCategoryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            CampaignCategory [<b>{campaignCategoryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>Category</dt>
            <dd>{campaignCategoryEntity.categoryId ? campaignCategoryEntity.categoryId : ''}</dd>
            <dt>Campaign</dt>
            <dd>{campaignCategoryEntity.campaignId ? campaignCategoryEntity.campaignId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/campaign-category-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/campaign-category-my-suffix/${campaignCategoryEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ campaignCategory }: IRootState) => ({
  campaignCategoryEntity: campaignCategory.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignCategoryMySuffixDetail);
