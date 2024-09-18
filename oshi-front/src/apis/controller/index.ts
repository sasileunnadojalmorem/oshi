import axios from "axios";
import { SignInRequestDto,SignUpRequestDto } from "../request/auth";
import { SignInResponseDto,SignUpResponseDto } from "../response/auth";
import { ResponseDto } from "../response";
import { GetSigninUserResponseDto } from "../response/user";
import OshiAddRequestDto from "../request/oshi/OshiAdd.request.dto";
import OshiAddResponseDto from "../response/oshi/OshiAdd.Response.Dto";

import { SaveImageResponseDto, UrlResponseDto } from "../response/image";
import { SaveImageRequsetDto } from "../request/image";
const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api`;
const SIGN_IN_URL = `${API_DOMAIN}/user/auth/sign-in`;
const SIGN_UP_URL = `${API_DOMAIN}/user/auth/sign-up`;
const GET_SIGN_IN_USER =  `${API_DOMAIN}/user`;
const OSHI_ADD_URL = `${API_DOMAIN}/oshi/add`;
const SAVE_IMAGE_URL = `${API_DOMAIN}/user/file/saveImage`;
const IMAGE_UPLOAD = `${API_DOMAIN}/user/file/upload`;
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
            const responseBody : GetSigninUserResponseDto = response.data;
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

export const OshiAddRequset = async (accessToken: string, requestBody:  OshiAddRequestDto) =>{
    const result = await axios.post(OSHI_ADD_URL, requestBody, authorization(accessToken))
        .then(response =>{
                const responseBody : OshiAddResponseDto = response.data;
                return responseBody;
            })
        .catch(error =>{
            if(!error.response.data) return null;
            const responseBody : ResponseDto  = error.response.data;
            return responseBody;
        })
        return result;
}


  export const upload = async (accessToken: string, file: FormData) =>{
    const result = await axios.post(IMAGE_UPLOAD,file,{headers: {
        'Content-Type': 'multipart/form-data',
        ...(accessToken ? { Authorization: `Bearer ${accessToken}` } : {})
      }})
        .then(response =>{
                const responseBody : UrlResponseDto = response.data;
                return responseBody;
            })
        .catch(error =>{
            if(!error.response.data) return null;
            const responseBody : ResponseDto  = error.response.data;
            return responseBody;
        })
        return result;
}

export const SaveImage = async (accessToken: string,requestBody : SaveImageRequsetDto ) =>
{
    const result  = await axios.post(SAVE_IMAGE_URL, requestBody, authorization(accessToken))
        .then(response =>{
            console.log(requestBody);
            const responseBody : SaveImageResponseDto = response.data;
            return responseBody;
        })
        .catch(error =>{
            if(!error.response) return null;
            const responseBody : ResponseDto  = error.response.data;
            return responseBody;
        })

        return result;
}
    