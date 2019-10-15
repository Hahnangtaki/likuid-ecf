import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInvestorAddressMySuffix, defaultValue } from 'app/shared/model/investor-address-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_INVESTORADDRESS_LIST: 'investorAddress/FETCH_INVESTORADDRESS_LIST',
  FETCH_INVESTORADDRESS: 'investorAddress/FETCH_INVESTORADDRESS',
  CREATE_INVESTORADDRESS: 'investorAddress/CREATE_INVESTORADDRESS',
  UPDATE_INVESTORADDRESS: 'investorAddress/UPDATE_INVESTORADDRESS',
  DELETE_INVESTORADDRESS: 'investorAddress/DELETE_INVESTORADDRESS',
  RESET: 'investorAddress/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInvestorAddressMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InvestorAddressMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: InvestorAddressMySuffixState = initialState, action): InvestorAddressMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INVESTORADDRESS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INVESTORADDRESS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INVESTORADDRESS):
    case REQUEST(ACTION_TYPES.UPDATE_INVESTORADDRESS):
    case REQUEST(ACTION_TYPES.DELETE_INVESTORADDRESS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INVESTORADDRESS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INVESTORADDRESS):
    case FAILURE(ACTION_TYPES.CREATE_INVESTORADDRESS):
    case FAILURE(ACTION_TYPES.UPDATE_INVESTORADDRESS):
    case FAILURE(ACTION_TYPES.DELETE_INVESTORADDRESS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INVESTORADDRESS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INVESTORADDRESS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INVESTORADDRESS):
    case SUCCESS(ACTION_TYPES.UPDATE_INVESTORADDRESS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INVESTORADDRESS):
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

const apiUrl = 'api/investor-addresses';

// Actions

export const getEntities: ICrudGetAllAction<IInvestorAddressMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INVESTORADDRESS_LIST,
  payload: axios.get<IInvestorAddressMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IInvestorAddressMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INVESTORADDRESS,
    payload: axios.get<IInvestorAddressMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInvestorAddressMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INVESTORADDRESS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInvestorAddressMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INVESTORADDRESS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInvestorAddressMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INVESTORADDRESS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
