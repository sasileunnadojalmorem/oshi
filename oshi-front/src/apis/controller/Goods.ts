import axios from "axios";
import { AddGoodsRequestDto, DeleteGoodsRequestDto, GetGoodsInfoRequestDto, GetGoodsListRequestDto, SearchGoodsRequestDto, UpdateGoodsRequestDto } from "../request/goods";
import { AddGoodsResponseDto, GetGoodsInfoResponseDto, GetGoodsListResponseDto, SearchGoodsResponseDto } from "../response/goods";
import { ResponseDto } from "../response";

const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api/goods`;

const GOODS_ADD_URL = `${API_DOMAIN}/add`;
const GOODS_LIST_URL = `${API_DOMAIN}/list`;
const GOODS_INFO_URL = `${API_DOMAIN}/info`;
const GOODS_SEARCH_URL = `${API_DOMAIN}/search`;
const GOODS_UPDATE_URL = `${API_DOMAIN}/update`;
const GOODS_DELETE_URL = `${API_DOMAIN}/delete`;

const authorization = (accessToken: string) => {
    return {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    }
}

// Add Goods (with multipart/form-data)
export const addGoodsRequest = async (accessToken: string, requestBody: AddGoodsRequestDto) => {
    const formData = new FormData();
    formData.append("oshiId", requestBody.oshiId.toString());
    formData.append("categoryId", requestBody.categoryId.toString());
    formData.append("name", requestBody.name);
    formData.append("type", requestBody.type.toString());
    if (requestBody.description) {
        formData.append("description", requestBody.description);
    }
    if (requestBody.file) {
        requestBody.file.forEach((file) => {
            formData.append("file", file);
        });
    }

    const result = await axios.post(GOODS_ADD_URL, formData, {
        ...authorization(accessToken),
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
        .then(response => {
            const responseBody: AddGoodsResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response.data) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });

    return result;
}

// Get Goods List
export const getGoodsListRequest = async (requestBody: GetGoodsListRequestDto) => {
    const result = await axios.post(GOODS_LIST_URL, requestBody)
        .then(response => {
            const responseBody: GetGoodsListResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response.data) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });

    return result;
}

// Get Goods Info
export const getGoodsInfoRequest = async (requestBody: GetGoodsInfoRequestDto) => {
    const result = await axios.post(GOODS_INFO_URL, requestBody)
        .then(response => {
            const responseBody: GetGoodsInfoResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response.data) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });

    return result;
}

// Search Goods
export const searchGoodsRequest = async (requestBody: SearchGoodsRequestDto) => {
    const result = await axios.post(GOODS_SEARCH_URL, requestBody)
        .then(response => {
            const responseBody: SearchGoodsResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response.data) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });

    return result;
}

// Update Goods (with multipart/form-data)
export const updateGoodsRequest = async (accessToken: string, requestBody: UpdateGoodsRequestDto) => {
    const formData = new FormData();
    formData.append("name", requestBody.name);
    formData.append("type", requestBody.type.toString());
    if (requestBody.description) {
        formData.append("description", requestBody.description);
    }
    if (requestBody.file) {
        requestBody.file.forEach((file) => {
            formData.append("file", file);
        });
    }

    const result = await axios.patch(GOODS_UPDATE_URL, formData, {
        ...authorization(accessToken),
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
        .then(response => {
            return response.status;
        })
        .catch(error => {
            if (!error.response.data) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });

    return result;
}

// Delete Goods
export const deleteGoodsRequest = async (accessToken: string, requestBody: DeleteGoodsRequestDto) => {
    const result = await axios.delete(GOODS_DELETE_URL, {
        ...authorization(accessToken),
        data: requestBody
    })
        .then(response => {
            return response.status;
        })
        .catch(error => {
            if (!error.response.data) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });

    return result;
}