import React from "react";
import './Comment.css';
import PhotoAndName from './PhotoAndName';

const Comment = (props) => {
    return (
      <div className="card margin-top-md padding-card" style={{width: '25,5rem'}}>
        <PhotoAndName {...props.user}/>
        <p>{props.body}</p>
      </div>
    );
}

export default Comment;