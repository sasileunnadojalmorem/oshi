import { PagenatedGoods } from "types/interface";
import pageInfoMock from "./pageinfo.mock";
import goodsmock from "./goods.mock";


const pagenatedgoodsmock: PagenatedGoods = {
    Goods : goodsmock,
    Pageinfo : pageInfoMock
};


export default pagenatedgoodsmock;