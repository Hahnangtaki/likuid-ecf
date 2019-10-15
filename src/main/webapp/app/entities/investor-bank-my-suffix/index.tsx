import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InvestorBankMySuffix from './investor-bank-my-suffix';
import InvestorBankMySuffixDetail from './investor-bank-my-suffix-detail';
import InvestorBankMySuffixUpdate from './investor-bank-my-suffix-update';
import InvestorBankMySuffixDeleteDialog from './investor-bank-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InvestorBankMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InvestorBankMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InvestorBankMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={InvestorBankMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={InvestorBankMySuffixDeleteDialog} />
  </>
);

export default Routes;
