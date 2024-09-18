import axios from "axios";
import { GetGoodsTypeResponseDto } from "../response/goodsType";
import { ResponseDto } from "../response";

const DOMAIN = "http://localhost:8080";
const API_DOMAIN = `${DOMAIN}/api/goodsType`;

// Get Goods Type List
export const getGoodsTypeRequest = async () => {
    const result = await axios.get(API_DOMAIN)
        .then(response => {
            const responseBody: GetGoodsTypeResponseDto = response.data;
            return responseBody;
        })
        .catch(error => {
            if (!error.response) return null;
            const responseBody: ResponseDto = error.response.data;
            return responseBody;
        });
    return result;
}