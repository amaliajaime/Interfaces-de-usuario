import './App.css';
import { Link } from 'react-router-dom';

const PostUser = ({id, portrait}) => {
    return (
        <div id={id} className="col-md-4">   
            <div className="card mb-4 shadow-sm">
                <Link to={`/post/${id}`}>
                    <img src={portrait} className="card-img-top"/>
                </Link>
            </div>
        </div>  
    )  
};


const UserPosts = (props) => {   
    
    return (
        <div className="container" style={{padding: '25px 10px'}}>
            <div className="row"> 
                {props.posts.map(post => <PostUser key={post.id} id={post.id} portrait={post.portrait}/>)}
            </div> 
        </div>   
  )
};

export default UserPosts;