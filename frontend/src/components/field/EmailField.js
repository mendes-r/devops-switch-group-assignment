import React from 'react';

export default function EmailField(props) {

    let response = "";

    const validateEmail = (email) => {
        if (email !== undefined) {
            email = email.trim();
            const format = new RegExp("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]+$");
            if (email.length === 0) response = ""
            else if (!email.match(format)) response = "* Email format invalid"
            else {
                response = ""
            }
            return response;
        }
    }

    return (
        <div>
            <div>
                <input type="text" id={props.id}
                       className="input-fields__family-form"
                       placeholder="Insert here the email"
                       value={props.value}
                       required={props.required} onChange={(e) => props.changeHandler(e)}/>
            </div>
            <div className="field-error">
                {validateEmail(props.data)}
            </div>
        </div>
    );
}