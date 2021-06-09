import React, { useState, useEffect } from "react";
import { Link, useParams} from 'react-router-dom';
import api from "./api";
import PhotoAndName from './PhotoAndName';
import AddCommentPost from "./AddCommentPost";
import InstagramHeader from "./InstagramHeader";
import LikesPost from "./LikesPost";

const Post = () => {

  const idPath = useParams();

  const [postData, setPostData] = useState({
    idPost: "",
    description: "",
    portrait: "",
    landscape: "",
    likes: [], 
    date: "",
    userPost: "",
    comments: []
  });

  const [idProvisional, setIdProvisional] = useState(0);

  useEffect(() => {
    const token = localStorage.getItem("token");
    const headers = {
        authorization: token,
    };
    api.getPost(idPath.id, headers) 
      .then(response =>
        setPostData(response.data))
      .catch(error => console.log("Error: ", error))
  }, [postData.id]);

 const addCommentPost = (body, user) => {
    const token = localStorage.getItem("token");
    const data = {
      body: body
    }
    const headers = {
      authorization: token,
    };
    
    api.comment(data, idPath.id, headers)
      .then((response) => 
      setPostData({
        ...postData,
        comments: postData.comments.concat({idProvisional, body, user})
      },
      setIdProvisional(idProvisional +1)))
      .catch((error) => console.log("Error:", error));
    };

  return (
    <>
      <InstagramHeader/>
      <div className="card margin-top-md padding-card" style={{width: '25rem'}}>
        <PhotoAndName {...postData.user}/>   
        <Link to={`/post/${idPath}`}>
          <img src={postData.portrait} className="card-img-top"/>
        </Link>
        <LikesPost id={postData.id} likes={postData.likes}/>
        <div className="card-body">
          <p className="card-text"> {postData.description} </p>
        </div>
        <div className="col-11">
          <AddCommentPost 
            comments= {postData.comments} 
            user= {postData.userPost}
            addCommentPost= {addCommentPost}/>
        </div>
      </div>
    </>
  );
};

export default Post;