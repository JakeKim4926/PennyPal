import axios from 'axios';

export const get = async (url: string, data?: object, token?: string) => {
    await axios.delete(`process.env.REACT_APP_API_URL${url}`!, {
        headers: {
            token: token,
        },
    });
};
