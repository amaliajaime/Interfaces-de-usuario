import React from 'react';
import { useState, useEffect} from 'react';
import './App.css';
import { useHistory } from "react-router-dom";
import api from './api';

const Logout = () => {
    
    const history = useHistory();
    
    const logout = () => {
        localStorage.removeItem("token");
	localStorage.removeItem("idUserLogin");
        history.push("/");
    };

    return (
        <button onClick={logout} className="btn btn-primary">Cerrar Sesión</button>
    )
};

const Follow = ({userId}) => {
    const [state, setState] = useState({isToggleOn: true});

    useEffect(() => {
        const token = localStorage.getItem("token");
        const headers = {
            authorization: token,
        };
         api.getFollow(userId, headers)
         .then(response => { 
             setState({isToggleOn: response.data.result});
         })
         .catch(error => console.log("Error: ", error))
    }, [userId]);
    
    const handleClick = () => {
        setState({isToggleOn: !state.isToggleOn});
        const token = localStorage.getItem("token");
        const headers = {
            authorization: token,
        };
        api.follow(userId, headers)
         .then(response => console.log(response)
         )
         .catch(error => console.log("Error: ", error))
    };

    return (
        <button onClick={handleClick} className="btn btn-primary">
            {state.isToggleOn ? 'Dejar de Seguir': 'Seguir'}
        </button>
    )
};

const ProfileButton = ({userId}) => {
    const userLoginId = localStorage.getItem("idUserLogin");

    return (
        <div>
            {(userId === userLoginId) ? <Logout/> : <Follow userId={userId}/>}
        </div>
    )
};

export default ProfileButton;