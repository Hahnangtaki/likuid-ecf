import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TaxMySuffix from './tax-my-suffix';
import TaxMySuffixDetail from './tax-my-suffix-detail';
import TaxMySuffixUpdate from './tax-my-suffix-update';
import TaxMySuffixDeleteDialog from './tax-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TaxMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TaxMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TaxMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={TaxMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TaxMySuffixDeleteDialog} />
  </>
);

export default Routes;
