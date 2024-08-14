import { Category } from "types/interface";

const categorymock: Category[] = [
    {
        categoryId: 1,
        categoryName: "Official Merchandise",
        categoryType: 'OFFICIAL',
        description: "공식 상품 카테고리",
        oshiId: 1,
        oshiName: "아이유"
    },
    {
        categoryId: 2,
        categoryName: "Fan-made Goods",
        categoryType: 'NONOFFICIAL',
        description: null, // 설명이 없는 경우
        oshiId: 2,
        oshiName: "BTS"
    }
];
export default categorymock;
