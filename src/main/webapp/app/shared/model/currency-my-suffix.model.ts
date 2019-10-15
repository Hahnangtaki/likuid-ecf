import { IFundRaiserBankMySuffix } from 'app/shared/model/fund-raiser-bank-my-suffix.model';
import { ICompanyBankMySuffix } from 'app/shared/model/company-bank-my-suffix.model';
import { IInvestorBankMySuffix } from 'app/shared/model/investor-bank-my-suffix.model';
import { ICampaignMySuffix } from 'app/shared/model/campaign-my-suffix.model';

export interface ICurrencyMySuffix {
  id?: number;
  currencyCode?: string;
  currencyName?: string;
  currencySymbol?: string;
  fundRaiserBanks?: IFundRaiserBankMySuffix[];
  companyBanks?: ICompanyBankMySuffix[];
  investorBanks?: IInvestorBankMySuffix[];
  campaigns?: ICampaignMySuffix[];
}

export const defaultValue: Readonly<ICurrencyMySuffix> = {};
