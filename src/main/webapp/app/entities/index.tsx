import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MemberAccountMySuffix from './member-account-my-suffix';
import OtpHistoryMySuffix from './otp-history-my-suffix';
import CategoryMySuffix from './category-my-suffix';
import CampaignCategoryMySuffix from './campaign-category-my-suffix';
import FundRaiserMySuffix from './fund-raiser-my-suffix';
import CurrencyMySuffix from './currency-my-suffix';
import CampaignMySuffix from './campaign-my-suffix';
import CampaignPaymentMySuffix from './campaign-payment-my-suffix';
import CampaignTransactionMySuffix from './campaign-transaction-my-suffix';
import InvestorMySuffix from './investor-my-suffix';
import InvestorBankMySuffix from './investor-bank-my-suffix';
import CompanyBankMySuffix from './company-bank-my-suffix';
import FundRaiserBankMySuffix from './fund-raiser-bank-my-suffix';
import BankMySuffix from './bank-my-suffix';
import InvestorAuthorizePersonMySuffix from './investor-authorize-person-my-suffix';
import CampaignInvestorMySuffix from './campaign-investor-my-suffix';
import InvestorInstitutionMySuffix from './investor-institution-my-suffix';
import InvestorIndividuMySuffix from './investor-individu-my-suffix';
import TaxMySuffix from './tax-my-suffix';
import InvestorAddressMySuffix from './investor-address-my-suffix';
import CountryMySuffix from './country-my-suffix';
import ProvinceMySuffix from './province-my-suffix';
import CityMySuffix from './city-my-suffix';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/member-account-my-suffix`} component={MemberAccountMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/otp-history-my-suffix`} component={OtpHistoryMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/category-my-suffix`} component={CategoryMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/campaign-category-my-suffix`} component={CampaignCategoryMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/fund-raiser-my-suffix`} component={FundRaiserMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/currency-my-suffix`} component={CurrencyMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/campaign-my-suffix`} component={CampaignMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/campaign-payment-my-suffix`} component={CampaignPaymentMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/campaign-transaction-my-suffix`} component={CampaignTransactionMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/investor-my-suffix`} component={InvestorMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/investor-bank-my-suffix`} component={InvestorBankMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/company-bank-my-suffix`} component={CompanyBankMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/fund-raiser-bank-my-suffix`} component={FundRaiserBankMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/bank-my-suffix`} component={BankMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/investor-authorize-person-my-suffix`} component={InvestorAuthorizePersonMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/campaign-investor-my-suffix`} component={CampaignInvestorMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/investor-institution-my-suffix`} component={InvestorInstitutionMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/investor-individu-my-suffix`} component={InvestorIndividuMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/tax-my-suffix`} component={TaxMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/investor-address-my-suffix`} component={InvestorAddressMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/country-my-suffix`} component={CountryMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/province-my-suffix`} component={ProvinceMySuffix} />
      <ErrorBoundaryRoute path={`${match.url}/city-my-suffix`} component={CityMySuffix} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
