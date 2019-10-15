import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FundRaiserMySuffix from './fund-raiser-my-suffix';
import FundRaiserMySuffixDetail from './fund-raiser-my-suffix-detail';
import FundRaiserMySuffixUpdate from './fund-raiser-my-suffix-update';
import FundRaiserMySuffixDeleteDialog from './fund-raiser-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FundRaiserMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FundRaiserMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FundRaiserMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={FundRaiserMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FundRaiserMySuffixDeleteDialog} />
  </>
);

export default Routes;
