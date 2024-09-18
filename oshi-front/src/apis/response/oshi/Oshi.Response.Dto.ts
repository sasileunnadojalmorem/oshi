export default interface OshiResponseDto {
    oshiId: number;
    name: string;
    description: string;
    imageUrl?: string; // Optional, 이미지 URL
    status?: number;   // Optional, 상태 (필요시 사용)
}