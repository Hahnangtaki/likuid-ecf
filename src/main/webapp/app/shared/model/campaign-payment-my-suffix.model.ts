export interface ICampaignPaymentMySuffix {
  id?: number;
  paymentCode?: string;
  paymentDesc?: string;
  amount?: number;
  status?: string;
  fundRaiserBankId?: number;
  campaignId?: number;
  companyBankId?: number;
}

export const defaultValue: Readonly<ICampaignPaymentMySuffix> = {};
