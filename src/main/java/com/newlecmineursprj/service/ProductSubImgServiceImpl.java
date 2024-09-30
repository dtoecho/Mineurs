package com.newlecmineursprj.service;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.ProductSubImg;
import com.newlecmineursprj.repository.ProductSubImgRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductSubImgServiceImpl implements ProductSubImgService {
    private final ProductSubImgRepository repository;
    public List<ProductSubImg> getListByProductId(long productId) {
        return repository.findAll(productId);
    }
}