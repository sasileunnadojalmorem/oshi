import CategoryResponseDto from "./Category.Response.Dto";
export default interface GetCategoryListResponseDto {
    categoryList: CategoryResponseDto[]; // 여러 카테고리를 담는 리스트
    totalPages: number;
    totalCount: number;
    currentPage: number;
}