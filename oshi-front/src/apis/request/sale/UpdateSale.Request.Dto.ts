export default interface UpdateSaleRequestDto {
    saleId: number; // @NotNull -> number 타입
    price: number; // BigDecimal을 number로 변환
    description: string; // @NotNull -> string 타입
    state: string; // SaleStatusEnum -> string 타입으로 변환
    file?: File; // MultipartFile -> File, 선택 필드로 변환
  }