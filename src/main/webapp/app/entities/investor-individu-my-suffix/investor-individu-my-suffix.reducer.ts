import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInvestorIndividuMySuffix, defaultValue } from 'app/shared/model/investor-individu-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_INVESTORINDIVIDU_LIST: 'investorIndividu/FETCH_INVESTORINDIVIDU_LIST',
  FETCH_INVESTORINDIVIDU: 'investorIndividu/FETCH_INVESTORINDIVIDU',
  CREATE_INVESTORINDIVIDU: 'investorIndividu/CREATE_INVESTORINDIVIDU',
  UPDATE_INVESTORINDIVIDU: 'investorIndividu/UPDATE_INVESTORINDIVIDU',
  DELETE_INVESTORINDIVIDU: 'investorIndividu/DELETE_INVESTORINDIVIDU',
  RESET: 'investorIndividu/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInvestorIndividuMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InvestorIndividuMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: InvestorIndividuMySuffixState = initialState, action): InvestorIndividuMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INVESTORINDIVIDU_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INVESTORINDIVIDU):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INVESTORINDIVIDU):
    case REQUEST(ACTION_TYPES.UPDATE_INVESTORINDIVIDU):
    case REQUEST(ACTION_TYPES.DELETE_INVESTORINDIVIDU):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INVESTORINDIVIDU_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INVESTORINDIVIDU):
    case FAILURE(ACTION_TYPES.CREATE_INVESTORINDIVIDU):
    case FAILURE(ACTION_TYPES.UPDATE_INVESTORINDIVIDU):
    case FAILURE(ACTION_TYPES.DELETE_INVESTORINDIVIDU):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INVESTORINDIVIDU_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INVESTORINDIVIDU):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INVESTORINDIVIDU):
    case SUCCESS(ACTION_TYPES.UPDATE_INVESTORINDIVIDU):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INVESTORINDIVIDU):
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

const apiUrl = 'api/investor-individus';

// Actions

export const getEntities: ICrudGetAllAction<IInvestorIndividuMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INVESTORINDIVIDU_LIST,
  payload: axios.get<IInvestorIndividuMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IInvestorIndividuMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INVESTORINDIVIDU,
    payload: axios.get<IInvestorIndividuMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInvestorIndividuMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INVESTORINDIVIDU,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInvestorIndividuMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INVESTORINDIVIDU,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInvestorIndividuMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INVESTORINDIVIDU,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
