import { Moment } from 'moment';

export interface IInvestorAuthorizePersonMySuffix {
  id?: number;
  firstName?: string;
  middleName?: string;
  lastName?: string;
  position?: string;
  ktp?: string;
  ktpExpDate?: Moment;
  npwp?: string;
  npwpRegDate?: Moment;
  passport?: string;
  passportExpDate?: Moment;
  kitas?: string;
  kitasExpDate?: Moment;
  investorId?: number;
}

export const defaultValue: Readonly<IInvestorAuthorizePersonMySuffix> = {};
