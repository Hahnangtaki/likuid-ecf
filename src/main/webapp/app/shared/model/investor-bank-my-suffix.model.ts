export interface IInvestorBankMySuffix {
  id?: number;
  bankAccountNo?: string;
  bankAccountName?: string;
  bankBranch?: string;
  status?: string;
  bankId?: number;
  currencyId?: number;
  investorId?: number;
}

export const defaultValue: Readonly<IInvestorBankMySuffix> = {};
