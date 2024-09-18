import axios from "axios";
import { GetOshiRequestDto,SearchOshiRequestDto,OshiAddRequestDto } from "apis/request/oshi";
import { OshiResponseDto, GetOshiResponseDto, SearchOshiResponseDto } from "../response/oshi";
import { ResponseDto } from "../response";

// API 엔드포인트 설정
const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api/oshi`;

const OSHI_ADD_URL = `${API_DOMAIN}/add`;
const OSHI_GET_URL = `${API_DOMAIN}`;
const OSHI_SEARCH_URL = `${API_DOMAIN}/search`;

// 인증 토큰을 헤더에 추가하는 함수
const authorization = (accessToken: string) => {
  return {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  };
};

// 오시 추가 API
export const postOshi = async (
    accessToken: string,
    requestBody: OshiAddRequestDto
  ): Promise<OshiResponseDto | ResponseDto | null> => {
    // FormData 생성
    const formData = new FormData();
    formData.append('name', requestBody.name);
    formData.append('description', requestBody.description);
    if (requestBody.file) {
      formData.append('file', requestBody.file);
    }
  
    try {
      const response = await axios.post(OSHI_ADD_URL, formData, {
        headers: {
          ...authorization(accessToken).headers,
          'Content-Type': 'multipart/form-data',
        },
      });
      return response.data as OshiResponseDto;
    } catch (error: any) {
      if (!error.response?.data) return null;
      return error.response.data as ResponseDto;
    }
  };

// 오시 조회 API
export const getOshi = async (
  accessToken: string,
  requestBody: GetOshiRequestDto
): Promise<GetOshiResponseDto | ResponseDto | null> => {
  const result = await axios
    .post(OSHI_GET_URL, requestBody, authorization(accessToken))
    .then((response) => {
      const responseBody: GetOshiResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response?.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
};

// 오시 검색 API
export const searchOshi = async (
  requestBody: SearchOshiRequestDto
): Promise<SearchOshiResponseDto | ResponseDto | null> => {
  const result = await axios
    .post(OSHI_SEARCH_URL, requestBody)
    .then((response) => {
      const responseBody: SearchOshiResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response?.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
};