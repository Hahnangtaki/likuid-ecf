import { IFundRaiserBankMySuffix } from 'app/shared/model/fund-raiser-bank-my-suffix.model';
import { ICampaignMySuffix } from 'app/shared/model/campaign-my-suffix.model';

export interface IFundRaiserMySuffix {
  id?: number;
  companyName?: string;
  yearFounded?: number;
  website?: string;
  fundRaiserBanks?: IFundRaiserBankMySuffix[];
  campaigns?: ICampaignMySuffix[];
}

export const defaultValue: Readonly<IFundRaiserMySuffix> = {};
