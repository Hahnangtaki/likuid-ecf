import { Moment } from 'moment';

export interface IOtpHistoryMySuffix {
  id?: number;
  createdBy?: number;
  createdDate?: Moment;
  expiredDate?: Moment;
  memberEmail?: string;
  phoneNumber?: string;
  procType?: number;
  tokenText?: string;
  token?: string;
  retryCount?: number;
  retryMax?: number;
  requestDataContentType?: string;
  requestData?: any;
  memberAccountId?: number;
}

export const defaultValue: Readonly<IOtpHistoryMySuffix> = {};
