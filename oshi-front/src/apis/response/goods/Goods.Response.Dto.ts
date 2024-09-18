import { ImageDto } from "../image";
export default interface GoodsResponseDto {
    oshiId: number;
    oshiName: string;
    categoryId: number;
    categoryName: string;
    goodsId: number;
    name: string;
    description: string;
    viewCount: number;
    favoriteCount: number;
    images: ImageDto[]; // Image DTO는 따로 정의
}   