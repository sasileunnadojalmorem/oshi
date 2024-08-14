export default interface Goodsitem {
    goodsId: number;
    goodsName: string;
    goodsImage: string | null; // null을 허용
    description?: string | null; // 선택적 필드에 null을 허용
    viewCount: number;
    favoriteCount: number;
    goodsType: string;
    oshiId: number;
    oshiName: string;
    categoryId: number;
    categoryName: string;
    categoryType: '공식' | '비공식';
    authorId?: number | null; // 선택적 필드에 null을 허용
    authorName?: string | null; // 선택적 필드에 null을 허용
}
