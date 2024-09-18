import ResponseDto from "../Response.dto";

export default interface SignInResponseDto {
    token: string;
    expirationTime: number;

}