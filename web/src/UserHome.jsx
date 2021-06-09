import './App.css';
import { useEffect, useState } from 'react';
import api from "./api";
import UserTimeline from './UserTimeline';
import InstagramHeader from './InstagramHeader';
import PhotoAndName from './PhotoAndName';

const UserHome = () => {

    const [userData, setUserData] = useState({
        id: "",
        name: "",
        image: "",
        followers: [],
        timeline: [] 
    });

    useEffect(() => {
        const token = localStorage.getItem("token");

        const headers = {
            authorization: token
        };

        api.getUser(headers)
        .then(
            response => setUserData(response.data),

            localStorage.setItem("idUserLogin", userData.id),
            localStorage.setItem("nameUserLogin", userData.name),
            localStorage.setItem("imageUserLogin", userData.image),

        )
        .catch(error => console.log("Error: ", error));
    }, [userData.id]);

    return (
        <>
            <InstagramHeader image={userData.image}/>
            <div className="row">
                <div className="column"></div>
                <UserTimeline timelineUser={userData.timeline}/>
                <div className="column">
                    <PhotoAndName key={userData.id} id={userData.id} name={userData.name} image={userData.image}/>
                    <a>Followers</a>
                    {userData.followers.map(follower => <PhotoAndName key={follower.id} id={follower.id} name={follower.name} image={follower.image}/>)}
                </div>
            </div>
        </>
  );
}

export default UserHome;