import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
import { IInvestorAddressMySuffix } from 'app/shared/model/investor-address-my-suffix.model';

export interface ICountryMySuffix {
  id?: number;
  countryCode?: string;
  countryName?: string;
  nationality?: string;
  provinces?: IProvinceMySuffix[];
  investorAddresses?: IInvestorAddressMySuffix[];
}

export const defaultValue: Readonly<ICountryMySuffix> = {};
