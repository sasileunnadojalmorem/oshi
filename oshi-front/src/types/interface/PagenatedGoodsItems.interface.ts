import goodspageinfo from "./goodsitem/goodspageinfo.interface";
import Goodsitem from "./goodsitem/Goods.interface";

export default interface PageinatedGoods{
    Goods: Goodsitem[];
    Pageinfo: goodspageinfo;
}