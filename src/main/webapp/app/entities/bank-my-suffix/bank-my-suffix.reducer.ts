import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBankMySuffix, defaultValue } from 'app/shared/model/bank-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_BANK_LIST: 'bank/FETCH_BANK_LIST',
  FETCH_BANK: 'bank/FETCH_BANK',
  CREATE_BANK: 'bank/CREATE_BANK',
  UPDATE_BANK: 'bank/UPDATE_BANK',
  DELETE_BANK: 'bank/DELETE_BANK',
  RESET: 'bank/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBankMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type BankMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: BankMySuffixState = initialState, action): BankMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BANK_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BANK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BANK):
    case REQUEST(ACTION_TYPES.UPDATE_BANK):
    case REQUEST(ACTION_TYPES.DELETE_BANK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_BANK_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BANK):
    case FAILURE(ACTION_TYPES.CREATE_BANK):
    case FAILURE(ACTION_TYPES.UPDATE_BANK):
    case FAILURE(ACTION_TYPES.DELETE_BANK):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_BANK_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BANK):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BANK):
    case SUCCESS(ACTION_TYPES.UPDATE_BANK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BANK):
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

const apiUrl = 'api/banks';

// Actions

export const getEntities: ICrudGetAllAction<IBankMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BANK_LIST,
  payload: axios.get<IBankMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IBankMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BANK,
    payload: axios.get<IBankMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBankMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BANK,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBankMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BANK,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBankMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BANK,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
