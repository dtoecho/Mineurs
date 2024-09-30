package com.newlecmineursprj.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSubImg {
    private Long productId;
    private String path;
    private Long id;

    public static List<ProductSubImg> updateSubImgs(List<String> storageSubImgName, List<ProductSubImg> foundAll) {
        return IntStream.range(0, storageSubImgName.size())
                .mapToObj(i -> ProductSubImg.builder()
                        .id(foundAll.get(i).getId())
                        .path(storageSubImgName.get(i))
                        .productId(foundAll.get(i).getProductId())
                        .build())
                .toList();
    }

    public static List<ProductSubImg> saveSubImgs(List<String> storageSubImgName, Product newProduct) {
        return storageSubImgName.stream()
                .map(imgName -> ProductSubImg.builder()
                        .path(imgName)
                        .productId(newProduct.getId())
                        .build())
                .toList();
    }
    public static List<String> getCurrentImgs(List<ProductSubImg> foundProducts) {
        return foundProducts.stream()
                .map(ProductSubImg::getPath)
                .toList();
    }
}
