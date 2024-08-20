export default interface Salesitem {
    oshiid: number;
    ohsiname: string;
    oshiImage: string | null;
    categoryid: number;
    categoryname: string;
    categorytype: '공식' | '비공식';
    goodsid: number;
    goodsname: string;
    goodsImage: string | null;
    authorid: string | null;
    authorname: string | null;
    goodstype: string;

    // Sales 관련 필드
    salesid: number;
    salesprice: number;
    salesstatus: number;
    salessellername: string;
    salessellerid: string;
    salesdescription: string | null;
}
