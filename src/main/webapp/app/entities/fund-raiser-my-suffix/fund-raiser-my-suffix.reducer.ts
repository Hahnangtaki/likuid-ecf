import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFundRaiserMySuffix, defaultValue } from 'app/shared/model/fund-raiser-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_FUNDRAISER_LIST: 'fundRaiser/FETCH_FUNDRAISER_LIST',
  FETCH_FUNDRAISER: 'fundRaiser/FETCH_FUNDRAISER',
  CREATE_FUNDRAISER: 'fundRaiser/CREATE_FUNDRAISER',
  UPDATE_FUNDRAISER: 'fundRaiser/UPDATE_FUNDRAISER',
  DELETE_FUNDRAISER: 'fundRaiser/DELETE_FUNDRAISER',
  RESET: 'fundRaiser/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFundRaiserMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FundRaiserMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: FundRaiserMySuffixState = initialState, action): FundRaiserMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FUNDRAISER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FUNDRAISER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FUNDRAISER):
    case REQUEST(ACTION_TYPES.UPDATE_FUNDRAISER):
    case REQUEST(ACTION_TYPES.DELETE_FUNDRAISER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FUNDRAISER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FUNDRAISER):
    case FAILURE(ACTION_TYPES.CREATE_FUNDRAISER):
    case FAILURE(ACTION_TYPES.UPDATE_FUNDRAISER):
    case FAILURE(ACTION_TYPES.DELETE_FUNDRAISER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FUNDRAISER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FUNDRAISER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FUNDRAISER):
    case SUCCESS(ACTION_TYPES.UPDATE_FUNDRAISER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FUNDRAISER):
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

const apiUrl = 'api/fund-raisers';

// Actions

export const getEntities: ICrudGetAllAction<IFundRaiserMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FUNDRAISER_LIST,
  payload: axios.get<IFundRaiserMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFundRaiserMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FUNDRAISER,
    payload: axios.get<IFundRaiserMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFundRaiserMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FUNDRAISER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFundRaiserMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FUNDRAISER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFundRaiserMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FUNDRAISER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
