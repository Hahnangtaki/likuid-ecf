import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInvestorAuthorizePersonMySuffix, defaultValue } from 'app/shared/model/investor-authorize-person-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_INVESTORAUTHORIZEPERSON_LIST: 'investorAuthorizePerson/FETCH_INVESTORAUTHORIZEPERSON_LIST',
  FETCH_INVESTORAUTHORIZEPERSON: 'investorAuthorizePerson/FETCH_INVESTORAUTHORIZEPERSON',
  CREATE_INVESTORAUTHORIZEPERSON: 'investorAuthorizePerson/CREATE_INVESTORAUTHORIZEPERSON',
  UPDATE_INVESTORAUTHORIZEPERSON: 'investorAuthorizePerson/UPDATE_INVESTORAUTHORIZEPERSON',
  DELETE_INVESTORAUTHORIZEPERSON: 'investorAuthorizePerson/DELETE_INVESTORAUTHORIZEPERSON',
  RESET: 'investorAuthorizePerson/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInvestorAuthorizePersonMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InvestorAuthorizePersonMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: InvestorAuthorizePersonMySuffixState = initialState, action): InvestorAuthorizePersonMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INVESTORAUTHORIZEPERSON_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INVESTORAUTHORIZEPERSON):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INVESTORAUTHORIZEPERSON):
    case REQUEST(ACTION_TYPES.UPDATE_INVESTORAUTHORIZEPERSON):
    case REQUEST(ACTION_TYPES.DELETE_INVESTORAUTHORIZEPERSON):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INVESTORAUTHORIZEPERSON_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INVESTORAUTHORIZEPERSON):
    case FAILURE(ACTION_TYPES.CREATE_INVESTORAUTHORIZEPERSON):
    case FAILURE(ACTION_TYPES.UPDATE_INVESTORAUTHORIZEPERSON):
    case FAILURE(ACTION_TYPES.DELETE_INVESTORAUTHORIZEPERSON):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INVESTORAUTHORIZEPERSON_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INVESTORAUTHORIZEPERSON):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INVESTORAUTHORIZEPERSON):
    case SUCCESS(ACTION_TYPES.UPDATE_INVESTORAUTHORIZEPERSON):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INVESTORAUTHORIZEPERSON):
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

const apiUrl = 'api/investor-authorize-people';

// Actions

export const getEntities: ICrudGetAllAction<IInvestorAuthorizePersonMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INVESTORAUTHORIZEPERSON_LIST,
  payload: axios.get<IInvestorAuthorizePersonMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IInvestorAuthorizePersonMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INVESTORAUTHORIZEPERSON,
    payload: axios.get<IInvestorAuthorizePersonMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInvestorAuthorizePersonMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INVESTORAUTHORIZEPERSON,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInvestorAuthorizePersonMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INVESTORAUTHORIZEPERSON,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInvestorAuthorizePersonMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INVESTORAUTHORIZEPERSON,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
