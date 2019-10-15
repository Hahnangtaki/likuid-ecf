import { IFundRaiserBankMySuffix } from 'app/shared/model/fund-raiser-bank-my-suffix.model';
import { ICompanyBankMySuffix } from 'app/shared/model/company-bank-my-suffix.model';
import { IInvestorBankMySuffix } from 'app/shared/model/investor-bank-my-suffix.model';

export interface IBankMySuffix {
  id?: number;
  bankCode?: string;
  bankName?: string;
  initialName?: string;
  biCode?: string;
  swiftCode?: string;
  fundRaiserBanks?: IFundRaiserBankMySuffix[];
  companyBanks?: ICompanyBankMySuffix[];
  investorBanks?: IInvestorBankMySuffix[];
}

export const defaultValue: Readonly<IBankMySuffix> = {};
