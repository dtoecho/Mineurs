package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.ProductSubImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProductSubImgService {
    List<ProductSubImg> getListByProductId(long productId);
}
