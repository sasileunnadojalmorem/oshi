export default interface GetCategoryListRequestDto {
    oshiid: number; // NotNull -> number 타입
    pagenum: number; // NotNull -> number 타입
    sortedBy: string; // NotBlank -> string 타입
  }
  