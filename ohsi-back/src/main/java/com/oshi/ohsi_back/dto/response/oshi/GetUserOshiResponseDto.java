package com.oshi.ohsi_back.dto.response.oshi;

import lombok.Getter;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.OshiEntity;

@Getter
public class GetUserOshiResponseDto extends ResponseDto {
    private List<OshiEntity> oshiList;

    private GetUserOshiResponseDto(List<OshiEntity> userOshiList) {
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        this.oshiList = userOshiList;
    }

    public static ResponseEntity<? super GetUserOshiResponseDto> success(List<OshiEntity> userOshiList) {
        GetUserOshiResponseDto responseBody = new GetUserOshiResponseDto(userOshiList);
        return ResponseEntity.status(200).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto responseBody = new ResponseDto(Responsecode.NOT_EXISTED_USER, Responsemessage.NOT_EXISTED_USER);
        return ResponseEntity.status(401).body(responseBody);
    }

}