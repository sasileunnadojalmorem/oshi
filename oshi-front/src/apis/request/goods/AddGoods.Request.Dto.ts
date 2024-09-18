export default interface AddGoodsRequestDto {
    oshiId: number; // @NotNull -> number 타입
    categoryId: number; // @NotNull -> number 타입
    name: string; // @NotBlank -> string 타입
    type: number; // @NotNull -> number 타입
    description?: string; // Optional field
    file?: File[]; // MultipartFile 리스트 -> File 배열로 변환
  }
  