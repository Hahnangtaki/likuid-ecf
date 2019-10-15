import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CampaignInvestorMySuffix from './campaign-investor-my-suffix';
import CampaignInvestorMySuffixDetail from './campaign-investor-my-suffix-detail';
import CampaignInvestorMySuffixUpdate from './campaign-investor-my-suffix-update';
import CampaignInvestorMySuffixDeleteDialog from './campaign-investor-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CampaignInvestorMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CampaignInvestorMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CampaignInvestorMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={CampaignInvestorMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CampaignInvestorMySuffixDeleteDialog} />
  </>
);

export default Routes;
