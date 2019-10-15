import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInvestorMySuffix, defaultValue } from 'app/shared/model/investor-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_INVESTOR_LIST: 'investor/FETCH_INVESTOR_LIST',
  FETCH_INVESTOR: 'investor/FETCH_INVESTOR',
  CREATE_INVESTOR: 'investor/CREATE_INVESTOR',
  UPDATE_INVESTOR: 'investor/UPDATE_INVESTOR',
  DELETE_INVESTOR: 'investor/DELETE_INVESTOR',
  RESET: 'investor/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInvestorMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InvestorMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: InvestorMySuffixState = initialState, action): InvestorMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INVESTOR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INVESTOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INVESTOR):
    case REQUEST(ACTION_TYPES.UPDATE_INVESTOR):
    case REQUEST(ACTION_TYPES.DELETE_INVESTOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INVESTOR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INVESTOR):
    case FAILURE(ACTION_TYPES.CREATE_INVESTOR):
    case FAILURE(ACTION_TYPES.UPDATE_INVESTOR):
    case FAILURE(ACTION_TYPES.DELETE_INVESTOR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INVESTOR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INVESTOR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INVESTOR):
    case SUCCESS(ACTION_TYPES.UPDATE_INVESTOR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INVESTOR):
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

const apiUrl = 'api/investors';

// Actions

export const getEntities: ICrudGetAllAction<IInvestorMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INVESTOR_LIST,
  payload: axios.get<IInvestorMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IInvestorMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INVESTOR,
    payload: axios.get<IInvestorMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInvestorMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INVESTOR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInvestorMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INVESTOR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInvestorMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INVESTOR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
