import { Moment } from 'moment';

export interface IInvestorIndividuMySuffix {
  id?: number;
  sid?: string;
  firstName?: string;
  middleName?: string;
  lastName?: string;
  nationalityId?: number;
  ktp?: string;
  ktpExpDate?: Moment;
  npwp?: string;
  npwpRegDate?: Moment;
  passport?: string;
  passportExpDate?: Moment;
  kitas?: string;
  kitasExpDate?: Moment;
  birthPlace?: string;
  birthDate?: Moment;
  sex?: string;
  maritalStatus?: string;
  spouseName?: string;
  heir?: string;
  heirRalation?: string;
  educationBackground?: string;
  occupation?: string;
  natureOfBusiness?: string;
  annualIncome?: number;
  fundSource?: string;
  fundSourceText?: string;
  description?: string;
  investmentObjective?: string;
  motherMaidenName?: string;
  directSid?: string;
  assetOwner?: string;
  authPersonKtpRegDate?: Moment;
  investorId?: number;
  taxId?: number;
}

export const defaultValue: Readonly<IInvestorIndividuMySuffix> = {};
