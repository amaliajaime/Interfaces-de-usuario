import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "./api";
import InstagramHeader from "./InstagramHeader";
import ResultTag from "./ResultTag";
import ResultUsers from "./ResultUsers";

const ResultSearch = () => {

    const [component, setComponent] = useState(null);
    
    const [key, setKey] = useState("");

    const keyParam = useParams();
	const remplaceTag = (value) => {
        if (value.startsWith('%23')){
            return "#".concat(value.slice(3))
        } else {
            return value
        }
    }

    useEffect(() => {
        setKey(keyParam.key);
  
        const token = localStorage.getItem("token");
        const headers = {
            authorization: token,
        };
        api.search(key, headers)
        .then(response => {
            if (key.startsWith('%23')) {
                setComponent(<ResultTag posts={response.data.content}/>);       
            } else {
                setComponent(<ResultUsers users={response.data.content}/>);
            }
        })
        .catch(error => console.log('Error: ', error))
    }, [key]);

    return (
        <>
            <InstagramHeader/>
		    <div className="container">
                <h6>Resultados para: {remplaceTag(key)}</h6>
            </div>
            {component}
        </>
    );
}

export default ResultSearch;