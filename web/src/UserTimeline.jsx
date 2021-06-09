import { Link } from 'react-router-dom';
import './App.css';
import LikesPost from './LikesPost';
import PhotoAndName from './PhotoAndName';

const PostTimeline = ({post}) => {

  return (
    <>
      <div className="card margin-top-md padding-card" style={{width: '25rem'}}>  
          <PhotoAndName id={post.user.id} name={post.user.name} image={post.user.image}/>   
          <Link to={`/post/${post.id}`}>
            <img src={post.portrait} className="card-img-top"/>
          </Link>
          <LikesPost id={post.id} likes={post.likes}/>
          <div className="card-body">
              <p className="card-text"> {post.description} </p>
          </div>
      </div>
    </>
  )
};

const UserTimeline = (props) => {

    return (
        <div className="column">
            {props.timelineUser.map(post => <PostTimeline key={post.user.id} post={post}/>)}
        </div>
    );
}

export default UserTimeline;
