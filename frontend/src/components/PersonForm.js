import React, { useContext, useState } from "react";
import AppContext from "../context/AppContext";
import { addPerson } from "../context/AppActions";
import VatField from "./field/VatField";
import EmailField from "./field/EmailField";
import DateField from "./field/DateField";
import HouseNumberOrStreetField from "./field/HouseNumberOrStreetField";
import ZipCodeField from "./field/ZipCodeField";
import PhoneNumberField from "./field/PhoneNumberField";
import PersonNameField from "./field/PersonNameField";
import StreetField from "./field/StreetField";
import CityField from "./field/CityField";
import CountryField from "./field/CountryField";
import "./addFamilyModal/AddFamilyModal.css";
import "./addMemberModal/addMemberModal.css";
import { closePersonForm } from "../context/AppActions";

export default function PersonForm(props) {
  const { state, dispatch } = useContext(AppContext);
  const { personForm } = state;
  const { result } = personForm;

  const handleClose = () => {
    dispatch(closePersonForm());
  };

  const [formPerson, setFormPerson] = useState({
    familyID: props.data,
    name: "",
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

  console.log(state);
  console.log(formPerson);

  const changeHandler = (e) => {
    setFormPerson({
      ...formPerson,
      [e.target.id]: e.target.value,
    });
  };

  const addPhone = (phone) => {
    if( phone !== "" && phone != null) {
      setFormPerson({
        ...formPerson,
        phoneNumbers: [...formPerson.phoneNumbers, phone],
      });
    }
  };

  const resetPhoneNumbers = () => {
    setFormPerson({
      ...formPerson,
      phoneNumbers: [],
    });
  };

  const submitHandler = (e) => {
    e.preventDefault();
    addPerson(formPerson, dispatch);
  };

  return (
    <div className="add-family-form">
      <form onSubmit={(e) => submitHandler(e)}>
        <div className="formFields">
          <PersonNameField
            id="name"
            required="required"
            data={formPerson.name}
            value={formPerson.name}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <EmailField
            id="email"
            required="required"
            data={formPerson.email}
            value={formPerson.email}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <VatField
            id="vat"
            required="required"
            data={formPerson.vat}
            value={formPerson.vat}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <DateField
            id="birthDate"
            required="required"
            data={formPerson.birthDate}
            value={formPerson.birthDate}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <HouseNumberOrStreetField
            id="houseNumber"
            required="required"
            data={formPerson.houseNumber}
            value={formPerson.houseNumber}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <StreetField
            id="street"
            required="required"
            data={formPerson.street}
            value={formPerson.street}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <ZipCodeField
            id="zipCode"
            required="required"
            data={formPerson.zipCode}
            value={formPerson.zipCode}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <CityField
            id="city"
            required="required"
            data={formPerson.city}
            value={formPerson.city}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <CountryField
            id="country"
            required="required"
            data={formPerson.country}
            value={formPerson.country}
            changeHandler={changeHandler}
          />
        </div>

        <div className="formFields">
          <PhoneNumberField
            id="phoneNumbers"
            data={formPerson.phoneNumbers}
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
