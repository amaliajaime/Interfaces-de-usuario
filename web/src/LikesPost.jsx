import { useEffect, useState } from 'react';
import api from './api';
import './App.css';
import Likes from './Likes';

const LikesPost = (props) => {
    const [likesPost, setLikesPost] = useState('');
    const likes = props.likes.length;

    useEffect((props) => {
        setLikesPost(likes)
    }, [props.id]);

    const countLike = () => {
        setLikesPost( likesPost + 1)   
    };

    const discountLike = () => {
        setLikesPost(likesPost - 1)
    };

    return (
        <div className="navbar">
            <LikeButton id={props.id} countLike={countLike} discountLike={discountLike}/>  
            <Likes likes={likesPost} /> 
        </div>
    )
};

const LikeButton = (post) => {
    
    const [state, setState] = useState({isToggleOn: false});

    useEffect(() => {
        const token = localStorage.getItem("token");
        const headers = {
            authorization: token,
        };
        api.isFollow(post.id, headers)
         .then(response => { 
             setState({isToggleOn: response.data.result})
         })
         .catch(error => console.log("Error: ", error))
    }, [post.id]);

    const handleClick = () => {
        setState({isToggleOn: !state.isToggleOn});
        const token = localStorage.getItem("token");
        const headers = {
            authorization: token,

        };
        api.like(post.id, headers)
         .then(response => {
            if (state.isToggleOn) {
                post.discountLike()
            } 
            else {
                post.countLike()
            };
            console.log(response)}
         )
         .catch(error => console.log("Error: ", error))
    };


    return (
        <div>
            <button onClick={handleClick} className="image-button margin-top-md">
                {state.isToggleOn ? <img src="/likeButton.png" className="image-button"/> : <img src='/noLike.png' className="image-button"/>}
            </button>
        </div>
    )

}

export default LikesPost;