export default interface UpdateGoodsRequestDto {
    goodsId: number; // @NotNull -> number 타입
    name: string; // @NotBlank -> string 타입
    description?: string; // Optional field
    type: number; // @NotNull -> number 타입
    file?: File[]; // MultipartFile 리스트 -> File 배열로 변환
  }