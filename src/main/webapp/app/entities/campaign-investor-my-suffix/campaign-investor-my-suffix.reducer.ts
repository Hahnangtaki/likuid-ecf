import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICampaignInvestorMySuffix, defaultValue } from 'app/shared/model/campaign-investor-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_CAMPAIGNINVESTOR_LIST: 'campaignInvestor/FETCH_CAMPAIGNINVESTOR_LIST',
  FETCH_CAMPAIGNINVESTOR: 'campaignInvestor/FETCH_CAMPAIGNINVESTOR',
  CREATE_CAMPAIGNINVESTOR: 'campaignInvestor/CREATE_CAMPAIGNINVESTOR',
  UPDATE_CAMPAIGNINVESTOR: 'campaignInvestor/UPDATE_CAMPAIGNINVESTOR',
  DELETE_CAMPAIGNINVESTOR: 'campaignInvestor/DELETE_CAMPAIGNINVESTOR',
  RESET: 'campaignInvestor/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICampaignInvestorMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CampaignInvestorMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: CampaignInvestorMySuffixState = initialState, action): CampaignInvestorMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CAMPAIGNINVESTOR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CAMPAIGNINVESTOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CAMPAIGNINVESTOR):
    case REQUEST(ACTION_TYPES.UPDATE_CAMPAIGNINVESTOR):
    case REQUEST(ACTION_TYPES.DELETE_CAMPAIGNINVESTOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CAMPAIGNINVESTOR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CAMPAIGNINVESTOR):
    case FAILURE(ACTION_TYPES.CREATE_CAMPAIGNINVESTOR):
    case FAILURE(ACTION_TYPES.UPDATE_CAMPAIGNINVESTOR):
    case FAILURE(ACTION_TYPES.DELETE_CAMPAIGNINVESTOR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAMPAIGNINVESTOR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAMPAIGNINVESTOR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CAMPAIGNINVESTOR):
    case SUCCESS(ACTION_TYPES.UPDATE_CAMPAIGNINVESTOR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CAMPAIGNINVESTOR):
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

const apiUrl = 'api/campaign-investors';

// Actions

export const getEntities: ICrudGetAllAction<ICampaignInvestorMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CAMPAIGNINVESTOR_LIST,
  payload: axios.get<ICampaignInvestorMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICampaignInvestorMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CAMPAIGNINVESTOR,
    payload: axios.get<ICampaignInvestorMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICampaignInvestorMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CAMPAIGNINVESTOR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICampaignInvestorMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CAMPAIGNINVESTOR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICampaignInvestorMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CAMPAIGNINVESTOR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
