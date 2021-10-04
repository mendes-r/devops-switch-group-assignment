import {fetchFamiliesFromWS, fetchFamilyMembersFromWS, fetchPersonRelationsFromWS} from "./AppService";
import axios from 'axios';

export const GET_MEMBERS_STARTED = "GET_MEMBERS_STARTED";
export const GET_MEMBERS_SUCCESS = "GET_MEMBERS_SUCCESS";
export const GET_MEMBERS_FAILURE = "GET_MEMBERS_FAILURE";

export const GET_FAMILIES_STARTED = "GET_FAMILIES_STARTED";
export const GET_FAMILIES_SUCCESS = "GET_FAMILIES_SUCCESS";
export const GET_FAMILIES_FAILURE = "GET_FAMILIES_FAILURE";
export const GET_FAMILIES_REFRESH = "GET_FAMILIES_REFRESH";

export const GET_FAMILY_RELATIONS_LIST_BY_ID_STARTED = "GET_FAMILY_RELATIONS_LIST_BY_ID_STARTED";
export const GET_FAMILY_RELATIONS_LIST_BY_ID_SUCCESS = "GET_FAMILY_RELATIONS_LIST_BY_ID_SUCCESS";
export const GET_FAMILY_RELATIONS_LIST_BY_ID_FAILURE = "GET_FAMILY_RELATIONS_LIST_BY_ID_FAILURE";

export const GET_USER_PROFILE_STARTED = "GET_USER_PROFILE_STARTED";
export const GET_USER_PROFILE_SUCCESS = "GET_USER_PROFILE_SUCCESS";
export const GET_USER_PROFILE_FAILURE = "GET_USER_PROFILE_FAILURE";

export const ADD_FAMILY_STARTED = "ADD_FAMILY_STARTED_TYPE"
export const ADD_FAMILY_SUCCESS = "ADD_FAMILY_SUCCESS_TYPE"
export const ADD_FAMILY_FAILURE = "ADD_FAMILY_FAILURE_TYPE"

export const ADD_PERSON_STARTED = "ADD_PERSON_STARTED_TYPE"
export const ADD_PERSON_SUCCESS = "ADD_PERSON_SUCCESS_TYPE"
export const ADD_PERSON_FAILURE = "ADD_PERSON_FAILURE_TYPE"

export const ADD_RELATION_STARTED = "ADD_RELATION_STARTED_TYPE"
export const ADD_RELATION_SUCCESS = "ADD_RELATION_SUCCESS_TYPE"
export const ADD_RELATION_FAILURE = "ADD_RELATION_FAILURE_TYPE"

export const GET_FAMILY_PROFILE_STARTED = "GET_FAMILY_PROFILE_STARTED_TYPE"
export const GET_FAMILY_PROFILE_SUCCESS = "GET_FAMILY_PROFILE_SUCCESS_TYPE"
export const GET_FAMILY_PROFILE_FAILURE = "GET_FAMILY_PROFILE_FAILURE_TYPE"

export const LOGIN_STARTED = 'LOGIN_STARTED';
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const LOGIN_FAILURE = 'LOGIN_FAILURE';

export const OPEN_FAMILY_FORM = 'OPEN_FAMILY_FORM';
export const CLOSE_FAMILY_FORM = 'CLOSE_FAMILY_FORM';

export const OPEN_PERSON_FORM = 'OPEN_PERSON_FORM';
export const CLOSE_PERSON_FORM = 'CLOSE_PERSON_FORM';

export const OPEN_RELATION_FORM = 'OPEN_RELATION_FORM';
export const CLOSE_RELATION_FORM = 'CLOSE_RELATION_FORM';

export const SIGNUP_STARTED = 'SIGNUP_STARTED';
export const SIGNUP_SUCCESS = 'SIGNUP_SUCCESS';
export const SIGNUP_FAILURE = 'SIGNUP_FAILURE';

export const SAVE_FAMILY_NAME_SUCCESS = 'SAVE_FAMILY_NAME_SUCCESS_TYPE';
export const SAVE_USER_RELATIONS_SUCCESS = 'SAVE_USER_RELATIONS_SUCCESS_TYPE';

