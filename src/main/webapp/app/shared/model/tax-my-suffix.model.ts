import { IInvestorIndividuMySuffix } from 'app/shared/model/investor-individu-my-suffix.model';
import { IInvestorInstitutionMySuffix } from 'app/shared/model/investor-institution-my-suffix.model';

export interface ITaxMySuffix {
  id?: number;
  taxCode?: string;
  shortDesc?: string;
  longDesc?: string;
  investorIndividus?: IInvestorIndividuMySuffix[];
  investorInstitutions?: IInvestorInstitutionMySuffix[];
}

export const defaultValue: Readonly<ITaxMySuffix> = {};
