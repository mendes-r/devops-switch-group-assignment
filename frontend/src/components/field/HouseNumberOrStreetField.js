import React from 'react';

export default function HouseNumberOrStreetStreetField(props) {

    let response = "";

    const validateComponent = (component) => {
        if (component !== undefined) {
            component = component.trim();
            const format = new RegExp("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _ºª]*$");
            if (component.length === 0) response = " "
            else if (!component.match(format)) response = "* Contains invalid characters"
            return response;
        }
    }

    return (
        <div>
            <div>
                <input type="text" id={props.id}
                       className="input-fields__family-form"
                       placeholder="Insert here house number"
                       required={props.required}
                       value={props.value}
                       onChange={(e) => props.changeHandler(e)}/>
            </div>
            <div className="field-error">
                {validateComponent(props.data)}
            </div>
        </div>
    );
}
