import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import memberAccount, {
  MemberAccountMySuffixState
} from 'app/entities/member-account-my-suffix/member-account-my-suffix.reducer';
// prettier-ignore
import otpHistory, {
  OtpHistoryMySuffixState
} from 'app/entities/otp-history-my-suffix/otp-history-my-suffix.reducer';
// prettier-ignore
import category, {
  CategoryMySuffixState
} from 'app/entities/category-my-suffix/category-my-suffix.reducer';
// prettier-ignore
import campaignCategory, {
  CampaignCategoryMySuffixState
} from 'app/entities/campaign-category-my-suffix/campaign-category-my-suffix.reducer';
// prettier-ignore
import fundRaiser, {
  FundRaiserMySuffixState
} from 'app/entities/fund-raiser-my-suffix/fund-raiser-my-suffix.reducer';
// prettier-ignore
import currency, {
  CurrencyMySuffixState
} from 'app/entities/currency-my-suffix/currency-my-suffix.reducer';
// prettier-ignore
import campaign, {
  CampaignMySuffixState
} from 'app/entities/campaign-my-suffix/campaign-my-suffix.reducer';
// prettier-ignore
import campaignPayment, {
  CampaignPaymentMySuffixState
} from 'app/entities/campaign-payment-my-suffix/campaign-payment-my-suffix.reducer';
// prettier-ignore
import campaignTransaction, {
  CampaignTransactionMySuffixState
} from 'app/entities/campaign-transaction-my-suffix/campaign-transaction-my-suffix.reducer';
// prettier-ignore
import investor, {
  InvestorMySuffixState
} from 'app/entities/investor-my-suffix/investor-my-suffix.reducer';
// prettier-ignore
import investorBank, {
  InvestorBankMySuffixState
} from 'app/entities/investor-bank-my-suffix/investor-bank-my-suffix.reducer';
// prettier-ignore
import companyBank, {
  CompanyBankMySuffixState
} from 'app/entities/company-bank-my-suffix/company-bank-my-suffix.reducer';
// prettier-ignore
import fundRaiserBank, {
  FundRaiserBankMySuffixState
} from 'app/entities/fund-raiser-bank-my-suffix/fund-raiser-bank-my-suffix.reducer';
// prettier-ignore
import bank, {
  BankMySuffixState
} from 'app/entities/bank-my-suffix/bank-my-suffix.reducer';
// prettier-ignore
import investorAuthorizePerson, {
  InvestorAuthorizePersonMySuffixState
} from 'app/entities/investor-authorize-person-my-suffix/investor-authorize-person-my-suffix.reducer';
// prettier-ignore
import campaignInvestor, {
  CampaignInvestorMySuffixState
} from 'app/entities/campaign-investor-my-suffix/campaign-investor-my-suffix.reducer';
// prettier-ignore
import investorInstitution, {
  InvestorInstitutionMySuffixState
} from 'app/entities/investor-institution-my-suffix/investor-institution-my-suffix.reducer';
// prettier-ignore
import investorIndividu, {
  InvestorIndividuMySuffixState
} from 'app/entities/investor-individu-my-suffix/investor-individu-my-suffix.reducer';
// prettier-ignore
import tax, {
  TaxMySuffixState
} from 'app/entities/tax-my-suffix/tax-my-suffix.reducer';
// prettier-ignore
import investorAddress, {
  InvestorAddressMySuffixState
} from 'app/entities/investor-address-my-suffix/investor-address-my-suffix.reducer';
// prettier-ignore
import country, {
  CountryMySuffixState
} from 'app/entities/country-my-suffix/country-my-suffix.reducer';
// prettier-ignore
import province, {
  ProvinceMySuffixState
} from 'app/entities/province-my-suffix/province-my-suffix.reducer';
// prettier-ignore
import city, {
  CityMySuffixState
} from 'app/entities/city-my-suffix/city-my-suffix.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly memberAccount: MemberAccountMySuffixState;
  readonly otpHistory: OtpHistoryMySuffixState;
  readonly category: CategoryMySuffixState;
  readonly campaignCategory: CampaignCategoryMySuffixState;
  readonly fundRaiser: FundRaiserMySuffixState;
  readonly currency: CurrencyMySuffixState;
  readonly campaign: CampaignMySuffixState;
  readonly campaignPayment: CampaignPaymentMySuffixState;
  readonly campaignTransaction: CampaignTransactionMySuffixState;
  readonly investor: InvestorMySuffixState;
  readonly investorBank: InvestorBankMySuffixState;
  readonly companyBank: CompanyBankMySuffixState;
  readonly fundRaiserBank: FundRaiserBankMySuffixState;
  readonly bank: BankMySuffixState;
  readonly investorAuthorizePerson: InvestorAuthorizePersonMySuffixState;
  readonly campaignInvestor: CampaignInvestorMySuffixState;
  readonly investorInstitution: InvestorInstitutionMySuffixState;
  readonly investorIndividu: InvestorIndividuMySuffixState;
  readonly tax: TaxMySuffixState;
  readonly investorAddress: InvestorAddressMySuffixState;
  readonly country: CountryMySuffixState;
  readonly province: ProvinceMySuffixState;
  readonly city: CityMySuffixState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  memberAccount,
  otpHistory,
  category,
  campaignCategory,
  fundRaiser,
  currency,
  campaign,
  campaignPayment,
  campaignTransaction,
  investor,
  investorBank,
  companyBank,
  fundRaiserBank,
  bank,
  investorAuthorizePerson,
  campaignInvestor,
  investorInstitution,
  investorIndividu,
  tax,
  investorAddress,
  country,
  province,
  city,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
