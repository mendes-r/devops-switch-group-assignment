import React, {useContext, useState} from 'react';
import AppContext from '../context/AppContext';
import './addRelationModal/addRelationModal.css';
import {addRelation} from "../context/AppActions";
import { closeRelationForm } from "../context/AppActions";
import RelationTypesDropTable from "./RelationTypesDropTable";

function RelationsForm(props) {
    const {state, dispatch} = useContext(AppContext);
    const {relationForm} = state;
    const {result} = relationForm;

    const [form, setForm] = useState({
        familyID: props.data.id,
        personEmail: props.data.email,
        kinEmail: "",
        relationType: ""
    })

    const handleClose = () => {
        dispatch(closeRelationForm());
      };

    const changeHandler = (e) => {
        setForm({
            ...form,
            [e.target.id]: e.target.value
        })
    }

    const submitHandler = e => {
        e.preventDefault()
        addRelation(form, dispatch, form.familyID);
        console.log(result)
        console.log(props)
        console.log(state)
    }

    return (
        <div className="relation-form">
            <form onSubmit={(e) => submitHandler(e)}>

                <div className="formFields">
                    <input
                        type="text"
                        className="input-fields__relation-form"
                        placeholder="Insert here the relative person email"
                        id="kinEmail"
                        required="required"
                        onChange={(e) => changeHandler(e)}/>
                </div>

         {/*       <div className="formFields">
                    <input
                        type="text"
                        className="formFields__relation-type"
                        placeholder="Insert here relation type"
                        id="relationType"
                        required="required"
                        onChange={(e) => changeHandler(e)}/>
                </div>*/}

                <div className="formFields">
                    <RelationTypesDropTable/>
                </div>

                <div className="formFields">
                    <button  className="new-family" type="submit">Submit</button>
                </div>
                <div className="formFields">
                    <button  className="new-family" type="closePersonForm" onClick={handleClose}>Close</button>
                </div>


            </form>
            <div className="field-error error-relations">{state.relationForm.result}</div>
        </div>
    );
}

export default RelationsForm;