import AppContext from "../context/AppContext";
import { getUserProfile } from "../context/AppActions";
import { useContext } from "react";
import React from "react";
import Menu from "./Menu";
import "../css/ViewProfilePage.css";
import GoPrevious from "./GoPreviousPath"
import pig from "../images/pineapple-pig-half1.png";

function ViewProfilePage(props) {
  const { state, dispatch, headers } = useContext(AppContext);
  const { profile, userRelation } = state;

  const getProfile = () => {
    getUserProfile(dispatch);
  };

  const {
    name,
    vat,
    birthDate,
    mainEmail,
    otherEmails,
    telephoneNumbers = [],
    houseNumber,
    street,
    city,
    country,
    zipCode,
  } = profile; //criar uma constante com todos os parametros de saida do backend, neste caso para o user profile

  React.useEffect(() => {
    getUserProfile(dispatch, props.match.params.email);
  }, []);
  return (
    <div>
      <div>
        <Menu />
      </div>
      <GoPrevious/>
      <div>
        <div>{JSON.stringify(getProfile)}</div>

                    <ul className="container">
                    <h2 className="h2"> {userRelation}'s Profile </h2>
                        <li className="table-row">
                            <div className="col-1">{headers.name}</div>
                            <div className="col-2-profile">{name}</div>
                        </li>
                        <li className="table-row">
                            <div className="col-1">{headers.vat}</div>
                            <div className="col-2-profile">{vat}</div>
                        </li>
                        <li className="table-row">
                            <div className="col-1">{headers.birthDate}</div>
                            <div className="col-2-profile">{birthDate}</div>
                        </li>
                        <li className="table-row">
                            <div className="col-1">{headers.mainEmail}</div>
                            <div className="col-2-profile">{mainEmail}</div>
                        </li>
                        <li className="table-row">
                            <div className="col-1">{headers.otherEmails}</div>
                            <div className="col-2-profile">{otherEmails}</div>
                        </li>
                        <li className="table-row">
                            <div className="col-1">{headers.telephoneNumbers}</div>
                            <div className="col-2-telephone">
                            { telephoneNumbers.map((number) => (<div className="col-2-profile">{number}</div>)) }
                            </div>
                        </li>
                        <li className="table-row">
                            <div className="col-1">{headers.houseNumber}</div>
                            <div className="col-2-profile">{houseNumber}</div>
                        </li>
                        <li className="table-row">
                            <div className="col-1">{headers.street}</div>
                            <div className="col-2-profile">{street}</div>
                        </li>
                        <li className="table-row">
                            <div className="col-1">{headers.city}</div>
                            <div className="col-2-profile">{city}</div>
                        </li>
                        <li className="table-row">
                            <div className="col-1">{headers.country}</div>
                            <div className="col-2-profile">{country}</div>
                        </li>
                        <li className="table-row">
                            <div className="col-1">{headers.zipCode}</div>
                            <div className="col-2-profile">{zipCode}</div>
                        </li>

          {/*<tr className="App-span">{headers.vat}: {vat}</tr>
                        <tr className="App-span">{headers.birthDate}: {birthDate}</tr>
                        <tr className="App-span">{headers.mainEmail}: {mainEmail}</tr>
                        <tr className="App-span">{headers.otherEmails}: {otherEmails}</tr>
                        <tr className="App-span">{headers.telephoneNumbers}: </tr>
                         <span className="App-span">{headers.telephoneNumbers}: {telephoneNumbers.join(', ')}</span>
                        { telephoneNumbers.map((number) => (<span className="App-span">{number}</span>)) }
                        <tr className="App-span">{headers.houseNumber}: {houseNumber}</tr>
                        <tr className="App-span">{headers.street}: {street}</tr>
                        <tr className="App-span">{headers.city}: {city}</tr>
                        <tr className="App-span">{headers.country}: {country}</tr>
                        <tr className="App-span">{headers.zipCode}: {zipCode}</tr>*/}
        </ul>
        <img
          src={pig}
          style={{
            position: "fixed",
            left: "0",
            bottom: "-130px",
            width: "250px",
            zIndex: "-1",
            opacity: "0.5",
          }}
        ></img>
      </div>
    </div>
  );
}

export default ViewProfilePage;