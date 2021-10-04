import AppContext from "../context/AppContext";
import {
  getListOfFamilies,
  openFamilyForm,
  saveFamilyName,
} from "../context/AppActions";
import { useContext } from "react";
import React from "react";
import "../css/FamilyTable.css";
import { Link } from "react-router-dom";
import Menu from "./Menu";
import FamilyForm from "./FamilyForm";
import FamilyProfile from "./FamilyProfile";
import pig from "../images/pineapple-pig-half1.png";

function FamiliesTable() {
  let { state, dispatch } = useContext(AppContext);
  let { families, familyForm } = state;
  const { data, refresh } = families;
  const { openForm } = familyForm;

  const handleClick = () => {
    dispatch(openFamilyForm());
  };

  const [showFamilyProfile, setShowFamilyProfile] = React.useState();

  function handleOnClick(index) {
    if (index === showFamilyProfile) setShowFamilyProfile(null);
    else setShowFamilyProfile(index);
  }

  const handleFamilyName = (familyname) => {
    saveFamilyName(familyname, dispatch);
  };

  const items = data.map((user, index) => {
    const urlInfo = "/families/" + user.familyID + "/members";

    return (
      <li className="tbody">
        <div>
          <div
            id="button"
            className="families-name"
            onClick={() => handleOnClick(index)}
          >
            {" "}
            {user.name}
          </div>
          <div className="profile-info">
            {showFamilyProfile === index && (
              <FamilyProfile id={user.familyID} />
            )}
          </div>
        </div>
        <div className="info-buttons">
          <Link to={urlInfo}>
            <button
              id={user.familyID}
              className="families-info__button"
              onClick={() => handleFamilyName(user.name)}
            >
              {" "}
              Members{" "}
            </button>
          </Link>
        </div>
      </li>
    );
  });

  React.useEffect(() => {
    getListOfFamilies(dispatch);
  }, [refresh]);

  return (
    <div>
      <div>
        <Menu />
      </div>
      <div className="families-table__container">
        <table className="families-table">
          <thead>
            <tr>
              <th>Families</th>
            </tr>
          </thead>
          {/* <tbody> {rows} </tbody>*/}
          <tbody> {items} </tbody>
          <button className="new-family-button" onClick={handleClick}>
            +
          </button>
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
        </table>
        {openForm && <FamilyForm data={state.id} />}
      </div>
    </div>

  );
}

export default FamiliesTable;
