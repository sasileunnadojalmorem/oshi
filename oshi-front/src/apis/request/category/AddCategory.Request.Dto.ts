export default interface AddCategoryRequestDto {
    oshiId: number; // NotNull -> number 타입
    name: string; // NotBlank -> string 타입
    description?: string; // Optional field
    type: string; // NotBlank -> string 타입
    file?: File; // MultipartFile -> File 타입
    authorid?: number; // Optional field
  }