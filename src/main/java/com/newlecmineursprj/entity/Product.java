package com.newlecmineursprj.entity;

import java.sql.Date;

import com.newlecmineursprj.dto.ProductRegDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    @NotBlank
    private String description;

    private String mainImgPath;
    private Boolean displayed;
    private Boolean sold;
    private String code;
    private Boolean deliveryToday;
    private Double discountRate;

    @NotNull
    private Long categoryId;
    private Date regDate;

    public static void saveNewImg(String mainImgPath, Product newProduct) {
        newProduct.setMainImgPath(mainImgPath);
    }

    public static Product createProduct(ProductRegDTO productRegDTO) {
        return Product.builder()
                .name(productRegDTO.getName())
                .price(productRegDTO.getPrice())
                .description(productRegDTO.getDescription())
                .mainImgPath(productRegDTO.getMainImgFile().getOriginalFilename())
                .displayed(productRegDTO.getIsDisplayed())
                .sold(productRegDTO.getIsSold())
                .code(productRegDTO.getCode())
                .deliveryToday(productRegDTO.getIsDeliveryToday())
                .discountRate(productRegDTO.getDiscountRate())
                .categoryId(productRegDTO.getCategoryId())
                .build();
    }

    public String getCurrentImg(String mainImgPath) {
        return mainImgPath;
    }
}
