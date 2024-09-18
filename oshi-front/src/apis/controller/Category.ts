import axios from "axios";
import { AddCategoryRequestDto,
    GetCategoryInfoRequestDto,
    GetCategoryListRequestDto,
    SearchCategoryRequestDto } from "apis/request/category";
import {
    AddCategoryResponseDto,
    GetCategoryInfoResponseDto,
    GetCategoryListResponseDto,
    SearchCategoryResponseDto
} from "../response/category";
import { ResponseDto } from "../response";

const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api/category`;

const CATEGORY_ADD_URL = `${API_DOMAIN}/add`;
const CATEGORY_LIST_URL = `${API_DOMAIN}/list`;
const CATEGORY_INFO_URL = `${API_DOMAIN}/info`;
const CATEGORY_SEARCH_URL = `${API_DOMAIN}/search`;

const authorization = (accessToken: string) => {
    return {
        headers: {
            Authorization: `Bearer ${accessToken}`,
            "Content-Type": "multipart/form-data"
        }
    }
}

// Add Category (Form Data Handling)
export const addCategoryRequest = async (accessToken: string, requestBody: AddCategoryRequestDto) => {
    const formData = new FormData();
    formData.append('oshiId', requestBody.oshiId.toString());
    formData.append('name', requestBody.name);
    if (requestBody.description) {
        formData.append('description', requestBody.description);
    }
    formData.append('type', requestBody.type);
    if (requestBody.file) {
        formData.append('file', requestBody.file);
    }
    if (requestBody.authorid) {
        formData.append('authorid', requestBody.authorid.toString());
    }

    const result = await axios.post(CATEGORY_ADD_URL, formData, authorization(accessToken))
        .then(response => {
            const responseBody: AddCategoryResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}

// Get Category List
export const getCategoryListRequest = async (requestBody: GetCategoryListRequestDto) => {
    const result = await axios.post(CATEGORY_LIST_URL, requestBody)
        .then(response => {
            const responseBody: GetCategoryListResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}

// Get Category Info
export const getCategoryInfoRequest = async (requestBody: GetCategoryInfoRequestDto) => {
    const result = await axios.post(CATEGORY_INFO_URL, requestBody)
        .then(response => {
            const responseBody: GetCategoryInfoResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}

// Search Category
export const searchCategoryRequest = async (requestBody: SearchCategoryRequestDto) => {
    const result = await axios.post(CATEGORY_SEARCH_URL, requestBody)
        .then(response => {
            const responseBody: SearchCategoryResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}