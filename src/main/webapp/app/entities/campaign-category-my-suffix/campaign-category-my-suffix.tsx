import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './campaign-category-my-suffix.reducer';
import { ICampaignCategoryMySuffix } from 'app/shared/model/campaign-category-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICampaignCategoryMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class CampaignCategoryMySuffix extends React.Component<ICampaignCategoryMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { campaignCategoryList, match } = this.props;
    return (
      <div>
        <h2 id="campaign-category-my-suffix-heading">
          Campaign Categories
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Campaign Category
          </Link>
        </h2>
        <div className="table-responsive">
          {campaignCategoryList && campaignCategoryList.length > 0 ? (
            <Table responsive aria-describedby="campaign-category-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Category</th>
                  <th>Campaign</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {campaignCategoryList.map((campaignCategory, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${campaignCategory.id}`} color="link" size="sm">
                        {campaignCategory.id}
                      </Button>
                    </td>
                    <td>
                      {campaignCategory.categoryId ? (
                        <Link to={`category-my-suffix/${campaignCategory.categoryId}`}>{campaignCategory.categoryId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {campaignCategory.campaignId ? (
                        <Link to={`campaign-my-suffix/${campaignCategory.campaignId}`}>{campaignCategory.campaignId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${campaignCategory.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${campaignCategory.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${campaignCategory.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Campaign Categories found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ campaignCategory }: IRootState) => ({
  campaignCategoryList: campaignCategory.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CampaignCategoryMySuffix);
