import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICurrencyMySuffix } from 'app/shared/model/currency-my-suffix.model';
import { getEntities as getCurrencies } from 'app/entities/currency-my-suffix/currency-my-suffix.reducer';
import { IFundRaiserMySuffix } from 'app/shared/model/fund-raiser-my-suffix.model';
import { getEntities as getFundRaisers } from 'app/entities/fund-raiser-my-suffix/fund-raiser-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './campaign-my-suffix.reducer';
import { ICampaignMySuffix } from 'app/shared/model/campaign-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICampaignMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICampaignMySuffixUpdateState {
  isNew: boolean;
  currencyId: string;
  fundRaiserId: string;
}

export class CampaignMySuffixUpdate extends React.Component<ICampaignMySuffixUpdateProps, ICampaignMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      currencyId: '0',
      fundRaiserId: '0',
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

    this.props.getCurrencies();
    this.props.getFundRaisers();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { campaignEntity } = this.props;
      const entity = {
        ...campaignEntity,
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
    this.props.history.push('/entity/campaign-my-suffix');
  };

  render() {
    const { campaignEntity, currencies, fundRaisers, loading, updating } = this.props;
    const { isNew } = this.state;

    const { prospectusFile, prospectusFileContentType } = campaignEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.campaign.home.createOrEditLabel">Create or edit a Campaign</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : campaignEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="campaign-my-suffix-id">ID</Label>
                    <AvInput id="campaign-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="campaignCodeLabel" for="campaign-my-suffix-campaignCode">
                    Campaign Code
                  </Label>
                  <AvField
                    id="campaign-my-suffix-campaignCode"
                    type="text"
                    name="campaignCode"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="campaignNameLabel" for="campaign-my-suffix-campaignName">
                    Campaign Name
                  </Label>
                  <AvField
                    id="campaign-my-suffix-campaignName"
                    type="text"
                    name="campaignName"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="campaignDescLabel" for="campaign-my-suffix-campaignDesc">
                    Campaign Desc
                  </Label>
                  <AvField
                    id="campaign-my-suffix-campaignDesc"
                    type="text"
                    name="campaignDesc"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="investmentTypeLabel" for="campaign-my-suffix-investmentType">
                    Investment Type
                  </Label>
                  <AvField
                    id="campaign-my-suffix-investmentType"
                    type="text"
                    name="investmentType"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="offerDateLabel" for="campaign-my-suffix-offerDate">
                    Offer Date
                  </Label>
                  <AvField id="campaign-my-suffix-offerDate" type="date" className="form-control" name="offerDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="deadlineDateLabel" for="campaign-my-suffix-deadlineDate">
                    Deadline Date
                  </Label>
                  <AvField id="campaign-my-suffix-deadlineDate" type="date" className="form-control" name="deadlineDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="priceLabel" for="campaign-my-suffix-price">
                    Price
                  </Label>
                  <AvField id="campaign-my-suffix-price" type="string" className="form-control" name="price" />
                </AvGroup>
                <AvGroup>
                  <Label id="fundTargetLabel" for="campaign-my-suffix-fundTarget">
                    Fund Target
                  </Label>
                  <AvField id="campaign-my-suffix-fundTarget" type="string" className="form-control" name="fundTarget" />
                </AvGroup>
                <AvGroup>
                  <Label id="fundRaisedLabel" for="campaign-my-suffix-fundRaised">
                    Fund Raised
                  </Label>
                  <AvField id="campaign-my-suffix-fundRaised" type="string" className="form-control" name="fundRaised" />
                </AvGroup>
                <AvGroup>
                  <Label id="fundPaidLabel" for="campaign-my-suffix-fundPaid">
                    Fund Paid
                  </Label>
                  <AvField id="campaign-my-suffix-fundPaid" type="string" className="form-control" name="fundPaid" />
                </AvGroup>
                <AvGroup>
                  <Label id="qtyTargetLabel" for="campaign-my-suffix-qtyTarget">
                    Qty Target
                  </Label>
                  <AvField id="campaign-my-suffix-qtyTarget" type="string" className="form-control" name="qtyTarget" />
                </AvGroup>
                <AvGroup>
                  <Label id="qtyRaisedLabel" for="campaign-my-suffix-qtyRaised">
                    Qty Raised
                  </Label>
                  <AvField id="campaign-my-suffix-qtyRaised" type="string" className="form-control" name="qtyRaised" />
                </AvGroup>
                <AvGroup>
                  <Label id="estRoiMinLabel" for="campaign-my-suffix-estRoiMin">
                    Est Roi Min
                  </Label>
                  <AvField id="campaign-my-suffix-estRoiMin" type="string" className="form-control" name="estRoiMin" />
                </AvGroup>
                <AvGroup>
                  <Label id="estRoiMaxLabel" for="campaign-my-suffix-estRoiMax">
                    Est Roi Max
                  </Label>
                  <AvField id="campaign-my-suffix-estRoiMax" type="string" className="form-control" name="estRoiMax" />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="prospectusFileLabel" for="prospectusFile">
                      Prospectus File
                    </Label>
                    <br />
                    {prospectusFile ? (
                      <div>
                        <a onClick={openFile(prospectusFileContentType, prospectusFile)}>Open</a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {prospectusFileContentType}, {byteSize(prospectusFile)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('prospectusFile')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_prospectusFile" type="file" onChange={this.onBlobChange(false, 'prospectusFile')} />
                    <AvInput type="hidden" name="prospectusFile" value={prospectusFile} />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="campaign-my-suffix-status">
                    Status
                  </Label>
                  <AvField id="campaign-my-suffix-status" type="text" name="status" />
                </AvGroup>
                <AvGroup>
                  <Label for="campaign-my-suffix-currency">Currency</Label>
                  <AvInput id="campaign-my-suffix-currency" type="select" className="form-control" name="currencyId">
                    <option value="" key="0" />
                    {currencies
                      ? currencies.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="campaign-my-suffix-fundRaiser">Fund Raiser</Label>
                  <AvInput id="campaign-my-suffix-fundRaiser" type="select" className="form-control" name="fundRaiserId">
                    <option value="" key="0" />
                    {fundRaisers
                      ? fundRaisers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/campaign-my-suffix" replace color="info">
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
  currencies: storeState.currency.entities,
  fundRaisers: storeState.fundRaiser.entities,
  campaignEntity: storeState.campaign.entity,
  loading: storeState.campaign.loading,
  updating: storeState.campaign.updating,
  updateSuccess: storeState.campaign.updateSuccess
});

const mapDispatchToProps = {
  getCurrencies,
  getFundRaisers,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignMySuffixUpdate);
