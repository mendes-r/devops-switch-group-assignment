import React from 'react';
import "./App.css";
import "./context/AppActions";
import "./context/AppContext";
import ViewProfilePage from "./components/ViewProfilePage";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import ViewRelationsFromPerson from "./components/ViewRelationsFromPerson";
import FamilyForm from "./components/FamilyForm";
import PersonForm from "./components/PersonForm";
import FamiliesTable from "./components/FamiliesTable";
import FamilyMembersTable from "./components/FamilyMembersTable";
import HomePage from "./components/pages/HomePage";
import LoginForm from "./components/LoginForm";
import AppContext from "./context/AppContext";
import NoRoute from "./components/NoRoute";
import FamilyProfile from "./components/FamilyProfile";
import SignUpForm from "./components/SignUpForm";

function App() {
    const {state} = React.useContext(AppContext);
    const {isLogged} = state;
    if(isLogged === true)
    return (
        <Router>
            <Switch>
                <Route exact path="/viewProfile/:email" component={ViewProfilePage}/>
                <Route exact path="/viewRelations/:id/:email" component={ViewRelationsFromPerson}/>
                <Route exact path="/addFamily" component={FamilyForm}/>
                <Route exact path="/addPerson" component={PersonForm}/>
                <Route exact path="/families" component={FamiliesTable}/>
                <Route exact path="/" component={HomePage}/>
                <Route exact path="/personForm" component={PersonForm}/>
                <Route exact path="/families/:id/members" component={FamilyMembersTable}/>
                <Route exact path="/members" component={FamilyMembersTable}/>
                <Route exact path="/families/:id/profile" component={FamilyProfile}/>
                <NoRoute component={LoginForm}/>
            </Switch>
        </Router>
    );
    else{
        return(
            <Router>
                <Route component={LoginForm}/>
                <Route exat path ="/auth/signup" component={SignUpForm}/>
            </Router>
        );
    }
}

export default App;
