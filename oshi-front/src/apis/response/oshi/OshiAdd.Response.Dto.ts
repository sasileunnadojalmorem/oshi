import ResponseDto from "../Response.dto";

export default interface OshiAddResponseDto  extends ResponseDto{
    id:number;
    name:string;
    description:string;
    ohsiimageurl:string;

}