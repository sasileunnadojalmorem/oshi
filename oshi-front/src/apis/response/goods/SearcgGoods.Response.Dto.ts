import GoodsResponseDto from "./Goods.Response.Dto";

export default interface SearchGoodsResponseDto {
    baseGoodsEntity: GoodsResponseDto[]; // 검색된 굿즈 리스트
    totalpages: number;
    totalCount: number;
}