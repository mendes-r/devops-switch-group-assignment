import React, {useState} from 'react';
import PhoneNumbersTable from "./PhoneNumbersTable";

export default function PhoneNumberField(props) {

    const [phone, setPhone] = useState({
        phone: ""
    })

    const changePhone = (e) => {
        setPhone({
            phone: e.target.value
        })
    }

    const cleanField = () => {
        document.getElementById("phoneNumber").value = "";
        setPhone({
            phone: ""
        })
    }

    let response = "";

    const validatePhoneNumber = (phoneNumber) => {
        phoneNumber = phoneNumber.trim();
        if (phoneNumber.length === 0) response = ""
        else if (!phoneNumber.match(new RegExp("^[9][1-3&6][0-9]{7}$")) && !phoneNumber.match(new RegExp("^[2][1-9][0-9]{7}$"))) response = "* Invalid format"
        return response;
    }

    return (
        <div>
            <div>
                <span>
                     <input type="text"
                            className="input-fields__family-form"
                            id="phoneNumber"
                            placeholder="Insert here the phone number(s)"
                            onChange={(e) => {
                                changePhone(e);
                                validatePhoneNumber(e.target.value)
                     }}/>
                     <div className="phone-numbers-field">
                        <PhoneNumbersTable data={props.data}/>
                    </div>
                     <div className="field-error">
                        {validatePhoneNumber(phone.phone)}
                    </div>
                    <div className="buttons-container">
                         <button type="button"
                                 className="forms__button-add"
                                 onClick={() => {
                                     props.addPhone(phone.phone);
                                     cleanField()
                                 }}>
                            Add
                         </button>
                        <button type="reset"
                                className="forms__button-reset"
                                onClick={() => props.resetPhoneNumbers()}>
                            Reset
                        </button>
                    </div>
                </span>

            </div>
        </div>
    );
}

