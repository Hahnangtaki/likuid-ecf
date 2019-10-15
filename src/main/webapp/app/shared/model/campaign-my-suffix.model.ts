import { Moment } from 'moment';
import { ICampaignPaymentMySuffix } from 'app/shared/model/campaign-payment-my-suffix.model';
import { ICampaignInvestorMySuffix } from 'app/shared/model/campaign-investor-my-suffix.model';
import { ICampaignTransactionMySuffix } from 'app/shared/model/campaign-transaction-my-suffix.model';
import { ICampaignCategoryMySuffix } from 'app/shared/model/campaign-category-my-suffix.model';

export interface ICampaignMySuffix {
  id?: number;
  campaignCode?: string;
  campaignName?: string;
  campaignDesc?: string;
  investmentType?: string;
  offerDate?: Moment;
  deadlineDate?: Moment;
  price?: number;
  fundTarget?: number;
  fundRaised?: number;
  fundPaid?: number;
  qtyTarget?: number;
  qtyRaised?: number;
  estRoiMin?: number;
  estRoiMax?: number;
  prospectusFileContentType?: string;
  prospectusFile?: any;
  status?: string;
  campaignPayments?: ICampaignPaymentMySuffix[];
  campaignInvestors?: ICampaignInvestorMySuffix[];
  campaignTransactions?: ICampaignTransactionMySuffix[];
  campaignCategories?: ICampaignCategoryMySuffix[];
  currencyId?: number;
  fundRaiserId?: number;
}

export const defaultValue: Readonly<ICampaignMySuffix> = {};
