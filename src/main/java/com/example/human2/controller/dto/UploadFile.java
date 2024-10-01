package com.example.human2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
public class UploadFile implements Serializable {
    private String fileName;
    private String uuid;
    private String folderPath;

    public String getImageURL(){
        return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, StandardCharsets.UTF_8);
    }

}
