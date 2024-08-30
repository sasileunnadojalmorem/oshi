package com.oshi.ohsi_back.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface Fileservice {

    String upload(MultipartFile file);

    Resource getImage(String fileName);

    

    
}
