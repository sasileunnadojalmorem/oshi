import axios from "axios";
import { SignInRequestDto, SignUpRequestDto } from "../request/auth";
import { SignInResponseDto, SignUpResponseDto } from "../response/auth";
import { ResponseDto } from "../response";

const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api/user/auth`;

const SIGN_IN_URL = `${API_DOMAIN}/sign-in`;
const SIGN_UP_URL = `${API_DOMAIN}/sign-up`;

export const signInRequest = async (requestBody: SignInRequestDto) => {
    const result = await axios.post(SIGN_IN_URL, requestBody)
        .then(response => {
            const responseBody: SignInResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response.data) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}

export const signUpRequest = async (requestBody: SignUpRequestDto) => {
    const result = await axios.post(SIGN_UP_URL, requestBody)
        .then(response => {
            const responseBody: SignUpResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response.data) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}