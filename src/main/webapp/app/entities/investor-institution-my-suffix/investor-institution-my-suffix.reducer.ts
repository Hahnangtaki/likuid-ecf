import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInvestorInstitutionMySuffix, defaultValue } from 'app/shared/model/investor-institution-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_INVESTORINSTITUTION_LIST: 'investorInstitution/FETCH_INVESTORINSTITUTION_LIST',
  FETCH_INVESTORINSTITUTION: 'investorInstitution/FETCH_INVESTORINSTITUTION',
  CREATE_INVESTORINSTITUTION: 'investorInstitution/CREATE_INVESTORINSTITUTION',
  UPDATE_INVESTORINSTITUTION: 'investorInstitution/UPDATE_INVESTORINSTITUTION',
  DELETE_INVESTORINSTITUTION: 'investorInstitution/DELETE_INVESTORINSTITUTION',
  RESET: 'investorInstitution/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInvestorInstitutionMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InvestorInstitutionMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: InvestorInstitutionMySuffixState = initialState, action): InvestorInstitutionMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INVESTORINSTITUTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INVESTORINSTITUTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INVESTORINSTITUTION):
    case REQUEST(ACTION_TYPES.UPDATE_INVESTORINSTITUTION):
    case REQUEST(ACTION_TYPES.DELETE_INVESTORINSTITUTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INVESTORINSTITUTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INVESTORINSTITUTION):
    case FAILURE(ACTION_TYPES.CREATE_INVESTORINSTITUTION):
    case FAILURE(ACTION_TYPES.UPDATE_INVESTORINSTITUTION):
    case FAILURE(ACTION_TYPES.DELETE_INVESTORINSTITUTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INVESTORINSTITUTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INVESTORINSTITUTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INVESTORINSTITUTION):
    case SUCCESS(ACTION_TYPES.UPDATE_INVESTORINSTITUTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INVESTORINSTITUTION):
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

const apiUrl = 'api/investor-institutions';

// Actions

export const getEntities: ICrudGetAllAction<IInvestorInstitutionMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INVESTORINSTITUTION_LIST,
  payload: axios.get<IInvestorInstitutionMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IInvestorInstitutionMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INVESTORINSTITUTION,
    payload: axios.get<IInvestorInstitutionMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInvestorInstitutionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INVESTORINSTITUTION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInvestorInstitutionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INVESTORINSTITUTION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInvestorInstitutionMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INVESTORINSTITUTION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
