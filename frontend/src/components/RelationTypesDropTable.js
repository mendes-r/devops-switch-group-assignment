import React, {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import {getRelationTypes} from "../context/AppActions";

function RelationTypesDropTable(){

    const{ state, dispatch} = useContext(AppContext);
    let {systemRelationList} = state;
    let {data} = systemRelationList;
    const [relation, setRelation] = useState({
            relation: "",
    });

    console.log(relation)
    console.log(data)



    const handleClick = () => {
        getRelationTypes(dispatch)
        console.log("olaa" );
    };

    const handleChange = (event) => {
        setRelation({
            relation: event.target.value,
        })
    }

    const possibleRelations = data.map((user) => {
        const info = user.split(',');
        return (
                <option value='${info}'> {info} </option>

            )
        }

    );

    return (
        <div>
            <div>
                <form>
                    <select id="relation" name="relation">
                        <option value = "" onClick={handleClick} >Select a relation</option>
                        {possibleRelations}
                    </select>
                </form>
            </div>
        </div>
    )
}

export default RelationTypesDropTable;

