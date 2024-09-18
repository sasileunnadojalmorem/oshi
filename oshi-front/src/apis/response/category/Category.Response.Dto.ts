export default interface CategoryResponseDto {
    categoryId: number;
    imageUrl: string;
    oshiId: number;
    oshiName: string;
    description: string;
    categoryName: string;
    categoryType: string; // Enum 같은 경우 string으로 처리
    authorId?: number; // Optional
    authorName?: string; // Optional
}