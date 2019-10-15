import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InvestorMySuffix from './investor-my-suffix';
import InvestorMySuffixDetail from './investor-my-suffix-detail';
import InvestorMySuffixUpdate from './investor-my-suffix-update';
import InvestorMySuffixDeleteDialog from './investor-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InvestorMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InvestorMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InvestorMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={InvestorMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={InvestorMySuffixDeleteDialog} />
  </>
);

export default Routes;
