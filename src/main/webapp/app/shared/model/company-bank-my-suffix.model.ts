import { ICampaignPaymentMySuffix } from 'app/shared/model/campaign-payment-my-suffix.model';

export interface ICompanyBankMySuffix {
  id?: number;
  bankAccountNo?: string;
  bankAccountName?: string;
  bankBranch?: string;
  status?: string;
  campaignPayments?: ICampaignPaymentMySuffix[];
  bankId?: number;
  currencyId?: number;
}

export const defaultValue: Readonly<ICompanyBankMySuffix> = {};