export const CLEAN_LOGIN_AND_SIGNUP_STATUS = 'CLEAN_LOGIN_AND_SIGNUP_STATUS';

export const GET_RELATION_TYPES_STARTED = "RELATION_TYPES_STARTED_TYPE";
export const GET_RELATION_TYPES_SUCCESS = "RELATION_TYPES_SUCCESS_TYPE";
export const GET_RELATION_TYPES_FAILURE = "RELATION_TYPES_FAILURE_TYPE";

export function openFamilyForm() {
    return {
        type: OPEN_FAMILY_FORM,
    }
}

export function closeFamilyForm() {
    return {
        type: CLOSE_FAMILY_FORM,
    }
}

export function signIn(credentials, dispatch) {
    console.log('credentials' + credentials);
    dispatch(signInStarted());
    axios.post(`${process.env.REACT_APP_URL_API}/auth/login`, credentials)
        .then((res) => {
            dispatch(signInSuccess(res));
        })
        .catch(error => {
            let errorMessage;
            if (error.message.includes("401")) {
                errorMessage = "Invalid username/password!";
            } else {
                errorMessage = "Unable to connect! Please try again or later.";
            }
            dispatch(signInFailure(errorMessage));
        })
}

export function signInStarted() {
    return {
        type: LOGIN_STARTED,
    };
}

export function signInSuccess(res) {
    return {
        type: LOGIN_SUCCESS,
        payload: {
            user: res,
        },
    };
}

export function signInFailure(message) {
    return {
        type: LOGIN_FAILURE,
        payload: {
            error: message,
        },
    };
}

export function signUp(info, dispatch) {
    dispatch(signUpStarted());
    axios.post(`${process.env.REACT_APP_URL_API}/auth/signup`, info)
        .then(() => {
            dispatch(signUpSuccess("Welcome! Registration completed successfully."));
        })
        .catch(error => {
            let errorMessage;
            if (error.message.includes("400")) {
                errorMessage = error.response.data.errorMessage;
            } else if (error.message.includes("500")) {
                errorMessage = "Failure to register user, please check submitted data!";
            }       else {
                errorMessage = "Unable to connect! Please try again or later.";
            }
            dispatch(signUpFailure(errorMessage));
        })
}

export function signUpStarted() {
    return {
        type: SIGNUP_STARTED,
    };
}

export function signUpSuccess(message) {
    return {
        type: SIGNUP_SUCCESS,
        payload: {
            success: message,
        },
    };
}

