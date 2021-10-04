import {
  ADD_FAMILY_FAILURE,
  ADD_FAMILY_STARTED,
  ADD_FAMILY_SUCCESS,
  ADD_PERSON_FAILURE,
  ADD_PERSON_STARTED,
  ADD_PERSON_SUCCESS,
  ADD_RELATION_FAILURE,
  ADD_RELATION_STARTED,
  ADD_RELATION_SUCCESS,
  CLEAN_LOGIN_AND_SIGNUP_STATUS,
  CLOSE_FAMILY_FORM,
  CLOSE_PERSON_FORM,
  CLOSE_RELATION_FORM,
  GET_FAMILIES_FAILURE,
  GET_FAMILIES_STARTED,
  GET_FAMILIES_SUCCESS,
  GET_FAMILY_PROFILE_FAILURE,
  GET_FAMILY_PROFILE_STARTED,
  GET_FAMILY_PROFILE_SUCCESS,
  GET_FAMILY_RELATIONS_LIST_BY_ID_FAILURE,
  GET_FAMILY_RELATIONS_LIST_BY_ID_STARTED,
  GET_FAMILY_RELATIONS_LIST_BY_ID_SUCCESS,
  GET_MEMBERS_FAILURE,
  GET_MEMBERS_STARTED,
  GET_MEMBERS_SUCCESS,
  GET_USER_PROFILE_FAILURE,
  GET_USER_PROFILE_STARTED,
  GET_USER_PROFILE_SUCCESS,
  LOGIN_FAILURE,
  LOGIN_STARTED,
  LOGIN_SUCCESS,
  OPEN_FAMILY_FORM,
  OPEN_PERSON_FORM,
  OPEN_RELATION_FORM,
  SAVE_FAMILY_NAME_SUCCESS,
  SAVE_USER_RELATIONS_SUCCESS,
  SIGNUP_FAILURE,
  SIGNUP_STARTED,
  SIGNUP_SUCCESS,
  GET_RELATION_TYPES_SUCCESS,
  GET_RELATION_TYPES_STARTED,
  GET_RELATION_TYPES_FAILURE,
} from "./AppActions";
import axios from "axios";

