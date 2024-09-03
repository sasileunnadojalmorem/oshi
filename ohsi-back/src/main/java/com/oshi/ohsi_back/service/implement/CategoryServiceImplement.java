package com.oshi.ohsi_back.service.implement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.oshi.ohsi_back.dto.request.category.AddCategoryRequsetDto;
import com.oshi.ohsi_back.dto.request.category.GetCategoryRequseDto;
import com.oshi.ohsi_back.dto.response.category.AddCategoryResponseDto;
import com.oshi.ohsi_back.dto.response.category.GetCategoryResponseDto;
import com.oshi.ohsi_back.entity.CategoryEntity;
import com.oshi.ohsi_back.repository.AuthorRepository;
import com.oshi.ohsi_back.repository.CategoryRepository;
import com.oshi.ohsi_back.repository.OshiRepository;
import com.oshi.ohsi_back.repository.UserRepository;
import com.oshi.ohsi_back.service.CategoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor    
public class CategoryServiceImplement  implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final OshiRepository oshiRepository;


    @Override
    public ResponseEntity<?super AddCategoryResponseDto> AddCategory(AddCategoryRequsetDto dto, String email) {
        try {
            boolean existsUser = userRepository.existsByEmail(email);
            if (!existsUser) return AddCategoryResponseDto.notExistUser();

            boolean existsName = categoryRepository.existsByName(dto.getName());
            if (existsName) return AddCategoryResponseDto.duplicationName();

            String type = dto.getType();
            if ("NONOFFICIAL".equals(type)) {
                boolean existAuthor = authorRepository.existsById(dto.getAuthorid());
                if (!existAuthor) return AddCategoryResponseDto.notExistUser();
            }
            if("OFFICIAL".equals(type)) {
                if(dto.getAuthorid() != null){
                    return AddCategoryResponseDto.databaseError();
                } 
            }

            CategoryEntity categoryEntity = new CategoryEntity(dto);
            categoryRepository.save(categoryEntity);
            return AddCategoryResponseDto.success(categoryEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return AddCategoryResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetCategoryResponseDto> getcategorybyoshiid(GetCategoryRequseDto dto) {
        int oshi_id = dto.getOshiid();
        int page_size = 30;

        try {
            boolean existsOshi = oshiRepository.existsById(dto.getOshiid());
            if (!existsOshi) return GetCategoryResponseDto.notExistBoard();

            Sort sortOrder = "recent".equals(dto.getSortedBy()) 
                                ? Sort.by("categoryId").descending()
                                : Sort.by("categoryId").ascending();

            Pageable pageable = PageRequest.of(dto.getPagenum(), page_size, sortOrder);
            Page<CategoryEntity> categoryEntityList = categoryRepository.findByOshiId(oshi_id, pageable);

            

            return GetCategoryResponseDto.success(categoryEntityList);

        } catch (Exception e) {
            e.printStackTrace();
            return GetCategoryResponseDto.databaseError();
        }
    }
}