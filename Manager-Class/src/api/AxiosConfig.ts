import axios from "axios";

const axiosInstace = axios.create({ 
    baseURL: "http://localhost:8080/api/v1"
})

axiosInstace.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        return Promise.reject(error);
    }

)
export default axiosInstace