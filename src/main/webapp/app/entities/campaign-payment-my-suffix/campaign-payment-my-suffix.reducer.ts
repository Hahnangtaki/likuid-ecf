import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICampaignPaymentMySuffix, defaultValue } from 'app/shared/model/campaign-payment-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_CAMPAIGNPAYMENT_LIST: 'campaignPayment/FETCH_CAMPAIGNPAYMENT_LIST',
  FETCH_CAMPAIGNPAYMENT: 'campaignPayment/FETCH_CAMPAIGNPAYMENT',
  CREATE_CAMPAIGNPAYMENT: 'campaignPayment/CREATE_CAMPAIGNPAYMENT',
  UPDATE_CAMPAIGNPAYMENT: 'campaignPayment/UPDATE_CAMPAIGNPAYMENT',
  DELETE_CAMPAIGNPAYMENT: 'campaignPayment/DELETE_CAMPAIGNPAYMENT',
  RESET: 'campaignPayment/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICampaignPaymentMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CampaignPaymentMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: CampaignPaymentMySuffixState = initialState, action): CampaignPaymentMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CAMPAIGNPAYMENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CAMPAIGNPAYMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CAMPAIGNPAYMENT):
    case REQUEST(ACTION_TYPES.UPDATE_CAMPAIGNPAYMENT):
    case REQUEST(ACTION_TYPES.DELETE_CAMPAIGNPAYMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CAMPAIGNPAYMENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CAMPAIGNPAYMENT):
    case FAILURE(ACTION_TYPES.CREATE_CAMPAIGNPAYMENT):
    case FAILURE(ACTION_TYPES.UPDATE_CAMPAIGNPAYMENT):
    case FAILURE(ACTION_TYPES.DELETE_CAMPAIGNPAYMENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAMPAIGNPAYMENT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAMPAIGNPAYMENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CAMPAIGNPAYMENT):
    case SUCCESS(ACTION_TYPES.UPDATE_CAMPAIGNPAYMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CAMPAIGNPAYMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/campaign-payments';

// Actions

export const getEntities: ICrudGetAllAction<ICampaignPaymentMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CAMPAIGNPAYMENT_LIST,
  payload: axios.get<ICampaignPaymentMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICampaignPaymentMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CAMPAIGNPAYMENT,
    payload: axios.get<ICampaignPaymentMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICampaignPaymentMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CAMPAIGNPAYMENT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICampaignPaymentMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CAMPAIGNPAYMENT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICampaignPaymentMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CAMPAIGNPAYMENT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
