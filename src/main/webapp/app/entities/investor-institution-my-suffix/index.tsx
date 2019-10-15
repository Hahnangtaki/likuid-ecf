import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InvestorInstitutionMySuffix from './investor-institution-my-suffix';
import InvestorInstitutionMySuffixDetail from './investor-institution-my-suffix-detail';
import InvestorInstitutionMySuffixUpdate from './investor-institution-my-suffix-update';
import InvestorInstitutionMySuffixDeleteDialog from './investor-institution-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InvestorInstitutionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InvestorInstitutionMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InvestorInstitutionMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={InvestorInstitutionMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={InvestorInstitutionMySuffixDeleteDialog} />
  </>
);

export default Routes;
