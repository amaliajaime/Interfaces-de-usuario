import api from './api';
import { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import './App.css';

const Searcher = () => {

    const history = useHistory();

    const [key, setKey] = useState("");

    const remplaceTag = (value) => {
        if (key.startsWith('#')){
            return "%23".concat(value.slice(1))
        } else {
            return key
        }
    }

    const handleInputChange = (event) => {
        setKey(event.target.value)
    }

    const handleClick = () => {
        history.push(`/search/${remplaceTag(key)}`);
        window.location.reload();
    }

    const handleKeyDown = (event) => {
      if (event.key === 'Enter') {
        handleClick()
      }
    };

    return (
        <div style={{width: '33%'}}>
            <input type="text" 
                   placeholder="Buscar"
                   value={key}
                   onChange={handleInputChange}
                   onKeyDown={handleKeyDown}
                   className="input-search"/>
        </div>        
    )
    
};


const InstagramHeader = () => {   

    const history = useHistory();

    const [userImage, setUserImage] = useState({
        id: "",
        image: ""
    }); 

    useEffect(() => {
        const token = localStorage.getItem("token");
        const headers = {
            authorization: token,
        };
        api.getUser(headers) 
        .then(
            response => setUserImage({
                id: response.data.id,
                image: response.data.image
                
            })
        )
        .catch(error => console.log("Error: ", error));
    }, [userImage.id]);

    const handleClick = () => {
        history.push(`/user/${userImage.id}`);
        window.location.reload();
    };

    return (
        <div className="navbar">
            <button onClick={() => {history.push('/home')}} className="instagram-button">
                <img src="/logoInstagram.png" style={{width: '10rem'}}/>            
            </button>
            <Searcher/>
            <div className="photo-user-login" >
                <button onClick={handleClick} className="image-button">
                    <img src={userImage.image} className="profile-image"/>
                </button>
            </div>                
        </div> 
  )
};

export default InstagramHeader;