import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CurrencyMySuffix from './currency-my-suffix';
import CurrencyMySuffixDetail from './currency-my-suffix-detail';
import CurrencyMySuffixUpdate from './currency-my-suffix-update';
import CurrencyMySuffixDeleteDialog from './currency-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CurrencyMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CurrencyMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CurrencyMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={CurrencyMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CurrencyMySuffixDeleteDialog} />
  </>
);

export default Routes;
