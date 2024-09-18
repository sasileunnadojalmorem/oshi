package com.oshi.ohsi_back.dto.response.category;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchCategoryResoponseDto extends ResponseDto{

    private Page<CategoryResponseDto> categoryEntites;


    private SearchCategoryResoponseDto (Page<CategoryResponseDto> categories){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        this.categoryEntites = categories;
    }

    public static ResponseEntity<? super SearchCategoryResoponseDto> success(Page<CategoryResponseDto> categoryEntityList){
        SearchCategoryResoponseDto responseDto = new SearchCategoryResoponseDto(categoryEntityList);
        return ResponseEntity.status(200).body(responseDto);
    }
}
