import { Goodsitem } from "types/interface";

const goodsmock: Goodsitem[] = [
    {
        goodsId: 1,
        goodsName: "아이유 포스터",
        goodsImage: "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSoYbbtK5Lg__VZyBeun21VoEJmsOWcz2yczGdB7vw5RSkb39gL",
        description: "아이유의 2024 시즌 포스터",
        viewCount: 1000,
        favoriteCount: 250,
        goodsType: "포스터",
        oshiId: 1,
        oshiName: "아이유",
        categoryId: 1,
        categoryName: "Official Merchandise",
        categoryType: '공식',
        authorId: null, // 저자가 없는 경우
        authorName: null // 저자가 없는 경우
    },
    {
        goodsId: 2,
        goodsName: "BTS 팬메이드 티셔츠",
        goodsImage: null,  // 이미지가 없는 경우
        description: null, // 설명이 없는 경우
        viewCount: 500,
        favoriteCount: 150,
        goodsType: "의류",
        oshiId: 2,
        oshiName: "BTS",
        categoryId: 2,
        categoryName: "Fan-made Goods",
        categoryType: '비공식',
        authorId: 123,
        authorName: "팬클럽"
    }
];

export default goodsmock;