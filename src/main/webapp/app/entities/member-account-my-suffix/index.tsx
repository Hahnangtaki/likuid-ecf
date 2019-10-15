import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MemberAccountMySuffix from './member-account-my-suffix';
import MemberAccountMySuffixDetail from './member-account-my-suffix-detail';
import MemberAccountMySuffixUpdate from './member-account-my-suffix-update';
import MemberAccountMySuffixDeleteDialog from './member-account-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MemberAccountMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MemberAccountMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MemberAccountMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={MemberAccountMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={MemberAccountMySuffixDeleteDialog} />
  </>
);

export default Routes;
