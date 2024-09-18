import ResponseDto from "../Response.dto";

export default interface OshiAddResponseDto  extends ResponseDto{
    oshiid:number;
    name:string;
    description:string;
    ohsiimageurl:string;
}