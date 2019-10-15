import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IInvestorMySuffix } from 'app/shared/model/investor-my-suffix.model';
import { getEntities as getInvestors } from 'app/entities/investor-my-suffix/investor-my-suffix.reducer';
import { ICampaignMySuffix } from 'app/shared/model/campaign-my-suffix.model';
import { getEntities as getCampaigns } from 'app/entities/campaign-my-suffix/campaign-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './campaign-investor-my-suffix.reducer';
import { ICampaignInvestorMySuffix } from 'app/shared/model/campaign-investor-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICampaignInvestorMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICampaignInvestorMySuffixUpdateState {
  isNew: boolean;
  investorId: string;
  campaignId: string;
}

export class CampaignInvestorMySuffixUpdate extends React.Component<
  ICampaignInvestorMySuffixUpdateProps,
  ICampaignInvestorMySuffixUpdateState
> {
  constructor(props) {
    super(props);
    this.state = {
      investorId: '0',
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

    this.props.getInvestors();
    this.props.getCampaigns();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { campaignInvestorEntity } = this.props;
      const entity = {
        ...campaignInvestorEntity,
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
    this.props.history.push('/entity/campaign-investor-my-suffix');
  };

  render() {
    const { campaignInvestorEntity, investors, campaigns, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.campaignInvestor.home.createOrEditLabel">Create or edit a CampaignInvestor</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : campaignInvestorEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="campaign-investor-my-suffix-id">ID</Label>
                    <AvInput id="campaign-investor-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="qtyLabel" for="campaign-investor-my-suffix-qty">
                    Qty
                  </Label>
                  <AvField id="campaign-investor-my-suffix-qty" type="string" className="form-control" name="qty" />
                </AvGroup>
                <AvGroup>
                  <Label id="fundAmountLabel" for="campaign-investor-my-suffix-fundAmount">
                    Fund Amount
                  </Label>
                  <AvField id="campaign-investor-my-suffix-fundAmount" type="string" className="form-control" name="fundAmount" />
                </AvGroup>
                <AvGroup>
                  <Label for="campaign-investor-my-suffix-investor">Investor</Label>
                  <AvInput id="campaign-investor-my-suffix-investor" type="select" className="form-control" name="investorId">
                    <option value="" key="0" />
                    {investors
                      ? investors.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="campaign-investor-my-suffix-campaign">Campaign</Label>
                  <AvInput id="campaign-investor-my-suffix-campaign" type="select" className="form-control" name="campaignId">
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
                <Button tag={Link} id="cancel-save" to="/entity/campaign-investor-my-suffix" replace color="info">
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
  investors: storeState.investor.entities,
  campaigns: storeState.campaign.entities,
  campaignInvestorEntity: storeState.campaignInvestor.entity,
  loading: storeState.campaignInvestor.loading,
  updating: storeState.campaignInvestor.updating,
  updateSuccess: storeState.campaignInvestor.updateSuccess
});

const mapDispatchToProps = {
  getInvestors,
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
)(CampaignInvestorMySuffixUpdate);
