import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFundRaiserBankMySuffix, defaultValue } from 'app/shared/model/fund-raiser-bank-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_FUNDRAISERBANK_LIST: 'fundRaiserBank/FETCH_FUNDRAISERBANK_LIST',
  FETCH_FUNDRAISERBANK: 'fundRaiserBank/FETCH_FUNDRAISERBANK',
  CREATE_FUNDRAISERBANK: 'fundRaiserBank/CREATE_FUNDRAISERBANK',
  UPDATE_FUNDRAISERBANK: 'fundRaiserBank/UPDATE_FUNDRAISERBANK',
  DELETE_FUNDRAISERBANK: 'fundRaiserBank/DELETE_FUNDRAISERBANK',
  RESET: 'fundRaiserBank/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFundRaiserBankMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FundRaiserBankMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: FundRaiserBankMySuffixState = initialState, action): FundRaiserBankMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FUNDRAISERBANK_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FUNDRAISERBANK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FUNDRAISERBANK):
    case REQUEST(ACTION_TYPES.UPDATE_FUNDRAISERBANK):
    case REQUEST(ACTION_TYPES.DELETE_FUNDRAISERBANK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FUNDRAISERBANK_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FUNDRAISERBANK):
    case FAILURE(ACTION_TYPES.CREATE_FUNDRAISERBANK):
    case FAILURE(ACTION_TYPES.UPDATE_FUNDRAISERBANK):
    case FAILURE(ACTION_TYPES.DELETE_FUNDRAISERBANK):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FUNDRAISERBANK_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FUNDRAISERBANK):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FUNDRAISERBANK):
    case SUCCESS(ACTION_TYPES.UPDATE_FUNDRAISERBANK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FUNDRAISERBANK):
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

const apiUrl = 'api/fund-raiser-banks';

// Actions

export const getEntities: ICrudGetAllAction<IFundRaiserBankMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FUNDRAISERBANK_LIST,
  payload: axios.get<IFundRaiserBankMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFundRaiserBankMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FUNDRAISERBANK,
    payload: axios.get<IFundRaiserBankMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFundRaiserBankMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FUNDRAISERBANK,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFundRaiserBankMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FUNDRAISERBANK,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFundRaiserBankMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FUNDRAISERBANK,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