export function signUpFailure(message) {
    return {
        type: SIGNUP_FAILURE,
        payload: {
            error: message,
        },
    };
}

    export function openPersonForm() {
        return {
            type: OPEN_PERSON_FORM,
        }
    }

    export function closePersonForm() {
        return {
            type: CLOSE_PERSON_FORM,
        }
    }

    export function openRelationForm() {
        return {
            type: OPEN_RELATION_FORM,
        }
    }

    export function closeRelationForm() {
        return {
            type: CLOSE_RELATION_FORM,
        }
    }


    export function logoutSuccess() {
        return {
            type: LOGIN_STARTED,
        }
    }

    export function logout(dispatch){
        dispatch(logoutSuccess());
    }

    export function saveFamilyName(familyName, dispatch) {
    console.log(familyName);
        dispatch(saveFamilyNameSuccess(familyName));
    }

    export function saveFamilyNameSuccess(familyName){
        return {
            type: SAVE_FAMILY_NAME_SUCCESS,
            payload: {
                familyName: familyName,
            }
        }
    }

    export function saveUserRelations(userrelation, dispatch) {
        console.log("ola ola ")
        console.log(userrelation);
        dispatch(saveUserRelationsSuccess(userrelation));
    }

    export function saveUserRelationsSuccess(userrelation){
        return {
            type: SAVE_USER_RELATIONS_SUCCESS,
            payload: {
                userRelation: userrelation,
            }
        }
    }

    export function getListOfFamilyMembers(dispatch, familyID) {
        dispatch(getListOfFamilyMembersStarted());
        fetchFamilyMembersFromWS(
            (res) => dispatch(getListOfFamilyMembersSuccess(res)),
            (err) => dispatch(getListOfFamilyMembersFailure(err.message)),
            familyID,
        );
    }

    export function getListOfFamilyMembersStarted() {
        return {
            type: GET_MEMBERS_STARTED,
        };
    }

    export function getListOfFamilyMembersSuccess(res) {
        return {
            type: GET_MEMBERS_SUCCESS,
            payload: {
                data: [...res],
            },
        };
    }

    export function getListOfFamilyMembersFailure(message) {
        return {
            type: GET_MEMBERS_FAILURE,
            payload: {
                error: message,
            },
        };
    }

    export function getListOfFamilies(dispatch) {
        dispatch(getListOfFamiliesStarted());
        fetchFamiliesFromWS(
            (res) => dispatch(getListOfFamiliesSuccess(res)),
            (err) => dispatch(getListOfFamiliesFailure(err.message))
        );
    }

    export function getListOfFamiliesStarted() {
        return {
            type: GET_FAMILIES_STARTED,
        };
    }

    export function getListOfFamiliesSuccess(res) {
        return {
            type: GET_FAMILIES_SUCCESS,
            payload: {
                data: [...res],
            },
        };
    }

    export function getListOfFamiliesFailure(message) {
        return {
            type: GET_FAMILIES_FAILURE,
            payload: {
                error: message,
            },
        };
    }

    export function getListOfFamiliesRefresh() {
        return {
            type: GET_FAMILIES_REFRESH,
        };
    }

    export function getFamilyRelations(dispatch, mainEmail) {
        dispatch(getFamilyRelationsListByIdStarted());
        fetchPersonRelationsFromWS(
            (res) => dispatch(getFamilyRelationsListByIdSuccess(res)),
            (error) => dispatch(getFamilyRelationsListByIdFailure(error.message)),
            mainEmail
        );
    }


    export function getFamilyRelationsListByIdStarted() {
        return {
            type: GET_FAMILY_RELATIONS_LIST_BY_ID_STARTED,
        };
    }

    export function getFamilyRelationsListByIdSuccess(res) {
        return {
            type: GET_FAMILY_RELATIONS_LIST_BY_ID_SUCCESS,
            payload: {
                data: [...res.relationList], //Aqui em vez de "relation" deve ser "relationList", ou seja, o que o backend retorna, e não a const criada em Apps.js .
            },
        };
    }

    export function getFamilyRelationsListByIdFailure(message) {
        return {
            type: GET_FAMILY_RELATIONS_LIST_BY_ID_FAILURE,
            payload: {
                error: message,
            },
        };
    }

    export function getUserProfile(dispatch, mainEmail) {

        dispatch(getUserProfileStarted());
        fetch(`${process.env.REACT_APP_URL_API}/users/${mainEmail}`) //o fetch "fala" com o backend
            .then((res) => res.json()) //transforma a resposta http em formato JSON. Quando termina..
            .then((res) => {
                dispatch(getUserProfileSuccess(res));
            }); //..Faz dispatch da acção

    }

