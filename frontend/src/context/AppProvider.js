import {Provider} from "./AppContext";
import React, {useReducer} from "react";
import PropTypes from "prop-types";
import reducer from "./AppReducer";

const initialState = {
    isLogged: false,
    user: [],
    familyName: "",
    userRelation: "",
    login: {
        status: null
    },
    members: {
        refresh: false,
        loading: true,
        error: null,
        data: [],
    },
    families: {
        refresh: false,
        loading: true,
        error: null,
        data: [],
    },
    relations:  {
        refresh: false,
        loading: true,
        error: null,
        data: [],
    },
    profile: {},
    familyForm: {
        openForm: false,
        result: null,
    },
    signup: {
        statusFailure: null,
        statusSuccess: null,
        statusLoading: null
    },
    personForm: {
        openForm: false,
        result: null
    },
    relationForm: {
        loading: false,
        error: null,
    },
    familyProfile: {},
    systemRelationList: {
        error: null,
        data: [],
    },
};

const headers = {
    email: "Email",
    name: "Name",
    vat: "VAT",
    birthDate: "Birthdate",
    mainEmail: "Main Email",
    otherEmails: "Other Emails",
    telephoneNumbers: "Telephone Numbers",
    houseNumber: "House Number",
    street: "Street",
    city: "City",
    country: "Country",
    zipCode: "Zip Code",
};


const headers1 = {
    familyName: "FamilyName: ",
    registrationDate: "RegistrationDate: ",
    administratorID: "Administrator: ",
};


const AppProvider = (props) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <Provider
            value={{
                state,
                headers,
                headers1,
                dispatch,
            }}
        >
            {props.children}
        </Provider>
    );
};

AppProvider.propTypes = {
    children: PropTypes.node,
};

export default AppProvider;
