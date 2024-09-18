import CategoryResponseDto from "./Category.Response.Dto";
export default interface SearchCategoryResponseDto {
    categoryEntities: CategoryResponseDto[]; // 검색된 카테고리 리스트
}