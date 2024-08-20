// paths.js
export const MAIN_PATH = () => '/';
export const AUTH_PATH = () => '/auth';
export const OSHI_PATH = () => '/oshi';
export const ADD_PATH = () => 'add';
export const OSHIID_PATH = (oshiid:number | string) => `${oshiid}`;
export const GOODSID_PATH = (goodsid:number | string) => `${goodsid}`;
export const CATEGORY_PATH = () => 'category';
export const GOODS_PATH = () => 'goods';
export const UPDATE_PATH = () => 'update';
export const DETAIL_PATH = () => 'detail';
export const SALE_PATH = () => '/goods/sale';
export const SALEID_PATH = (salesid:number  | string) => `${salesid}`;
export const USER_PATH = (userEmail:string) => `/user/${userEmail}`;
export const OSHI_SHOW_PATH = (oshiid:number | string) => `show/${oshiid}`
export const GOODS_UPDATE_PATH =(goodsid:number | string) => `update/${goodsid}`
export const GOODS_DETAIL_PATH = (goodsid:number | string)  => `detail/${goodsid}`
export const SALES_UPDATE_PATH =(salesid:number | string) => `update/${salesid}`
export const SALES_DETAIL_PATH = (salesid:number | string)  => `detail/${salesid}`

