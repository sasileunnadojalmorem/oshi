import { ResponseCode } from "../../types/enum";
export default interface ResponseDto{
    code : ResponseCode;
    message : string;
    timestamp: string;  // 오류 발생 시점 (ISO 8601 형식으로 제공)
    statusCode: number;  // HTTP 상태 코드 값
    statusCodeName: string;  // HTTP 상태 코드 이름 (예: "BAD_REQUEST")
    runtimeValue?: string;  // 런타임 시 제공되는 추가

}