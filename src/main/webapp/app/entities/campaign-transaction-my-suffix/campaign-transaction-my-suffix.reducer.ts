import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICampaignTransactionMySuffix, defaultValue } from 'app/shared/model/campaign-transaction-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_CAMPAIGNTRANSACTION_LIST: 'campaignTransaction/FETCH_CAMPAIGNTRANSACTION_LIST',
  FETCH_CAMPAIGNTRANSACTION: 'campaignTransaction/FETCH_CAMPAIGNTRANSACTION',
  CREATE_CAMPAIGNTRANSACTION: 'campaignTransaction/CREATE_CAMPAIGNTRANSACTION',
  UPDATE_CAMPAIGNTRANSACTION: 'campaignTransaction/UPDATE_CAMPAIGNTRANSACTION',
  DELETE_CAMPAIGNTRANSACTION: 'campaignTransaction/DELETE_CAMPAIGNTRANSACTION',
  RESET: 'campaignTransaction/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICampaignTransactionMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CampaignTransactionMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: CampaignTransactionMySuffixState = initialState, action): CampaignTransactionMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CAMPAIGNTRANSACTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CAMPAIGNTRANSACTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CAMPAIGNTRANSACTION):
    case REQUEST(ACTION_TYPES.UPDATE_CAMPAIGNTRANSACTION):
    case REQUEST(ACTION_TYPES.DELETE_CAMPAIGNTRANSACTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CAMPAIGNTRANSACTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CAMPAIGNTRANSACTION):
    case FAILURE(ACTION_TYPES.CREATE_CAMPAIGNTRANSACTION):
    case FAILURE(ACTION_TYPES.UPDATE_CAMPAIGNTRANSACTION):
    case FAILURE(ACTION_TYPES.DELETE_CAMPAIGNTRANSACTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAMPAIGNTRANSACTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAMPAIGNTRANSACTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CAMPAIGNTRANSACTION):
    case SUCCESS(ACTION_TYPES.UPDATE_CAMPAIGNTRANSACTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CAMPAIGNTRANSACTION):
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

const apiUrl = 'api/campaign-transactions';

// Actions

export const getEntities: ICrudGetAllAction<ICampaignTransactionMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CAMPAIGNTRANSACTION_LIST,
  payload: axios.get<ICampaignTransactionMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICampaignTransactionMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CAMPAIGNTRANSACTION,
    payload: axios.get<ICampaignTransactionMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICampaignTransactionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CAMPAIGNTRANSACTION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICampaignTransactionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CAMPAIGNTRANSACTION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICampaignTransactionMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CAMPAIGNTRANSACTION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
