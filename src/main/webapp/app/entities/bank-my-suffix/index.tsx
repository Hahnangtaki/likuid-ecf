import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BankMySuffix from './bank-my-suffix';
import BankMySuffixDetail from './bank-my-suffix-detail';
import BankMySuffixUpdate from './bank-my-suffix-update';
import BankMySuffixDeleteDialog from './bank-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BankMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BankMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BankMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={BankMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BankMySuffixDeleteDialog} />
  </>
);

export default Routes;
