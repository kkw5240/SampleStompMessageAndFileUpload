package com.example.demo.service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileService {
    public void storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        /*try{
            if(fileName.contains("..")){
                throw new Exception("invalid path : "+fileName );
            }

            UploadFile uploadFile = new UploadFile(projectId, uploader, fileName,
                    file.getContentType(), file.getBytes());

            uploadFileRepository.save(uploadFile);

        } catch (IOException | Exception ex){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }*/
    }
}
