import AppContext from "../context/AppContext";
import {getListOfFamilyMembers, openPersonForm, saveUserRelations} from "../context/AppActions";
import { useContext } from "react";
import React from "react";
import { Link } from "react-router-dom";
import Menu from "./Menu";
import PersonForm from "./PersonForm";
import "../css/FamilyTable.css";
import pig from "../images/pineapple-pig-half1.png"

function FamilyMembersTable(props) {
  const { state, dispatch } = useContext(AppContext);
  const { members, personForm, familyName } = state;

  const { data, refresh } = members;
  const { openForm } = personForm;

  const handleClick = () => {
    dispatch(openPersonForm());
  };

  const handleUserRelation = (username) => {
    console.log("ollllaaa")
    console.log(username)
    saveUserRelations(username,dispatch);
  }

  console.log(state);

  const items = data.map((user, index) => {
    const urlInfoProfile = "/viewProfile/" + user.mainEmail;
    const urlInfoRelations =
      "/viewRelations/" + props.match.params.id + "/" + user.mainEmail;

    return (
      <li className="tbody" key={index}>

        <div className="families-name"> {user.name}</div>
        <div>
          <Link to={urlInfoProfile}>
            <button id={user.mainEmail} className="families-info__button" onClick={() => handleUserRelation(user.name)}>
              {" "}
              Profile
            </button>
          </Link>
          <Link to={urlInfoRelations}>
            <button id={user.mainEmail} className="families-info__button" onClick={() => handleUserRelation(user.name)}>
              {" "}
              Relations
            </button>
          </Link>
        </div>
      </li>
    );
  });

  React.useEffect(() => {
    getListOfFamilyMembers(dispatch, props.match.params.id);
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
              <th>{familyName}'s Members</th>
            </tr>
          </thead>
          <tbody> {items} </tbody>
          <button className="new-family-button" onClick={handleClick}>
          +
        </button>
        
        </table>
        {openForm && <PersonForm data={props.match.params.id} />}
      </div>
      <img src={pig} style={{position:"fixed", left:"0", bottom:"-130px", width:"250px", zIndex:"-1", opacity:"0.5" }}></img>
    </div>
  );
}

export default FamilyMembersTable;
