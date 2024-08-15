export default interface Categoryitem {
    categoryId: number;
    categoryName: string;
    categoryType: '공식' | '비공식';
    description?: string | null; // 선택적 필드에 null을 허용
    oshiId: number;
    oshiName: string;
    categoryimg: string;
}
