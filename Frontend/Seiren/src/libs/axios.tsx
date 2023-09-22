import axios from "axios";

const SERVER_ADDRESS = `http://192.168.30.130:8080`;

export const customAxios = axios.create({
  baseURL: `${SERVER_ADDRESS}/api/`,
  headers: {
    "Content-Type": "application/json",
  },
});

customAxios.interceptors.request.use(
  (config) => {
    const accessToken = localStorage.getItem("accessToken");
    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);