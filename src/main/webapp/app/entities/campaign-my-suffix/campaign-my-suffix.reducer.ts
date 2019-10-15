import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICampaignMySuffix, defaultValue } from 'app/shared/model/campaign-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_CAMPAIGN_LIST: 'campaign/FETCH_CAMPAIGN_LIST',
  FETCH_CAMPAIGN: 'campaign/FETCH_CAMPAIGN',
  CREATE_CAMPAIGN: 'campaign/CREATE_CAMPAIGN',
  UPDATE_CAMPAIGN: 'campaign/UPDATE_CAMPAIGN',
  DELETE_CAMPAIGN: 'campaign/DELETE_CAMPAIGN',
  SET_BLOB: 'campaign/SET_BLOB',
  RESET: 'campaign/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICampaignMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CampaignMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: CampaignMySuffixState = initialState, action): CampaignMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CAMPAIGN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CAMPAIGN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CAMPAIGN):
    case REQUEST(ACTION_TYPES.UPDATE_CAMPAIGN):
    case REQUEST(ACTION_TYPES.DELETE_CAMPAIGN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CAMPAIGN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CAMPAIGN):
    case FAILURE(ACTION_TYPES.CREATE_CAMPAIGN):
    case FAILURE(ACTION_TYPES.UPDATE_CAMPAIGN):
    case FAILURE(ACTION_TYPES.DELETE_CAMPAIGN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAMPAIGN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAMPAIGN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CAMPAIGN):
    case SUCCESS(ACTION_TYPES.UPDATE_CAMPAIGN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CAMPAIGN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType
        }
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/campaigns';

// Actions

export const getEntities: ICrudGetAllAction<ICampaignMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CAMPAIGN_LIST,
  payload: axios.get<ICampaignMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICampaignMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CAMPAIGN,
    payload: axios.get<ICampaignMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICampaignMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CAMPAIGN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICampaignMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CAMPAIGN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICampaignMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CAMPAIGN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType
  }
});

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
