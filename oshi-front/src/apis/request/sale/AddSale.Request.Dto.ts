export default interface AddSaleRequestDto {
    oshiId: number; // @NotNull -> number 타입
    categoryId: number; // @NotNull -> number 타입
    goodsId: number; // @NotNull -> number 타입
    price: number; // BigDecimal을 number로 변환
    description: string; // @NotNull -> string 타입
    file?: File; // MultipartFile -> File, 선택 필드로 변환
  }
  