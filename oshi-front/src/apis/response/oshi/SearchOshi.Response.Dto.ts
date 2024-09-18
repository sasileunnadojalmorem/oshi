import OshiResponseDto from "./Oshi.Response.Dto";

export default interface SearchOshiResponseDto {
    oshiEntityList: OshiResponseDto[]; // 검색된 오시 리스트
}