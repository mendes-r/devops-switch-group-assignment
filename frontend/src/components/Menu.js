import "../css/Header.css";
import { Link } from "react-router-dom";
import account from "../images/menu-account.png";
import profile from "../images/menu-profile.png";
import family from "../images/menu-family.png";
import home from "../images/menu-home.png";
import LogoutForm from "./LogoutForm";
import AppContext from "../context/AppContext";
import React from "react";

function Menu() {
    const { state } = React.useContext(AppContext);

    return (
        <div className="topnav">
            <Link className="menu-link" to="/">
                <div className="box" style={{ backgroundColor: "#2b5458" }}>
                    <img className="icon" src={home} alt="home2" />
                    <div className="text">Home</div>
                </div>
            </Link>

            <Link className="menu-link" to="/families">
                <div className="box" style={{ backgroundColor: "#5e8b7e" }}>
                    <img className="icon" src={family} alt="family" />
                    <div className="text">Families</div>
                </div>
            </Link>

            <div className="box" style={{ backgroundColor: "#a7c4bc" }}>
                <img className="icon" src={account} alt="accounts" />
                <div className="text">Accounts</div>
            </div>

            <div className="box" style={{ backgroundColor: "#B5D3CB" }}>
                <img className="icon" src={profile} alt="profile" />
                <div className="text">Welcome, {state.user.data.username}</div>
            </div>
            <div className="logout-box" style={{ backgroundColor: "#2b5458" }}>
                <LogoutForm />
            </div>
        </div>
    );
}

export default Menu;
