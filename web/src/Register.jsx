import React, { useState } from "react";
import api from "./api";
import { useHistory } from "react-router-dom";
import './Forms.css';

const Register = () => {
    const history = useHistory();
    const [data, setData] = useState({ name: '', email: '', password: '', image: '' });
    const  [error, setError] = useState('');

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
            api.register(data)
            .then((response) => {
                localStorage.setItem("token", response.headers.authorization);
                history.push("/home")
            })
            .catch((error) =>  {
                setError('El email ya está en uso')})
        } else{
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
                <div className='row col-12'>
                    <div className='card-body row col-lg-4 offset-lg-4 col-md-6 offset-md-3 col-12'>
                        <img src="logoInstagram.png" style={{width: '10rem'}}/> 
                        <p className="text-grey text-center">Registrate para ver fotos y videos de tus amigos</p>
                        <label htmlFor="name">
                            <input className="form-control" type="text" placeholder="Name" name="name" value={data.name} onChange={handleInputChange} />
                        </label>
                        <label htmlFor="email">
                            <input className="form-control" type="text" placeholder="Email" name="email" value={data.email} onChange={handleInputChange} />
                        </label>
                        <label htmlFor="password">
                            <input className="form-control" type="password" placeholder="Password" name="password" value={data.password} onChange={handleInputChange} />
                        </label >
                        <label htmlFor="image">
                            <input className="form-control" type="text" placeholder="ImageLink" name="image" value={data.image} onChange={handleInputChange} />
                        </label>
                        <button className="btn-register btn-primary" type="button" value="Registrarse" type="submit">Registrarse</button>                     
                        <div className='row registrarse'>
                            <div className="s-part text-grey">¿Tienes una cuenta? <a href="/login">Inicia sesión</a></div>
                        </div>
                        <div className='error'>
                            {error}
                        </div>
                    </div>
                </div>
            </div>
        </form>
    );
}
    
export default Register;
