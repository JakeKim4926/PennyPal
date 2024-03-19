import axios from 'axios';

export const get = async (url: string, data?: object, token?: string, responseType?: any) => {
    await axios.get(`process.env.REACT_APP_API_URL${url}`!, {
        responseType: responseType,
        headers: {
            token: token,
        },
    });
};
