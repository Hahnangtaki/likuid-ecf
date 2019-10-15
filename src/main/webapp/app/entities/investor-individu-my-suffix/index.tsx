import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InvestorIndividuMySuffix from './investor-individu-my-suffix';
import InvestorIndividuMySuffixDetail from './investor-individu-my-suffix-detail';
import InvestorIndividuMySuffixUpdate from './investor-individu-my-suffix-update';
import InvestorIndividuMySuffixDeleteDialog from './investor-individu-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InvestorIndividuMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InvestorIndividuMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InvestorIndividuMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={InvestorIndividuMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={InvestorIndividuMySuffixDeleteDialog} />
  </>
);

export default Routes;
