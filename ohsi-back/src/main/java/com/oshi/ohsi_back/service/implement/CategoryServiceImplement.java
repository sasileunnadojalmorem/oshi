package com.oshi.ohsi_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oshi.ohsi_back.dto.request.category.AddCategoryRequsetDto;
import com.oshi.ohsi_back.dto.response.category.AddCategoryResponseDto;
import com.oshi.ohsi_back.entity.CategoryEntity;
import com.oshi.ohsi_back.repository.AuthorRepository;
import com.oshi.ohsi_back.repository.CategoryRepository;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.service.CategoryService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor    
public class CategoryServiceImplement  implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    @Override
    public ResponseEntity<?super AddCategoryResponseDto> AddCategory(AddCategoryRequsetDto dto,String email) {
       try {
        boolean existsUser = userRepository.existsByEmail(email);
        if(!existsUser) return AddCategoryResponseDto.notExistUser();
        boolean existsname = categoryRepository.existsByname(dto.getName());
        if(existsname) return AddCategoryResponseDto.duplicationName();
        String type = dto.getType();
        if(type == "NONOFFICIAL") 
        {
            boolean existAuthor = authorRepository.existsById(dto.getAuthorid());
            if(!existAuthor) return AddCategoryResponseDto.notExistUser();
        }
        CategoryEntity categoryEntity = new CategoryEntity(dto);
        categoryRepository.save(categoryEntity);
        return AddCategoryResponseDto.success(categoryEntity);
        
       } catch (Exception e) {
        e.printStackTrace();
        return  AddCategoryResponseDto.databaseError();
       }
    }

}
