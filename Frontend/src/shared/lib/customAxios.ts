import axios, { AxiosInstance } from 'axios';

export const customAxios: AxiosInstance = axios.create({
    // baseURL: `${process.env.REACT_APP_API_URL}`,
    baseURL: `${process.env.REACT_APP_TEST_API_URL}`,
    // headers: {
    //     Authorization: sessionStorage.getItem('token'),
    // },
});
