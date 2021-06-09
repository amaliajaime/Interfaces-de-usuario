import './App.css';
import { Link} from 'react-router-dom';

const PhotoAndName = (props) => {

  return (
    <div className="profile-userpic padding-card" >
      <img src={props.image} className="profile-image"/>
      <Link to={`/user/${props.id}`} className="card-title"> {props.name} </Link>
    </div>
  )
};

export default PhotoAndName;