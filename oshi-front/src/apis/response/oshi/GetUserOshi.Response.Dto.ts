import OshiResponseDto from "./Oshi.Response.Dto";

export default interface GetUserOshiResponseDto {
    oshiList: OshiResponseDto[]; // 유저가 좋아하는 오시 리스트
}