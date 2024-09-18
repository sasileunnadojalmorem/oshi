import axios from "axios";
import { GetSigninUserResponseDto } from "../response/user";
import { ResponseDto } from "../response";

const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api/user`;
const GET_SIGNIN_USER_URL = `${API_DOMAIN}`;

const authorization = (accessToken: string) => {
    return {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    }
}

// Get Sign-In User Information
export const getSignInUserRequest = async (accessToken: string) => {
    const result = await axios.get(GET_SIGNIN_USER_URL, authorization(accessToken))
        .then(response => {
            const responseBody: GetSigninUserResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}