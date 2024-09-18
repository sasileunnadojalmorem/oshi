import axios from "axios";
import { AddUserOshiResponseDto, GetUserOshiResponseDto } from "../response/oshi";
import { ResponseDto } from "../response";

const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api/user/oshi`;
const ADD_USER_OSHI_URL = `${API_DOMAIN}/`;
const GET_USER_OSHI_URL = `${API_DOMAIN}/get`;

const authorization = (accessToken: string) => {
    return {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    }
}

// Add User Oshi (POST)
export const addUserOshiRequest = async (accessToken: string) => {
    const result = await axios.post(ADD_USER_OSHI_URL, authorization(accessToken))
        .then(response => {
            const responseBody: AddUserOshiResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}

// Get User Oshi List (GET)
export const getUserOshiListRequest = async (accessToken: string) => {
    const result = await axios.get(GET_USER_OSHI_URL, authorization(accessToken))
        .then(response => {
            const responseBody: GetUserOshiResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}