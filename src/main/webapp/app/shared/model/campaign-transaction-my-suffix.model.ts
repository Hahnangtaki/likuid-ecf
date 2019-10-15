import { Moment } from 'moment';

export interface ICampaignTransactionMySuffix {
  id?: number;
  transCode?: string;
  transDesc?: string;
  transDate?: Moment;
  transType?: string;
  qty?: number;
  amount?: number;
  uniqueCharges?: number;
  netAmount?: number;
  status?: string;
  investorId?: number;
  campaignId?: number;
}

export const defaultValue: Readonly<ICampaignTransactionMySuffix> = {};
