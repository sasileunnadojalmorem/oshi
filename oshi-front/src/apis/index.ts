import axios from "axios";
import { SignInRequestDto,SignUpRequestDto } from "./request/auth";
import { SignInResponseDto,SignUpResponseDto } from "./response/auth";
import { ResponseDto } from "./response";
import { error } from "console";
import { GetSignInResponseDto } from "./user";
const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api`;
const SIGN_IN_URL = `${API_DOMAIN}/user/auth/sign-in`;
const SIGN_UP_URL = `${API_DOMAIN}/user/auth/sign-up`;
const GET_SIGN_IN_USER =  `${API_DOMAIN}/user`;
const authorization  = (accessToken:string) =>{
    return {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    }
   
    

}


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
    const result = await axios.post(SIGN_UP_URL,requestbody)
        .then(repsponse => {
            const responseBody: SignUpResponseDto = repsponse.data;
            return responseBody;
        })

        .catch(error =>{
            if(!error.response.data) return null;
            const responseBody : ResponseDto  = error.response.data;
            return responseBody;
        })

        return result;


}

export const getSignInUserRequest = async (accessToken: string) => {

    const result = await axios.get(GET_SIGN_IN_USER, authorization(accessToken))
        .then(response =>{
            const responseBody : GetSignInResponseDto =response.data;
            return responseBody;
        })
        .catch(error =>{ 
            if(!error.response) return null;
            const responseBody : ResponseDto  = error.response.data;
            return responseBody;

        }

        )
        return result;

}