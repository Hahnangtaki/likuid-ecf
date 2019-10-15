import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CompanyBankMySuffix from './company-bank-my-suffix';
import CompanyBankMySuffixDetail from './company-bank-my-suffix-detail';
import CompanyBankMySuffixUpdate from './company-bank-my-suffix-update';
import CompanyBankMySuffixDeleteDialog from './company-bank-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CompanyBankMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CompanyBankMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CompanyBankMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={CompanyBankMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CompanyBankMySuffixDeleteDialog} />
  </>
);

export default Routes;
