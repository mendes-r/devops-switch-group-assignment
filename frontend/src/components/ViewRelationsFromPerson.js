import AppContext from "../context/AppContext";
import { getFamilyRelations, openRelationForm } from "../context/AppActions";
import React, { useContext } from "react";
import Menu from "./Menu";
import RelationsForm from "./RelationsForm";
import "./addRelationModal/addRelationModal.css";
import pig from "../images/pineapple-pig-half1.png";
import GoPrevious from "./GoPreviousPath";

function ViewRelationsFromPerson(props) {
  const { state, dispatch } = useContext(AppContext);
  const { relations, relationForm, userRelation } = state;
  const { data, refresh } = relations;
  const { openForm } = relationForm;

  console.log(state);

  const handleClick = () => {
    dispatch(openRelationForm());
  };

  const items = data.map((user, index) => {
    var arr = user.split(",");
    return (
      <tr className="tbody" key={index}>
        <td id="tdForRelation">
          <div className="families-name">
            {arr[0]} is {arr[1]} of {arr[2]}
          </div>
        </td>
      </tr>
    );
  });

  React.useEffect(() => {
    getFamilyRelations(dispatch, props.match.params.email);
  }, [refresh]);

  return (
    <div>
      <div>
        <Menu />
      </div>

      <GoPrevious />
      <div id="container" className="families-table__container">
        <div id="tablediv">
          <table id="families-table" className="families-table">
            <thead>
              <tr>
                <th id="userRelations"> {userRelation}'s Relations</th>
              </tr>
            </thead>
            <tbody> {items} </tbody>
          </table>
          <div id="divForButton">
            <button
              id="addRelation"
              className="new-family-button"
              onClick={handleClick}
            >
              +
            </button>
          </div>
        </div>
        <div id="divForForm" className="formFields">
          {openForm && <RelationsForm data={props.match.params} />}
        </div>
      </div>

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
  );
}

export default ViewRelationsFromPerson;
