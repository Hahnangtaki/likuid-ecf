import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CampaignTransactionMySuffix from './campaign-transaction-my-suffix';
import CampaignTransactionMySuffixDetail from './campaign-transaction-my-suffix-detail';
import CampaignTransactionMySuffixUpdate from './campaign-transaction-my-suffix-update';
import CampaignTransactionMySuffixDeleteDialog from './campaign-transaction-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CampaignTransactionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CampaignTransactionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CampaignTransactionMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={CampaignTransactionMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CampaignTransactionMySuffixDeleteDialog} />
  </>
);

export default Routes;
