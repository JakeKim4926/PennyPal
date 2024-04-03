import axios, { AxiosInstance } from 'axios';
import { getCookie } from './cookieHelper';

export const customAxios: AxiosInstance = axios.create({
    baseURL: `${process.env.REACT_APP_API_URL}`,
});

export const dataAxios: AxiosInstance = axios.create({
    baseURL: `${process.env.REACT_APP_DATA_URL}`,
});

// 요청 인터셉터를 추가하여 요청을 보내기 전에 실행되는 로직을 정의합니다.
customAxios.interceptors.request.use(
    (config) => {
        const token = getCookie('memberToken'); // 'memberToken' 쿠키의 값을 가져옵니다.

        // 'memberToken' 쿠키의 값이 null이 아니면 Authorization 헤더에 추가합니다.
        if (token) {
            config.headers['Authorization'] = 'Bearer' + ' ' + token;
        }

        return config; // 수정된 설정으로 요청을 계속 진행합니다.
    },
    (error) => {
        // 요청 설정 중 오류가 발생한 경우 처리합니다.
        console.log(error);
        // 에러403인경우 토큰이 만료되어서 재로그인을 필요로함.

        //로그인 페이지로, 혹은 모달로 처리.  모달처리가 더 낫다. 작업중이던 페이지를 유지시키기위해서.
        // 페이지로 구현한 SignIn.tsx를 모달로 가능??
        return Promise.reject(error);
    },
);

dataAxios.interceptors.request.use(
    (config) => {
        const token = getCookie('memberToken'); // 'memberToken' 쿠키의 값을 가져옵니다.

        // 'memberToken' 쿠키의 값이 null이 아니면 Authorization 헤더에 추가합니다.
        if (token) {
            config.headers['Authorization'] = 'Bearer' + ' ' + token;
        }

        return config; // 수정된 설정으로 요청을 계속 진행합니다.
    },
    (error) => {
        // 요청 설정 중 오류가 발생한 경우 처리합니다.
        console.log(error);
        // 에러403인경우 토큰이 만료되어서 재로그인을 필요로함.

        //로그인 페이지로, 혹은 모달로 처리.  모달처리가 더 낫다. 작업중이던 페이지를 유지시키기위해서.
        // 페이지로 구현한 SignIn.tsx를 모달로 가능??
        return Promise.reject(error);
    },
);
