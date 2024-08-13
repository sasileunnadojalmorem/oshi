export default interface Category {
    categoryId: number;
    categoryName: string;
    categoryType: 'OFFICIAL' | 'NONOFFICIAL';
    description?: string | null; // 선택적 필드에 null을 허용
    oshiId: number;
    oshiName: string;
}
