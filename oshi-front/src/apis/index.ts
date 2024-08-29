import axios from "axios";
import { SignInRequestDto,SignUpRequestDto } from "./request/auth";
import { SignInResponseDto,SignUpResponseDto } from "./response/auth";
import { ResponseDto } from "./response";
const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api`;
const SIGN_IN_URL = `${API_DOMAIN}/user/auth/sign-in`;
const SIGN_UP_URL = `${API_DOMAIN}/user/auth/sign-up`;


export const signInRequset = async (requestbody: SignInRequestDto) => {
    const result = await axios.post(SIGN_IN_URL,requestbody)
        .then(repsponse => {
            const responseBody : SignInResponseDto = repsponse.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.response.data) return null;

            const responseBody : ResponseDto  = error.response.data;
            return responseBody;
        }

        )
        return result;
}

export const signUpRequest = async (requestbody: SignUpRequestDto) => {


}
  