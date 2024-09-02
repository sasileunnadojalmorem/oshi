package com.oshi.ohsi_back.dto.response.Sale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.SaleEntity;

import lombok.Getter;


@Getter
public class AddSaleResponseDto extends ResponseDto{

    private int Goods_id;

    private AddSaleResponseDto(SaleEntity saleEntity){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        this.Goods_id = saleEntity.getGoods_id();
    }

    public static ResponseEntity<AddSaleResponseDto> success(SaleEntity saleEntity){
        AddSaleResponseDto responsebody = new AddSaleResponseDto(saleEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responsebody);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto responsebody = new ResponseDto(Responsecode.NOT_EXISTED_USER,Responsemessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responsebody);

    }
    
    public static ResponseEntity<ResponseDto> duplicationName() {
        ResponseDto result = new ResponseDto(Responsecode.DUPLICATE_NAME,Responsemessage.DUPLICATE_NAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
