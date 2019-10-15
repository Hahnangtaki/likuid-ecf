import { Moment } from 'moment';

export interface IInvestorInstitutionMySuffix {
  id?: number;
  sid?: string;
  companyName?: string;
  bicCode?: string;
  legalDomicileId?: number;
  npwp?: string;
  npwpRegDate?: Moment;
  skd?: string;
  skdExpDate?: Moment;
  companyEstablishPlce?: string;
  companyEstablishDate?: Moment;
  businessType?: string;
  companyChracteristic?: string;
  fundSource?: string;
  fundSourceText?: string;
  articleAssociation?: string;
  bussinessRegNo?: string;
  financialAsset1?: number;
  financialAsset2?: number;
  financialAsset3?: number;
  operatingProfit1?: number;
  operatingProfit2?: number;
  operatingProfit3?: number;
  description?: string;
  investmentObjective?: string;
  directSid?: string;
  assetOwner?: string;
  supDocType?: string;
  supDocExpDate?: Moment;
  investorId?: number;
  taxId?: number;
}

export const defaultValue: Readonly<IInvestorInstitutionMySuffix> = {};
