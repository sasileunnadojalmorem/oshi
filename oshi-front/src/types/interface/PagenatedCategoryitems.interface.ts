import Categoryitem from "./categoryitem/Category.interface";
import categorypageinfo from "./categoryitem/Categorypageinfo.interface";

export default interface pagenatedCategory {
    Categorys:Categoryitem[];
    Pageinfo: categorypageinfo;
    

}