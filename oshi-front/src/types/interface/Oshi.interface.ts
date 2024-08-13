export default interface Oshiitem {
    oshiId: number;
    oshiName: string;
    oshiImage: string | null; // null을 허용
    description?: string | null; // 선택적 필드에 null을 허용
}
