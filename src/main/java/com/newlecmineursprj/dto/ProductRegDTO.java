package com.newlecmineursprj.dto;

import com.newlecmineursprj.entity.ProductItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRegDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Integer price;
    @NotBlank
    private String description;
    private MultipartFile mainImgFile;
    private List<MultipartFile> subImgFiles;
    @NotNull
    private Long categoryId;
    private Boolean isDisplayed;
    private Boolean isSold;
    private String code;
    private Boolean isDeliveryToday;
    private Double discountRate;
    private List<ProductItem> productItems;


}
