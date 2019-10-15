import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FundRaiserBankMySuffix from './fund-raiser-bank-my-suffix';
import FundRaiserBankMySuffixDetail from './fund-raiser-bank-my-suffix-detail';
import FundRaiserBankMySuffixUpdate from './fund-raiser-bank-my-suffix-update';
import FundRaiserBankMySuffixDeleteDialog from './fund-raiser-bank-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FundRaiserBankMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FundRaiserBankMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FundRaiserBankMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={FundRaiserBankMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FundRaiserBankMySuffixDeleteDialog} />
  </>
);

export default Routes;
