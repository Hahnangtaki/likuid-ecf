export interface IInvestorAddressMySuffix {
  id?: number;
  addressType?: string;
  address1?: string;
  address2?: string;
  address3?: string;
  postalCode?: string;
  phone?: string;
  mobilePhone?: string;
  email?: string;
  fax?: string;
  cityId?: number;
  provinceId?: number;
  countryId?: number;
  investorId?: number;
}

export const defaultValue: Readonly<IInvestorAddressMySuffix> = {};
