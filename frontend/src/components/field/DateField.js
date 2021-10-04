import React from 'react';

export default function DateField(props) {

    let response = "";

    const validateDate = (date) => {
        if (date !== undefined) {
            if (date.length === 0) response = ""
            else if (new Date(date).valueOf() > new Date().valueOf())
                return "* Date after today"
            return response;
        }
    }

    return (
        <div>
            <div>
                <input
                    type="date"
                    className="input-fields__family-form input-field-date"
                    placeholder="Insert birth date: "
                    id={props.id}
                    value={props.value}
                    required={props.required}
                    onChange={(e) => props.changeHandler(e)}/>
            </div>
            <div className="field-error">
                {validateDate(props.data)}
            </div>
        </div>

    );
}