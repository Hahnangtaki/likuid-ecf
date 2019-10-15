import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CampaignCategoryMySuffix from './campaign-category-my-suffix';
import CampaignCategoryMySuffixDetail from './campaign-category-my-suffix-detail';
import CampaignCategoryMySuffixUpdate from './campaign-category-my-suffix-update';
import CampaignCategoryMySuffixDeleteDialog from './campaign-category-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CampaignCategoryMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CampaignCategoryMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CampaignCategoryMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={CampaignCategoryMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CampaignCategoryMySuffixDeleteDialog} />
  </>
);

export default Routes;
