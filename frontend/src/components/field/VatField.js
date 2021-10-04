import React from 'react';

export default function VatField(props) {

    let response = "";

    const validateControlDigit =(vat) => {
        const maxDigits = 9;
        let checkSum = 0;
        for (let index = 0; index < maxDigits - 1; index++) {
            checkSum += Number(vat.charAt(index)) * (maxDigits - index);
        }
        let checkDigit = 11 - (checkSum % 11);
        if (checkDigit >= 10) checkDigit = 0;
        return checkDigit === Number(vat.charAt(maxDigits - 1) - '0');
    }

    const validateVat = (vat) => {
        if (vat !== undefined) {
            vat = vat.trim();
            const format = new RegExp("([1-3])[0-9]{8}");
            if (vat.length === 0) response = ""
            else if (vat.match(new RegExp("[^0-9]"))) response = "* VAT contains non-numeric characters"
            else if (vat.length !== 9) response = "* VAT does not contain 9 digits"
            else if (!vat.match(format)) return response = "* VAT not corresponds to a Person's VAT"
            else if (!validateControlDigit(vat)) return response = "* VAT control digit is not valid"
            return response;
        }
    }

    return (
        <div>
            <div>
                <input type="text" id={props.id}
                       className="input-fields__family-form"
                       required={props.required}
                       value={props.value}
                       placeholder="Insert here the VAT (Value-Added Tax)"
                       onChange={(e) => props.changeHandler(e)}/>
            </div>
            <div className="field-error">
                {validateVat(props.data)}
            </div>
        </div>
    );
}
