import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInvestorBankMySuffix, defaultValue } from 'app/shared/model/investor-bank-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_INVESTORBANK_LIST: 'investorBank/FETCH_INVESTORBANK_LIST',
  FETCH_INVESTORBANK: 'investorBank/FETCH_INVESTORBANK',
  CREATE_INVESTORBANK: 'investorBank/CREATE_INVESTORBANK',
  UPDATE_INVESTORBANK: 'investorBank/UPDATE_INVESTORBANK',
  DELETE_INVESTORBANK: 'investorBank/DELETE_INVESTORBANK',
  RESET: 'investorBank/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInvestorBankMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InvestorBankMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: InvestorBankMySuffixState = initialState, action): InvestorBankMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INVESTORBANK_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INVESTORBANK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INVESTORBANK):
    case REQUEST(ACTION_TYPES.UPDATE_INVESTORBANK):
    case REQUEST(ACTION_TYPES.DELETE_INVESTORBANK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INVESTORBANK_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INVESTORBANK):
    case FAILURE(ACTION_TYPES.CREATE_INVESTORBANK):
    case FAILURE(ACTION_TYPES.UPDATE_INVESTORBANK):
    case FAILURE(ACTION_TYPES.DELETE_INVESTORBANK):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INVESTORBANK_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INVESTORBANK):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INVESTORBANK):
    case SUCCESS(ACTION_TYPES.UPDATE_INVESTORBANK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INVESTORBANK):
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

const apiUrl = 'api/investor-banks';

// Actions

export const getEntities: ICrudGetAllAction<IInvestorBankMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INVESTORBANK_LIST,
  payload: axios.get<IInvestorBankMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IInvestorBankMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INVESTORBANK,
    payload: axios.get<IInvestorBankMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInvestorBankMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INVESTORBANK,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInvestorBankMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INVESTORBANK,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInvestorBankMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INVESTORBANK,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
