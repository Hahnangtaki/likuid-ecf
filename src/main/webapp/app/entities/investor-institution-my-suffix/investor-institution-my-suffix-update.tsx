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
import { ITaxMySuffix } from 'app/shared/model/tax-my-suffix.model';
import { getEntities as getTaxes } from 'app/entities/tax-my-suffix/tax-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './investor-institution-my-suffix.reducer';
import { IInvestorInstitutionMySuffix } from 'app/shared/model/investor-institution-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInvestorInstitutionMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IInvestorInstitutionMySuffixUpdateState {
  isNew: boolean;
  investorId: string;
  taxId: string;
}

export class InvestorInstitutionMySuffixUpdate extends React.Component<
  IInvestorInstitutionMySuffixUpdateProps,
  IInvestorInstitutionMySuffixUpdateState
> {
  constructor(props) {
    super(props);
    this.state = {
      investorId: '0',
      taxId: '0',
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
    this.props.getTaxes();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { investorInstitutionEntity } = this.props;
      const entity = {
        ...investorInstitutionEntity,
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
    this.props.history.push('/entity/investor-institution-my-suffix');
  };

  render() {
    const { investorInstitutionEntity, investors, taxes, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="likuidecfApp.investorInstitution.home.createOrEditLabel">Create or edit a InvestorInstitution</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : investorInstitutionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="investor-institution-my-suffix-id">ID</Label>
                    <AvInput id="investor-institution-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="sidLabel" for="investor-institution-my-suffix-sid">
                    Sid
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-sid"
                    type="text"
                    name="sid"
                    validate={{
                      maxLength: { value: 15, errorMessage: 'This field cannot be longer than 15 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="companyNameLabel" for="investor-institution-my-suffix-companyName">
                    Company Name
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-companyName"
                    type="text"
                    name="companyName"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bicCodeLabel" for="investor-institution-my-suffix-bicCode">
                    Bic Code
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-bicCode"
                    type="text"
                    name="bicCode"
                    validate={{
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="legalDomicileIdLabel" for="investor-institution-my-suffix-legalDomicileId">
                    Legal Domicile Id
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-legalDomicileId"
                    type="string"
                    className="form-control"
                    name="legalDomicileId"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="npwpLabel" for="investor-institution-my-suffix-npwp">
                    Npwp
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-npwp"
                    type="text"
                    name="npwp"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="npwpRegDateLabel" for="investor-institution-my-suffix-npwpRegDate">
                    Npwp Reg Date
                  </Label>
                  <AvField id="investor-institution-my-suffix-npwpRegDate" type="date" className="form-control" name="npwpRegDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="skdLabel" for="investor-institution-my-suffix-skd">
                    Skd
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-skd"
                    type="text"
                    name="skd"
                    validate={{
                      maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="skdExpDateLabel" for="investor-institution-my-suffix-skdExpDate">
                    Skd Exp Date
                  </Label>
                  <AvField id="investor-institution-my-suffix-skdExpDate" type="date" className="form-control" name="skdExpDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="companyEstablishPlceLabel" for="investor-institution-my-suffix-companyEstablishPlce">
                    Company Establish Plce
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-companyEstablishPlce"
                    type="text"
                    name="companyEstablishPlce"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="companyEstablishDateLabel" for="investor-institution-my-suffix-companyEstablishDate">
                    Company Establish Date
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-companyEstablishDate"
                    type="date"
                    className="form-control"
                    name="companyEstablishDate"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="businessTypeLabel" for="investor-institution-my-suffix-businessType">
                    Business Type
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-businessType"
                    type="text"
                    name="businessType"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="companyChracteristicLabel" for="investor-institution-my-suffix-companyChracteristic">
                    Company Chracteristic
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-companyChracteristic"
                    type="text"
                    name="companyChracteristic"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fundSourceLabel" for="investor-institution-my-suffix-fundSource">
                    Fund Source
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-fundSource"
                    type="text"
                    name="fundSource"
                    validate={{
                      maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fundSourceTextLabel" for="investor-institution-my-suffix-fundSourceText">
                    Fund Source Text
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-fundSourceText"
                    type="text"
                    name="fundSourceText"
                    validate={{
                      maxLength: { value: 120, errorMessage: 'This field cannot be longer than 120 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="articleAssociationLabel" for="investor-institution-my-suffix-articleAssociation">
                    Article Association
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-articleAssociation"
                    type="text"
                    name="articleAssociation"
                    validate={{
                      maxLength: { value: 120, errorMessage: 'This field cannot be longer than 120 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bussinessRegNoLabel" for="investor-institution-my-suffix-bussinessRegNo">
                    Bussiness Reg No
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-bussinessRegNo"
                    type="text"
                    name="bussinessRegNo"
                    validate={{
                      maxLength: { value: 120, errorMessage: 'This field cannot be longer than 120 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="financialAsset1Label" for="investor-institution-my-suffix-financialAsset1">
                    Financial Asset 1
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-financialAsset1"
                    type="string"
                    className="form-control"
                    name="financialAsset1"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="financialAsset2Label" for="investor-institution-my-suffix-financialAsset2">
                    Financial Asset 2
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-financialAsset2"
                    type="string"
                    className="form-control"
                    name="financialAsset2"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="financialAsset3Label" for="investor-institution-my-suffix-financialAsset3">
                    Financial Asset 3
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-financialAsset3"
                    type="string"
                    className="form-control"
                    name="financialAsset3"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="operatingProfit1Label" for="investor-institution-my-suffix-operatingProfit1">
                    Operating Profit 1
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-operatingProfit1"
                    type="string"
                    className="form-control"
                    name="operatingProfit1"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="operatingProfit2Label" for="investor-institution-my-suffix-operatingProfit2">
                    Operating Profit 2
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-operatingProfit2"
                    type="string"
                    className="form-control"
                    name="operatingProfit2"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="operatingProfit3Label" for="investor-institution-my-suffix-operatingProfit3">
                    Operating Profit 3
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-operatingProfit3"
                    type="string"
                    className="form-control"
                    name="operatingProfit3"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="investor-institution-my-suffix-description">
                    Description
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-description"
                    type="text"
                    name="description"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="investmentObjectiveLabel" for="investor-institution-my-suffix-investmentObjective">
                    Investment Objective
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-investmentObjective"
                    type="text"
                    name="investmentObjective"
                    validate={{
                      maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="directSidLabel" for="investor-institution-my-suffix-directSid">
                    Direct Sid
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-directSid"
                    type="text"
                    name="directSid"
                    validate={{
                      maxLength: { value: 15, errorMessage: 'This field cannot be longer than 15 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="assetOwnerLabel" for="investor-institution-my-suffix-assetOwner">
                    Asset Owner
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-assetOwner"
                    type="text"
                    name="assetOwner"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="supDocTypeLabel" for="investor-institution-my-suffix-supDocType">
                    Sup Doc Type
                  </Label>
                  <AvField
                    id="investor-institution-my-suffix-supDocType"
                    type="text"
                    name="supDocType"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="supDocExpDateLabel" for="investor-institution-my-suffix-supDocExpDate">
                    Sup Doc Exp Date
                  </Label>
                  <AvField id="investor-institution-my-suffix-supDocExpDate" type="date" className="form-control" name="supDocExpDate" />
                </AvGroup>
                <AvGroup>
                  <Label for="investor-institution-my-suffix-tax">Tax</Label>
                  <AvInput id="investor-institution-my-suffix-tax" type="select" className="form-control" name="taxId">
                    <option value="" key="0" />
                    {taxes
                      ? taxes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/investor-institution-my-suffix" replace color="info">
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
  taxes: storeState.tax.entities,
  investorInstitutionEntity: storeState.investorInstitution.entity,
  loading: storeState.investorInstitution.loading,
  updating: storeState.investorInstitution.updating,
  updateSuccess: storeState.investorInstitution.updateSuccess
});

const mapDispatchToProps = {
  getInvestors,
  getTaxes,
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
)(InvestorInstitutionMySuffixUpdate);
