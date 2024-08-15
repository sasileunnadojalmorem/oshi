// mocks/pagenatedCategory.mock.ts
import { pagenatedCategory } from "types/interface";
import pageInfoMock from "./pageinfo.mock";
import categorymock from "./category.mock";

const pagenatedmock: pagenatedCategory = {
    Categorys: categorymock,  // categorymock는 배열이 아닌 단일 객체여야 합니다.
    Pageinfo: pageInfoMock    // pageInfoMock도 올바른 타입으로 가져옵니다.
};

export default pagenatedmock;
