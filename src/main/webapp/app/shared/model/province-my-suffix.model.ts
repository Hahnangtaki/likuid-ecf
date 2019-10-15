import { ICityMySuffix } from 'app/shared/model/city-my-suffix.model';
import { IInvestorAddressMySuffix } from 'app/shared/model/investor-address-my-suffix.model';

export interface IProvinceMySuffix {
  id?: number;
  provinceCode?: string;
  provinceName?: string;
  cities?: ICityMySuffix[];
  investorAddresses?: IInvestorAddressMySuffix[];
  countryId?: number;
}

export const defaultValue: Readonly<IProvinceMySuffix> = {};
