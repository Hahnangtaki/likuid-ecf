import { IInvestorBankMySuffix } from 'app/shared/model/investor-bank-my-suffix.model';
import { IInvestorAddressMySuffix } from 'app/shared/model/investor-address-my-suffix.model';
import { IInvestorAuthorizePersonMySuffix } from 'app/shared/model/investor-authorize-person-my-suffix.model';
import { ICampaignInvestorMySuffix } from 'app/shared/model/campaign-investor-my-suffix.model';
import { ICampaignTransactionMySuffix } from 'app/shared/model/campaign-transaction-my-suffix.model';

export interface IInvestorMySuffix {
  id?: number;
  investorCode?: string;
  investorName?: string;
  investorType?: string;
  investorInstitutionId?: number;
  investorIndividuId?: number;
  investorBanks?: IInvestorBankMySuffix[];
  investorAddresses?: IInvestorAddressMySuffix[];
  investorAuthorizePeople?: IInvestorAuthorizePersonMySuffix[];
  campaignInvestors?: ICampaignInvestorMySuffix[];
  campaignTransactions?: ICampaignTransactionMySuffix[];
}

export const defaultValue: Readonly<IInvestorMySuffix> = {};
