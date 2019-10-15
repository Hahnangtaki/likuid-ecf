import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CampaignPaymentMySuffix from './campaign-payment-my-suffix';
import CampaignPaymentMySuffixDetail from './campaign-payment-my-suffix-detail';
import CampaignPaymentMySuffixUpdate from './campaign-payment-my-suffix-update';
import CampaignPaymentMySuffixDeleteDialog from './campaign-payment-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CampaignPaymentMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CampaignPaymentMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CampaignPaymentMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={CampaignPaymentMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CampaignPaymentMySuffixDeleteDialog} />
  </>
);

export default Routes;
