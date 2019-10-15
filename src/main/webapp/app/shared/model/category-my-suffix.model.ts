import { ICampaignCategoryMySuffix } from 'app/shared/model/campaign-category-my-suffix.model';

export interface ICategoryMySuffix {
  id?: number;
  catCode?: string;
  catName?: string;
  campaignCategories?: ICampaignCategoryMySuffix[];
}

export const defaultValue: Readonly<ICategoryMySuffix> = {};
