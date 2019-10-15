import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMemberAccountMySuffix, defaultValue } from 'app/shared/model/member-account-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_MEMBERACCOUNT_LIST: 'memberAccount/FETCH_MEMBERACCOUNT_LIST',
  FETCH_MEMBERACCOUNT: 'memberAccount/FETCH_MEMBERACCOUNT',
  CREATE_MEMBERACCOUNT: 'memberAccount/CREATE_MEMBERACCOUNT',
  UPDATE_MEMBERACCOUNT: 'memberAccount/UPDATE_MEMBERACCOUNT',
  DELETE_MEMBERACCOUNT: 'memberAccount/DELETE_MEMBERACCOUNT',
  RESET: 'memberAccount/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMemberAccountMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type MemberAccountMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: MemberAccountMySuffixState = initialState, action): MemberAccountMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MEMBERACCOUNT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MEMBERACCOUNT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_MEMBERACCOUNT):
    case REQUEST(ACTION_TYPES.UPDATE_MEMBERACCOUNT):
    case REQUEST(ACTION_TYPES.DELETE_MEMBERACCOUNT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_MEMBERACCOUNT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MEMBERACCOUNT):
    case FAILURE(ACTION_TYPES.CREATE_MEMBERACCOUNT):
    case FAILURE(ACTION_TYPES.UPDATE_MEMBERACCOUNT):
    case FAILURE(ACTION_TYPES.DELETE_MEMBERACCOUNT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_MEMBERACCOUNT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MEMBERACCOUNT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_MEMBERACCOUNT):
    case SUCCESS(ACTION_TYPES.UPDATE_MEMBERACCOUNT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_MEMBERACCOUNT):
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

const apiUrl = 'api/member-accounts';

// Actions

export const getEntities: ICrudGetAllAction<IMemberAccountMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MEMBERACCOUNT_LIST,
  payload: axios.get<IMemberAccountMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IMemberAccountMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MEMBERACCOUNT,
    payload: axios.get<IMemberAccountMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IMemberAccountMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MEMBERACCOUNT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMemberAccountMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MEMBERACCOUNT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMemberAccountMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MEMBERACCOUNT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
