import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICategoryMySuffix } from 'app/shared/model/category-my-suffix.model';
import { getEntities as getCategories } from 'app/entities/category-my-suffix/category-my-suffix.reducer';
import { ICampaignMySuffix } from 'app/shared/model/campaign-my-suffix.model';
import { getEntities as getCampaigns } from 'app/entities/campaign-my-suffix/campaign-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './campaign-category-my-suffix.reducer';
import { ICampaignCategoryMySuffix } from 'app/shared/model/campaign-category-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICampaignCategoryMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICampaignCategoryMySuffixUpdateState {
  isNew: boolean;
  categoryId: string;
  campaignId: string;
}

export class CampaignCategoryMySuffixUpdate extends React.Component<
  ICampaignCategoryMySuffixUpdateProps,
  ICampaignCategoryMySuffixUpdateState
> {
  constructor(props) {
    super(props);
    this.state = {
      categoryId: '0',
      campaignId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getCategories();
    this.props.getCampaigns();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { campaignCategoryEntity } = this.props;
      const entity = {
        ...campaignCategoryEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/campaign-category-my-suffix');
  };

  render() {
    const { campaignCategoryEntity, categories, campaigns, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.campaignCategory.home.createOrEditLabel">Create or edit a CampaignCategory</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : campaignCategoryEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="campaign-category-my-suffix-id">ID</Label>
                    <AvInput id="campaign-category-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label for="campaign-category-my-suffix-category">Category</Label>
                  <AvInput id="campaign-category-my-suffix-category" type="select" className="form-control" name="categoryId">
                    <option value="" key="0" />
                    {categories
                      ? categories.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="campaign-category-my-suffix-campaign">Campaign</Label>
                  <AvInput id="campaign-category-my-suffix-campaign" type="select" className="form-control" name="campaignId">
                    <option value="" key="0" />
                    {campaigns
                      ? campaigns.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/campaign-category-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  categories: storeState.category.entities,
  campaigns: storeState.campaign.entities,
  campaignCategoryEntity: storeState.campaignCategory.entity,
  loading: storeState.campaignCategory.loading,
  updating: storeState.campaignCategory.updating,
  updateSuccess: storeState.campaignCategory.updateSuccess
});

const mapDispatchToProps = {
  getCategories,
  getCampaigns,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignCategoryMySuffixUpdate);
