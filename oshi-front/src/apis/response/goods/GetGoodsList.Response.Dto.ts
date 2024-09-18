import GoodsResponseDto from "./Goods.Response.Dto";

export default interface GetGoodsListResponseDto {
    goodsList: GoodsResponseDto[]; // 여러 굿즈 정보를 담는 리스트
    totalPages: number;
    totalCount: number;
    currentPage: number;
}