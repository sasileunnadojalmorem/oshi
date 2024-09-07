    package com.oshi.ohsi_back.controller;

import org.springframework.core.io.Resource;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.annotation.AuthenticationPrincipal;
    import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import com.oshi.ohsi_back.dto.request.Image.ImageRequestDto;
    import com.oshi.ohsi_back.dto.response.image.ImageResponseDto;
    import com.oshi.ohsi_back.service.Fileservice;

    import lombok.RequiredArgsConstructor;

    @RestController
    @RequestMapping("/api/user/file")
    @RequiredArgsConstructor
public class Filecontrollor {

    private final Fileservice fileservice;


    @GetMapping(value = "{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getImage(
            @PathVariable("fileName") String fileName) {
        
        Resource resource = fileservice.getImage(fileName);
        return resource;
    }
}