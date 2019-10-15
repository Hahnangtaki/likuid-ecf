import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InvestorAuthorizePersonMySuffix from './investor-authorize-person-my-suffix';
import InvestorAuthorizePersonMySuffixDetail from './investor-authorize-person-my-suffix-detail';
import InvestorAuthorizePersonMySuffixUpdate from './investor-authorize-person-my-suffix-update';
import InvestorAuthorizePersonMySuffixDeleteDialog from './investor-authorize-person-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InvestorAuthorizePersonMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InvestorAuthorizePersonMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InvestorAuthorizePersonMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={InvestorAuthorizePersonMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={InvestorAuthorizePersonMySuffixDeleteDialog} />
  </>
);

export default Routes;
