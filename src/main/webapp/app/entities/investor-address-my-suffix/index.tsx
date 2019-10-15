import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InvestorAddressMySuffix from './investor-address-my-suffix';
import InvestorAddressMySuffixDetail from './investor-address-my-suffix-detail';
import InvestorAddressMySuffixUpdate from './investor-address-my-suffix-update';
import InvestorAddressMySuffixDeleteDialog from './investor-address-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InvestorAddressMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InvestorAddressMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InvestorAddressMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={InvestorAddressMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={InvestorAddressMySuffixDeleteDialog} />
  </>
);

export default Routes;
