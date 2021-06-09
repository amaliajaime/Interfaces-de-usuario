import React, { useState } from "react";
import api from "./api";
import { useHistory } from "react-router-dom";
import './Forms.css';

const Login = () => {
    const history = useHistory();
    const [data, setData] = useState({ email: '', password: '', });
    const [error, setError] = useState('');

    const handleInputChange = (event) => {
        event.preventDefault();
        setData({
            ...data,
            [event.target.name]: event.target.value,
        });
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        if(isEmail(data.email)) {
            api.login(data)
            .then((response) => {
                localStorage.setItem("token", response.headers.authorization);
                api.setToken(localStorage.getItem('token'));
                history.push("/home");
            })
            .catch((error) => {
                setError('No se encontro un usuario con ese email y/o contraseña')
            })
        } else {
            setError('El email es incorrecto')
        }
    }

    function isEmail(val) {
        let regEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return(regEmail.test(val))
    }

    return (
        <form onSubmit={handleSubmit}>
            <div className='container wrapper'>
                <div className='row'>
                    <div className='card-body login col-lg-4 offset-lg-12 col-md-6 offset-md-3 col-12'>
                        <img src="logoInstagram.png" style={{width: '10rem'}}/>
                        <label htmlFor="email">
                            <input className="form-control" type="text" placeholder="Email" name="email" value={data.email} onChange={handleInputChange} />
                        </label >
                        <label htmlFor="password">
                            <input className="form-control" type="password" placeholder="Password" name="password" value={data.password} onChange={handleInputChange} />
                        </label >
                        <button type="botton" value="Iniciar sesión" type="submit" className="btn btn-primary">Iniciar sesión</button> 
                        <div className="s-part text-grey">¿No tienes cuenta? <a href="/register">Registrate</a></div>
                        <div className="error">
                            {error}
                        </div>
                    </div>
                </div>
            </div>
        </form>
    );
};

export default Login;