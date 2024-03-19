import axios from 'axios';

export const post = async (url: string, data?: object, token?: string) => {
    await axios.post(`process.env.REACT_APP_API_URL${url}`!, data, {
        headers: {
            token: token,
        },
    });
};
