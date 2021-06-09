import axios from 'axios';

axios.defaults.baseURL = "http://localhost:7000";
axios.defaults.timeout = 10000; // 10s
axios.defaults.headers.post['Content-Type'] = 'application/json';

const request = (type, path, body) =>
    axios
        .request({ url: path, method: type, data: body})
        .then(response => response)
        .catch(error => {
            console.log('Error', error);
});

const request2 = (type, path, header) =>
    axios
        .request({ url: path, method: type, headers: header})
        .then(response => response)
        .catch(error => {
            console.log('Error', error.toJSON());
});

const request3 = (type, path, body, header) =>
    axios
        .request({ url: path, method: type, data: body, headers: header})
        .then(response => response)
        .catch(error => {
            console.log('Error', error.toJSON());
});

const api = {
    login:(data) => request(
        'post', `/login`, data
    ),
    register:(data) => request(
        'post', `/register`, data
    ),
    getUser:(header) => request2(
        'get', `/user`, header
    ),
    getPost:(id, header) => request2(
        'get', `/post/${id}`, header
    ),
    getFollow:(id, header) => request2(
        'get',  `/user/${id}/follow`, header
    ),
    follow:(id, header) => request2(
        'put',  `/user/${id}/follow`, header
    ),
    getProfile:(id, header)=> request2(
        'get',  `/user/${id}`, header
    ),
    comment:(data, id, header) => request3(
        'post', `/post/${id}/comment`, data, header
    ),
    search:(key, header) => request2(
        'get', `search?q=${key}`, header
    ),
    isFollow:(id, header) => request2(
        'get', `post/${id}/like`, header
    ),
    like:(id, header)=> request2(
        'put', `post/${id}/like`, header
    ),
    setToken:(token) => (
        axios.defaults.headers.common['authorization'] = token
    ),
}

export default api;