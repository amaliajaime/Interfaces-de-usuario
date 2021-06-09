import React, { useState } from 'react';
import Comment from "./Comment";

const AddCommentPost = (props) => {

    const [preComment, setPreComment] = useState('');
    
    const changeComment = (event) => {
        setPreComment(event.target.value)
    }

    const addComment = (event) => {
        const user={
            id:localStorage.getItem("idUserLogin"),
            name:localStorage.getItem("nameUserLogin"),
            image:localStorage.getItem("imageUserLogin")
        }
        props.addCommentPost(preComment, user)
        setPreComment('');   
    };

    return (
        <div>
            <div>
                {props.comments.map(comment => <Comment key={comment.id} body={comment.body} user={comment.user} />)}
            </div>
            <div className="col-11">
                <textarea value={preComment} onChange={changeComment} className="form-control mb-2" placeholder={"Agrega un comentario"}/>
                <button disabled={!preComment} type="button" className="btn btn-primary" onClick={addComment}>Comentar</button>
            </div>
        </div>
    ); 
}

export default AddCommentPost;
