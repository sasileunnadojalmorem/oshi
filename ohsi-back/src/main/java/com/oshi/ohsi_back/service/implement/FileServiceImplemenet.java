package com.oshi.ohsi_back.service.implement;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oshi.ohsi_back.service.Fileservice;
@Service
public class FileServiceImplemenet implements Fileservice{
    @Value("${file.path}")  
    private String filePath ;
    @Value("${file.url}")  
    private String fileUrl ;

    @Override
    public String upload(MultipartFile file) {
       
        if(file.isEmpty()) return null;
        String orignalFileName = file.getOriginalFilename();
        String extension = orignalFileName.substring(orignalFileName.lastIndexOf("."));
        String UUid = UUID.randomUUID().toString();
        String saveFileName = UUid + extension;
        String saveFilePath = filePath + saveFileName;

        try {
            file.transferTo(new File(saveFilePath));
        } catch (Exception e) {
            e.printStackTrace();
            return null;    
        }

        String url = fileUrl + saveFileName;
        return url;

    }

    @Override
    public Resource getImage(String fileName) {
        Resource resource = null;
        try {
            resource = new UrlResource("file:"+filePath + fileName);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return resource;
    }

    

}
    