function reducer(state, action) {
  switch (action.type) {
    case LOGIN_STARTED:
      return {
        ...state,
        login: {
          status: null
        },
        isLogged: false,
        user: [],
      };
    case LOGIN_SUCCESS:
      const {user} = action.payload;
      return {
        ...state,
        login: {
          status: null
        },
        user,
        isLogged: true,
      };
    case LOGIN_FAILURE:
      return {
        ...state,
        login: {
          status: action.payload.error
        },
        isLogged: false,
      };
    case GET_MEMBERS_STARTED:
      return {
        ...state,
        members: {
          formOpen: false,
          loading: true,
          error: null,
          data: [],
        },
      };
    case SAVE_FAMILY_NAME_SUCCESS:
      return {
        ...state,
        familyName: action.payload.familyName
      }
    case SAVE_USER_RELATIONS_SUCCESS:
      return {
        ...state,
        userRelation: action.payload.userRelation
      }
    case GET_MEMBERS_SUCCESS:
      return {
        ...state,
        members: {
          formOpen: false,
          loading: false,
          error: null,
          data: [...action.payload.data],
        },
      };
    case GET_MEMBERS_FAILURE:
      return {
        ...state,
        members: {
          formOpen: false,
          loading: false,
          error: action.payload.error,
          data: [],
        },
      };
    case GET_FAMILIES_STARTED:
      return {
        ...state,
        families: {
          formOpen: false,
          loading: true,
          error: null,
          data: [],
        },
      };
    case GET_FAMILIES_SUCCESS:
      return {
        ...state,
        families: {
          formOpen: false,
          loading: false,
          error: null,
          data: [...action.payload.data],
        },
      };
    case GET_FAMILIES_FAILURE:
      return {
        ...state,
        families: {
          formOpen: false,
          loading: true,
          error: action.payload.data,
          data: [],
        },
      };
    case GET_FAMILY_RELATIONS_LIST_BY_ID_STARTED:
      return {
        ...state,
        relations: {
          formOpen: false,
          loading: true,
          error: null,
          data: [],
        },
      };
    case GET_FAMILY_RELATIONS_LIST_BY_ID_SUCCESS:
      return {
        ...state,
        relations: {
          formOpen: false,
          loading: false,
          error: null,
          data: [...action.payload.data],
        },
      };
    case GET_FAMILY_RELATIONS_LIST_BY_ID_FAILURE:
      return {
        ...state,
        relations: {
          formOpen: false,
          loading: true,
          error: action.payload.data,
          data: [],
        },
      };

    case GET_USER_PROFILE_STARTED:
      return {
        ...state,
        profile: "Started",
      };
    case GET_USER_PROFILE_SUCCESS:
      return {
        ...state,
        profile: action.payload.data,
      };
    case GET_USER_PROFILE_FAILURE:
      return {
        ...state,
        profile: "Failed",
      };

    case ADD_FAMILY_STARTED:
      return {
        ...state,
        familyForm: {
          openForm: true,
          result: "Loading..."
        },
      };
    case ADD_FAMILY_SUCCESS:
      return {
        ...state,
        families: {
          refresh: true,
          loading: false,
          error: null,
          data: [],
        },
        familyForm: {
          openForm: false,
          result: "Success!"
        },
      };
    case ADD_FAMILY_FAILURE:
      return {
        ...state,
        familyForm: {
          openForm: true,
          result: "Error: " + action.payload.error,
        },
      };


    case OPEN_FAMILY_FORM:
      return {
        ...state,
        familyForm: {
          openForm: true,
          loading: false,
          error: null,
          status: null,
        }
      };
    case CLOSE_FAMILY_FORM:
      return {
        ...state,
        familyForm: {
          openForm: false,
          loading: false,
          error: null,
          status: null,
        }
      };


    case OPEN_PERSON_FORM:
      return {
        ...state,
        personForm: {
          openForm: true,
          loading: false,
          error: null,
          status: null,
        }
      };
    case CLOSE_PERSON_FORM:
      return {
        ...state,
        personForm: {
          openForm: false,
          loading: false,
          error: null,
          status: null,
        }
      };


    case OPEN_RELATION_FORM:
      return {
        ...state,
        relationForm: {
          openForm: true,
          loading: false,
          error: null,
          status: null,
        }
      };
    case CLOSE_RELATION_FORM:
      return {
        ...state,
        relationForm: {
          openForm: false,
          loading: false,
          error: null,
          status: null,
        }
      };


    case ADD_PERSON_STARTED:
      return {
        ...state,
        personForm: {
          openForm: true,
          result: "Loading..."
        },
      };
    case ADD_PERSON_SUCCESS:
      return {
        ...state,

        members: {
          refresh: true,
          loading: false,
          error: null,
          data: [],
        },
        personForm: {
          openForm: false,
          result: "Success!"
        },

      };
    case ADD_PERSON_FAILURE:
      return {
        ...state,
        personForm: {
          openForm: true,
          result: action.payload.error
        },
      };

    case ADD_RELATION_STARTED:
      return {
        ...state,
        relationForm: {
          openForm: true,
          result: "Loading..."
        },
      };
    case ADD_RELATION_SUCCESS:
      return {
        ...state,
        relations: {
          refresh: true,
          loading: false,
          error: null,
          data: [],
        },
        relationForm: {
          openForm: false,
          result: "Success!"
        },
      };
    case ADD_RELATION_FAILURE:
      return {
        ...state,
        relationForm: {
          openForm: true,
          result: action.payload.error
        },
      };

    case GET_FAMILY_PROFILE_STARTED:
      return {
        ...state,
        familyProfile: "Started",
      };

    case GET_FAMILY_PROFILE_SUCCESS:
      return {
        ...state,
        familyProfile: action.payload.data,
      };

    case GET_FAMILY_PROFILE_FAILURE:
      return {
        ...state,
        familyProfile: "Failed",
      };

    case SIGNUP_STARTED:
      return {
        ...state,
        signup: {
          statusLoading: "Loading..."
        }
      };

    case SIGNUP_SUCCESS:
      return {
        ...state,
        signup: {
          statusSuccess: action.payload.success
        }
      };
    case SIGNUP_FAILURE:
      return {
        ...state,
        signup: {
          statusFailure: action.payload.error
        }
      };
    case CLEAN_LOGIN_AND_SIGNUP_STATUS:
      return {
        ...state,
        signup: {
          statusFailure: null,
          statusLoading: null,
          statusSuccess: null,
        },
        login: {
          status: null
        }
      }
    case GET_RELATION_TYPES_STARTED:
      return {
        ...state,
        systemRelationList:{
          data: [],
        }
      }
    case GET_RELATION_TYPES_SUCCESS:
      return {
        ...state,
        systemRelationList: {
          data: [...action.payload.data]
        }
      }
    case GET_RELATION_TYPES_FAILURE:
      return {
        ...state,
        systemRelationList: {
          error: action.payload.data,
          data: [],
        }
      }
  }
}

export default reducer;
