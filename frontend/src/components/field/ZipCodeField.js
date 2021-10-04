import React from 'react';

export default function ZipCodeField(props) {

    let response = "";

    const validateZipCode = (zipCode) => {
        if (zipCode !== undefined) {
            zipCode = zipCode.trim();
            const format = new RegExp('^\\d{4}-\\d{3}$');
            if (zipCode.length === 0) response = " "
            else if (!zipCode.match(new RegExp('\\d'))) response = "* Contains invalid characters"
            else if (!zipCode.match(format)) response = "* Invalid format"
            return response;
        }
    }

    return (
        <div>
            <div>
                <input type="text" id={props.id}
                       className="input-fields__family-form"
                       placeholder="Insert here zip code"
                       required={props.required}
                       value={props.value}
                       onChange={(e) => props.changeHandler(e)}/>
            </div>
            <div className="field-error">
                {validateZipCode(props.data)}
            </div>
        </div>
    );
}
