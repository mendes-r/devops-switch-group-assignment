import React, { useContext, useState } from "react";
import AppContext from "../context/AppContext";
import { addFamily } from "../context/AppActions";
import "./addFamilyModal/AddFamilyModal.css";
import FamilyNameField from "./field/FamilyNameField";
import PersonNameField from "./field/PersonNameField";
import EmailField from "./field/EmailField";
import VatField from "./field/VatField";
import DateField from "./field/DateField";
import StreetField from "./field/StreetField";
import ZipCodeField from "./field/ZipCodeField";
import CityField from "./field/CityField";
import CountryField from "./field/CountryField";
import HouseNumberOrStreetField from "./field/HouseNumberOrStreetField";
import PhoneNumbersField from "./field/PhoneNumberField";
import { closeFamilyForm } from "../context/AppActions";

function FamilyForm() {
  const { state, dispatch } = useContext(AppContext);
  const { familyForm } = state;
  const { result } = familyForm;

  const handleClose = () => {
    dispatch(closeFamilyForm());
  };
 console.log(state)
  const [form, setForm] = useState({
    familyName: "",
    personName: "",
    birthDate: "",
    vat: "",
    houseNumber: "",
    street: "",
    city: "",
    country: "",
    email: "",
    zipCode: "",
    phoneNumbers: [],
  });
  console.log(form)
  const changeHandler = (e) => {
    setForm({
      ...form,
      [e.target.id]: e.target.value,
    });
  };

  const addPhone = (phone) => {
    if( phone !== "" && phone != null){
      setForm({
        ...form,
        phoneNumbers: [...form.phoneNumbers, phone],
      });
    }
  };

  const resetPhoneNumbers = () => {
    setForm({
      ...form,
      phoneNumbers: [],
    });
  };

  const submitHandler = (e) => {
    e.preventDefault();
    addFamily(form, dispatch, state);
  };

  return (
    <div className="add-family-form">
      <form onSubmit={(e) => submitHandler(e)}>
        <div className="formFields">
          <FamilyNameField
            id="familyName"
            required="required"
            data={form.familyName}
            value={form.familyName}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <PersonNameField
            id="personName"
            required="required"
            data={form.personName}
            value={form.personName}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <EmailField
            id="email"
            required="required"
            data={form.email}
            value={form.email}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <VatField
            id="vat"
            required="required"
            data={form.vat}
            value={form.vat}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <DateField
            id="birthDate"
            required="required"
            data={form.birthDate}
            value={form.birthDate}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <HouseNumberOrStreetField
            id="houseNumber"
            required="required"
            data={form.houseNumber}
            value={form.houseNumber}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <StreetField
            id="street"
            required="required"
            data={form.street}
            value={form.street}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <ZipCodeField
            id="zipCode"
            required="required"
            data={form.zipCode}
            value={form.zipCode}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <CityField
            id="city"
            required="required"
            data={form.city}
            value={form.city}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <CountryField
            id="country"
            required="required"
            data={form.country}
            value={form.country}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <PhoneNumbersField
            id="phoneNumbers"
            data={form.phoneNumbers}
            addPhone={addPhone}
            resetPhoneNumbers={resetPhoneNumbers}
          />
        </div>
        <div className="formFields">
            <button className="new-family" type="submit">
              Submit
            </button>
            <button className="new-family" onClick={handleClose}>
              CLOSE
            </button>
        </div> 
      </form>
      <div className="field-error error-date">{result}</div>
    </div>
  );
}

export default FamilyForm;
