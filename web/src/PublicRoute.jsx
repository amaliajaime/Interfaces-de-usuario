import React from 'react'
import { Route, Redirect } from "react-router-dom";

const PublicRoute = ({path, component}) => {

    const isAuthenticated = localStorage.getItem("token") && true;

    if (isAuthenticated) return <Redirect to={"/home"}/>

    return (
        <Route path={path} component={component}/>
    )
};

export default PublicRoute;