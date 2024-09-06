    package com.oshi.ohsi_back.controller;
    import javax.validation.Valid;

import org.springframework.core.io.Resource;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.annotation.AuthenticationPrincipal;
    import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.multipart.MultipartFile;

    import com.fasterxml.jackson.core.JsonProcessingException;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.oshi.ohsi_back.dto.request.Image.ImageRequestDto;
    import com.oshi.ohsi_back.dto.response.image.ImageResponseDto;
    import com.oshi.ohsi_back.service.Fileservice;

    import lombok.RequiredArgsConstructor;

    @RestController
    @RequestMapping("/api/user/file")
    @RequiredArgsConstructor
public class Filecontrollor {

    private final Fileservice fileservice;

   @PostMapping("/uploadAndSaveImage")
        public ResponseEntity<? super ImageResponseDto> uploadAndSaveImage(
        @AuthenticationPrincipal String email,
        @ModelAttribute  ImageRequestDto dto) {    
    
    ResponseEntity<? super ImageResponseDto> response = fileservice.uploadAndSaveImage(dto.getFile(), dto, email);
    return response;
}
    @GetMapping(value = "{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getImage(
            @PathVariable("fileName") String fileName) {
        
        Resource resource = fileservice.getImage(fileName);
        return resource;
    }
}