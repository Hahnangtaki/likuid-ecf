import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICampaignCategoryMySuffix, defaultValue } from 'app/shared/model/campaign-category-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_CAMPAIGNCATEGORY_LIST: 'campaignCategory/FETCH_CAMPAIGNCATEGORY_LIST',
  FETCH_CAMPAIGNCATEGORY: 'campaignCategory/FETCH_CAMPAIGNCATEGORY',
  CREATE_CAMPAIGNCATEGORY: 'campaignCategory/CREATE_CAMPAIGNCATEGORY',
  UPDATE_CAMPAIGNCATEGORY: 'campaignCategory/UPDATE_CAMPAIGNCATEGORY',
  DELETE_CAMPAIGNCATEGORY: 'campaignCategory/DELETE_CAMPAIGNCATEGORY',
  RESET: 'campaignCategory/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICampaignCategoryMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CampaignCategoryMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: CampaignCategoryMySuffixState = initialState, action): CampaignCategoryMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CAMPAIGNCATEGORY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CAMPAIGNCATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CAMPAIGNCATEGORY):
    case REQUEST(ACTION_TYPES.UPDATE_CAMPAIGNCATEGORY):
    case REQUEST(ACTION_TYPES.DELETE_CAMPAIGNCATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CAMPAIGNCATEGORY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CAMPAIGNCATEGORY):
    case FAILURE(ACTION_TYPES.CREATE_CAMPAIGNCATEGORY):
    case FAILURE(ACTION_TYPES.UPDATE_CAMPAIGNCATEGORY):
    case FAILURE(ACTION_TYPES.DELETE_CAMPAIGNCATEGORY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAMPAIGNCATEGORY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAMPAIGNCATEGORY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CAMPAIGNCATEGORY):
    case SUCCESS(ACTION_TYPES.UPDATE_CAMPAIGNCATEGORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CAMPAIGNCATEGORY):
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

const apiUrl = 'api/campaign-categories';

// Actions

export const getEntities: ICrudGetAllAction<ICampaignCategoryMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CAMPAIGNCATEGORY_LIST,
  payload: axios.get<ICampaignCategoryMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICampaignCategoryMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CAMPAIGNCATEGORY,
    payload: axios.get<ICampaignCategoryMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICampaignCategoryMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CAMPAIGNCATEGORY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICampaignCategoryMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CAMPAIGNCATEGORY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICampaignCategoryMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CAMPAIGNCATEGORY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
