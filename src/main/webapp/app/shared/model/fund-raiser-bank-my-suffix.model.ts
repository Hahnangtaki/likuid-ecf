import { ICampaignPaymentMySuffix } from 'app/shared/model/campaign-payment-my-suffix.model';

export interface IFundRaiserBankMySuffix {
  id?: number;
  bankAccountNo?: string;
  bankAccountName?: string;
  bankBranch?: string;
  status?: string;
  campaignPayments?: ICampaignPaymentMySuffix[];
  fundRaiserId?: number;
  bankId?: number;
  currencyId?: number;
}

export const defaultValue: Readonly<IFundRaiserBankMySuffix> = {};
