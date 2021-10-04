import React, {useState} from 'react';
import '../addFamilyModal/AddFamilyModal.css'

export default function FamilyNameField(props) {

    let response = "";

    const validateName = (text) => {
        if (text !== undefined) {
            text = text.trim();
            const format = new RegExp("^[a-z\u00C0-\u00ff A-Z]+$");
            if (text.length === 0) response = " "
            else if (text.match(new RegExp("[0-9]"))) response = "* Contains numeric characters"
            else if (!text.match(format)) response = "* Contains invalid characters"
            return response;
        }
    }

    return (
        <div>
            <div>
                <input type="text" id={props.id}
                       className="input-fields__family-form"
                       required={props.required}
                       placeholder="Insert here the family name"
                       value={props.value}
                       onChange={(e) => props.changeHandler(e)}/>
            </div>
            <div className="field-error">
                {validateName(props.data)}
            </div>

        </div>
    );
}