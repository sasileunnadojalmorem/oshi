export default interface SaleResponseDto {
    salesId: number;
    writerId: number;
    writerName: string;
    oshiId: number;
    oshiName: string;
    categoryId: number;
    categoryName: string;
    goodsId: number;
    goodsName: string;
    price: number;
    status: string; // Enum일 경우 string으로
    imageId?: number; // Optional
    description: string;
    imageUrl?: string; // Optional
}