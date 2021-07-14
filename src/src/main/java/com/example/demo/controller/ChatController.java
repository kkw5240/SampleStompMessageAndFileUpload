package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
public class ChatController {

    @Value("${file-path}")
    private String filePath;

    @MessageMapping("/hello")
    @SendTo("/topic/roomId")
    public Message broadCast(Message message){
        return message;
    }

    @PostMapping("/file")
    public Message handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        //겹쳐지지 않는 파일명을 위한 유니크한 값 생성
        UUID uid = UUID.randomUUID();

        String originalFileName = file.getOriginalFilename();
        String fileType = file.getContentType().split("/")[1];

        //원본파일 이름과 UUID 결합
        String savedName = uid + "_" + originalFileName+"."+ fileType;

        //저장할 파일준비
        File target = new File(filePath, savedName);

        //파일을 저장
        FileCopyUtils.copy(file.getBytes(), target);

        String formatName = originalFileName.substring(originalFileName.lastIndexOf(".")+1);

        return new Message("savedName", "formatName", new Date());
    }
}
