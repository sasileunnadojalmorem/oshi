package com.oshi.ohsi_back.dto.response.category;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.oshi.ohsi_back.common.Responsecode;
import com.oshi.ohsi_back.common.Responsemessage;
import com.oshi.ohsi_back.dto.response.ResponseDto;
import com.oshi.ohsi_back.entity.CategoryEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchCategoryResoponseDto extends ResponseDto{

    private List<CategoryEntity> categories;

    private SearchCategoryResoponseDto (List<CategoryEntity> categories){
        super(Responsecode.SUCCESSS, Responsemessage.SUCCESSS);
        this.categories = categories;
    }

    public static ResponseEntity<? super SearchCategoryResoponseDto> success(List<CategoryEntity> categoryEntityList){
        SearchCategoryResoponseDto responseDto = new SearchCategoryResoponseDto(categoryEntityList);
        return ResponseEntity.status(200).body(responseDto);
    }


    
}
