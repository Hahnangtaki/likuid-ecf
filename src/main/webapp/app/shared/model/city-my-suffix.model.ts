import { IInvestorAddressMySuffix } from 'app/shared/model/investor-address-my-suffix.model';

export interface ICityMySuffix {
  id?: number;
  cityCode?: string;
  cityName?: string;
  investorAddresses?: IInvestorAddressMySuffix[];
  provinceId?: number;
}

export const defaultValue: Readonly<ICityMySuffix> = {};
