import axios from "axios";
import { ResponseDto } from "../response";

const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api/user/file`;

const authorization = (accessToken: string) => {
    return {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    };
}

// 이미지 가져오기
export const getImageRequest = async (fileName: string, accessToken: string) => {
    const IMAGE_URL = `${API_DOMAIN}/${fileName}`;
    
    const result = await axios.get(IMAGE_URL, {
        responseType: 'blob' // 이미지 같은 binary 데이터를 받기 위해 responseType을 설정
    })
    .then(response => {
        return URL.createObjectURL(response.data); // 이미지 URL 생성
    })
    .catch(error => {
        if (!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
    });
    return result;  
}