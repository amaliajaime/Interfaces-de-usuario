import './App.css';
import { useEffect, useState } from 'react';
import api from './api';
import InstagramHeader from './InstagramHeader';
import UserPosts from './UserPosts';
import ProfileButton from './ProfileButton';
import { useParams } from 'react-router-dom';
import PhotoAndName from './PhotoAndName';

const UserProfile = () => {  
    const idParam = useParams();

    const [userData, setUserData] = useState({
        id: "",
        name: "",
        image: "",
        followers: [],
        posts: [] 
    });

    useEffect(() => {
        const token = localStorage.getItem("token");
        const headers = {
            authorization: token,
        };
        api.getProfile(idParam.id, headers) 
        .then(
            response => setUserData(response.data)
        )
        .catch(error => console.log("Error: ", error))
    }, [idParam]);
    
    return (
        <>
            <InstagramHeader/>
            <div className="logout">
                <PhotoAndName id={userData.id} name={userData.name} image={userData.image}/>
                <ProfileButton userId={idParam.id}/> 
            </div>
            <UserPosts posts={userData.posts}/>   
        </>
  )
};
export default UserProfile;