import './App.css';
import PhotoAndName from './PhotoAndName';


const ViewUsersResult = (props) => {
    return (
        <div className="col-md-4">
            <div className="mb-4">
                <PhotoAndName id={props.id} name={props.name} image={props.image}/>
            </div> 
        </div>
    )
}


const ResultUsers = ({users}) => {
    return (
        <div className="container" style={{padding: '25px 10px'}}>
            <div className="row">
                {users.map(user => <ViewUsersResult key={user.id} id={user.id} name={user.name} image={user.image}/>)}
            </div> 
        </div> 
    )
};


export default ResultUsers;