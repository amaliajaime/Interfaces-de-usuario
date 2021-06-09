import { Link } from 'react-router-dom';
import './App.css';

const PostResult = ({id, portrait}) => {
    return (
        <div className="col-md-4">   
            <div className="card mb-4 shadow-sm">
                <Link to={`/post/${id}`}>
                    <img src={portrait} className="card-img-top"/>
                </Link>
            </div>
        </div>  
    )  
};

const ResultTag = ({posts}) => {

    return (
        <div className="container" style={{padding: '25px 10px'}}>
            <div className="row">
                {posts.map(post => <PostResult key={post.id} id={post.id} portrait={post.portrait}/>)}
            </div> 
        </div>  
    )
};

export default ResultTag;