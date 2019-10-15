import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './campaign-investor-my-suffix.reducer';
import { ICampaignInvestorMySuffix } from 'app/shared/model/campaign-investor-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICampaignInvestorMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CampaignInvestorMySuffixDetail extends React.Component<ICampaignInvestorMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { campaignInvestorEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            CampaignInvestor [<b>{campaignInvestorEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="qty">Qty</span>
            </dt>
            <dd>{campaignInvestorEntity.qty}</dd>
            <dt>
              <span id="fundAmount">Fund Amount</span>
            </dt>
            <dd>{campaignInvestorEntity.fundAmount}</dd>
            <dt>Investor</dt>
            <dd>{campaignInvestorEntity.investorId ? campaignInvestorEntity.investorId : ''}</dd>
            <dt>Campaign</dt>
            <dd>{campaignInvestorEntity.campaignId ? campaignInvestorEntity.campaignId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/campaign-investor-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/campaign-investor-my-suffix/${campaignInvestorEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ campaignInvestor }: IRootState) => ({
  campaignInvestorEntity: campaignInvestor.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignInvestorMySuffixDetail);
