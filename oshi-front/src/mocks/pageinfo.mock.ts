// mocks/pageinfomock.ts
import categorypageinfo from "types/interface/categoryitem/Categorypageinfo.interface";

const pageInfoMock: categorypageinfo = {
    currentPage: 1,      // 현재 페이지 번호
    totalPages: 10,      // 전체 페이지 수
    pageSize: 20,    // 페이지당 항목 수
    totalItems: 200,     // 전체 항목 수
};
export default pageInfoMock;