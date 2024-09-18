import axios from "axios";
import { AddSaleRequestDto, UpdateSaleRequestDto, DeleteSaleRequestDto, GetSaleListRequestDto } from "../request/sale";
import { AddSaleResponseDto, GetSaleInfoResponseDto, GetSaleListResponseDto } from "../response/sale";
import { ResponseDto } from "../response";

const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api/sale`;

const SALE_ADD_URL = `${API_DOMAIN}/add`;
const SALE_INFO_URL = `${API_DOMAIN}`;
const SALE_LIST_URL = `${API_DOMAIN}/list`;
const SALE_UPDATE_URL = `${API_DOMAIN}/update`;
const SALE_DELETE_URL = `${API_DOMAIN}/delete`;

const authorization = (accessToken: string) => {
    return {
        headers: {
            Authorization: `Bearer ${accessToken}`,
            "Content-Type": "multipart/form-data"
        }
    }
}

// Add Sale (Form Data Handling)
export const addSaleRequest = async (accessToken: string, requestBody: AddSaleRequestDto) => {
    const formData = new FormData();
    formData.append('oshiId', requestBody.oshiId.toString());
    formData.append('categoryId', requestBody.categoryId.toString());
    formData.append('goodsId', requestBody.goodsId.toString());
    formData.append('price', requestBody.price.toString());
    formData.append('description', requestBody.description);
    if (requestBody.file) {
        formData.append('file', requestBody.file);
    }

    const result = await axios.post(SALE_ADD_URL, formData, authorization(accessToken))
        .then(response => {
            const responseBody: AddSaleResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}

// Get Sale Info
export const getSaleInfoRequest = async (saleId: number) => {
    const result = await axios.get(`${SALE_INFO_URL}/${saleId}`)
        .then(response => {
            const responseBody: GetSaleInfoResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}

// Get Sale List
export const getSaleListRequest = async (requestBody: GetSaleListRequestDto) => {
    const result = await axios.post(SALE_LIST_URL, requestBody)
        .then(response => {
            const responseBody: GetSaleListResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}

// Update Sale (Form Data Handling)
export const updateSaleRequest = async (accessToken: string, requestBody: UpdateSaleRequestDto) => {
    const formData = new FormData();
    formData.append('saleId', requestBody.saleId.toString());
    formData.append('price', requestBody.price.toString());
    formData.append('description', requestBody.description);
    formData.append('state', requestBody.state); // Enum string 처리
    if (requestBody.file) {
        formData.append('file', requestBody.file);
    }

    const result = await axios.put(SALE_UPDATE_URL, formData, authorization(accessToken))
        .then(() => {
            return true;  // 성공 시 true 반환
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}

// Delete Sale
export const deleteSaleRequest = async (accessToken: string, requestBody: DeleteSaleRequestDto) => {
    const result = await axios.delete(SALE_DELETE_URL, {
        headers: {
            Authorization: `Bearer ${accessToken}`
        },
        data: requestBody  // DELETE 요청 시에는 data 필드에 requestBody를 전달
    })
        .then(() => {
            return true;  // 성공 시 true 반환
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}