//faltava a linha 10 e actyivar uma extensão nova do CORS "https://mybrowseraddon.com/access-control-allow-origin.html?v=0.1.6&type=install"

    export function getUserProfileStarted() {
        return {
            type: GET_USER_PROFILE_STARTED,
        };
    }

    export function getUserProfileSuccess(res) {
        return {
            type: GET_USER_PROFILE_SUCCESS,
            payload: {
                data: res, //Aqui será um "res" universal para retornar toda a informação que vem do backEnd .
                /*{name: res.name} se quisesse obter informação específica do backend*/
            },
        };
    }

    export function getUserProfileFailure(message) {
        return {
            type: GET_USER_PROFILE_FAILURE,
            payload: {
                error: message,
            },
        };
    }


    export function addFamily(form, dispatch, state) {
        //console.log(JSON.stringify("ola"));
        dispatch(addFamilyStarted());
        let {token}= state.user.data.jwt;
            let config = {
              headers: {
                  'Authorization': `Bearer ${token}`
              }
          }
        axios.post(`${process.env.REACT_APP_URL_API}/families`, form, config)
            .then(() => {
                dispatch(addFamilySuccess());
            })
            .catch(error => {
                console.log(error);
                dispatch(addFamilyFailure(error.response.data.errorMessage));
            })
    }

    export function addFamilyStarted() {
        return {
            type: ADD_FAMILY_STARTED,
        }
    }

    export function addFamilySuccess() {
        return {
            type: ADD_FAMILY_SUCCESS,
        }
    }

    export function addFamilyFailure(message) {
        return {
            type: ADD_FAMILY_FAILURE,
            payload: {
                error: message
            }
        }
    }


    export function addPerson(form, dispatch) {
        dispatch(addPersonStarted());
        axios.post(`${process.env.REACT_APP_URL_API}/users`, form)
            .then(() => {
                dispatch(addPersonSuccess());
            })
            .catch(error => {
                console.log(error)
                dispatch(addPersonFailure(error.response.data));
            })
    }


    export function addPersonStarted() {
        return {
            type: ADD_PERSON_STARTED,
        }
    }

    export function addPersonSuccess() {
        return {
            type: ADD_PERSON_SUCCESS,
        }
    }

    export function addPersonFailure(message) {
        return {
            type: ADD_PERSON_FAILURE,
            payload: {
                error: message
            }
        }
    }

    export function addRelation(form, dispatch, familyID) {
        dispatch(addRelationStarted());
        axios.post(`${process.env.REACT_APP_URL_API}/families/${familyID}/relations`, form)
            .then(() => {
                dispatch(addRelationSuccess());
            })
            .catch(error => {
                dispatch(addRelationFailure(error.response.data));
            })
    }


    export function addRelationStarted() {
        return {
            type: ADD_RELATION_STARTED,
        }
    }

    export function addRelationSuccess() {
        return {
            type: ADD_RELATION_SUCCESS,
        }
    }

    export function addRelationFailure(msg) {
        return {
            type: ADD_RELATION_FAILURE,
            payload: {
                error: msg
            }
        }
    }

    export function getFamilyProfile(dispatch, familyID) {
        dispatch(getFamilyProfileStarted());
        fetch(`${process.env.REACT_APP_URL_API}/families/${familyID}`)
            .then((res) => res.json()) //transforma a resposta http em formato JSON. Quando termina..
            .then((res) => {
                dispatch(getFamilyProfileSuccess(res));
            });
    }

    export function getFamilyProfileStarted() {
        return {
            type: GET_FAMILY_PROFILE_STARTED,
        };
    }

export function getFamilyProfileSuccess(res) {
    return {
        type: GET_FAMILY_PROFILE_SUCCESS,
        payload: {
            data: res,
        },
    };
}

export function getFamilyProfileFailure(message) {
    return {
        type: GET_FAMILY_PROFILE_FAILURE,
        payload: {
            error: message,
        },
    };
}

export function cleanLoginAndSignupStatus(dispatch) {
    dispatch(clearLoginAndSignup());
}

export function clearLoginAndSignup() {
    return {
        type: CLEAN_LOGIN_AND_SIGNUP_STATUS,
    };
}

export function getRelationTypesStarted(){
    return {
        type: GET_RELATION_TYPES_STARTED,
    }
}

export function getRelationTypesSuccess(res){
    return {
        type: GET_RELATION_TYPES_SUCCESS,
        payload: {
            data: [...res.systemRelationsList],
        }
    }
}

export function getRelationTypesFailure(message){
    return {
        type: GET_RELATION_TYPES_FAILURE,
        payload: {
            error: message,
        }
    }
}

export function getRelationTypes(dispatch) {
    dispatch(getRelationTypesStarted());
    fetch(`${process.env.REACT_APP_URL_API}/families/relations/`)
        .then((res) => res.json())
        .then((res) => {
            dispatch(getRelationTypesSuccess(res));
        })
        .catch(error => {
        dispatch(getRelationTypesFailure(error.data));
        })
}

