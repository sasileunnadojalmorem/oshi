
import SaleResponseDto from "./Sale.Response.Dto";
export default interface GetSaleListResponseDto {
    saleList: SaleResponseDto[]; // 세일 목록
    totalPages: number;          // 총 페이지 수
    totalCount: number;          // 총 세일 수
    currentPage: number;
}