package com.newlecmineursprj.mapper;

import com.newlecmineursprj.entity.ProductSubImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class SubImgMapper {
    public static ProductSubImg toSubImg(MultipartFile imgFile, long productId){
        return ProductSubImg.builder()
                .path(imgFile.getOriginalFilename())
                .productId(productId)
                .build();
    }
}