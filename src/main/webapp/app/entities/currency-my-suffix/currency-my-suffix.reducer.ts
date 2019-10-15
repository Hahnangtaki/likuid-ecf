import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICurrencyMySuffix, defaultValue } from 'app/shared/model/currency-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_CURRENCY_LIST: 'currency/FETCH_CURRENCY_LIST',
  FETCH_CURRENCY: 'currency/FETCH_CURRENCY',
  CREATE_CURRENCY: 'currency/CREATE_CURRENCY',
  UPDATE_CURRENCY: 'currency/UPDATE_CURRENCY',
  DELETE_CURRENCY: 'currency/DELETE_CURRENCY',
  RESET: 'currency/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICurrencyMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CurrencyMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: CurrencyMySuffixState = initialState, action): CurrencyMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CURRENCY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CURRENCY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CURRENCY):
    case REQUEST(ACTION_TYPES.UPDATE_CURRENCY):
    case REQUEST(ACTION_TYPES.DELETE_CURRENCY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CURRENCY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CURRENCY):
    case FAILURE(ACTION_TYPES.CREATE_CURRENCY):
    case FAILURE(ACTION_TYPES.UPDATE_CURRENCY):
    case FAILURE(ACTION_TYPES.DELETE_CURRENCY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CURRENCY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CURRENCY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CURRENCY):
    case SUCCESS(ACTION_TYPES.UPDATE_CURRENCY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CURRENCY):
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

const apiUrl = 'api/currencies';

// Actions

export const getEntities: ICrudGetAllAction<ICurrencyMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CURRENCY_LIST,
  payload: axios.get<ICurrencyMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICurrencyMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CURRENCY,
    payload: axios.get<ICurrencyMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICurrencyMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CURRENCY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICurrencyMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CURRENCY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICurrencyMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CURRENCY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
