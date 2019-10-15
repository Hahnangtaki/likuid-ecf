import { Moment } from 'moment';
import { IOtpHistoryMySuffix } from 'app/shared/model/otp-history-my-suffix.model';

export interface IMemberAccountMySuffix {
  id?: number;
  memberEmail?: string;
  phoneNumber?: string;
  memberSince?: Moment;
  memberStatus?: number;
  otpHistories?: IOtpHistoryMySuffix[];
}

export const defaultValue: Readonly<IMemberAccountMySuffix> = {};
