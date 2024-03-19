import axios from 'axios';

export const put = async (url: string, data?: object, token?: string) => {
    await axios.put(`process.env.REACT_APP_API_URL${url}`!, data, {
        headers: {
            token: token,
        },
    });
};
