package com.newlecmineursprj.mapper;

import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.dto.ProductRegDTO;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {
    public static ProductListDTO toDto(ProductView productView) {

        return ProductListDTO.builder()
                .id(productView.getId())
                .name(productView.getName())
                .price(productView.getPrice())
                .discountPrice((int) (productView.getDiscountRate() * productView.getPrice()))
                .colors(productView.getColors())
                .regDate(productView.getRegDate())
                .sizes(productView.getSizes())
                .isDeliveryToday(productView.isDeliveryToday())
                .isSold(productView.getIsSold())
                .isDisplayed(productView.getIsDisplayed())
                .mainImgPath(productView.getMainImgPath())
                .currentUserLiked(productView.getCurrentUserLiked())
                .build();
    }

//    public static Product toProduct(ProductRegDTO productRegDTO) {
//        return Product.builder()
//                .id(productRegDTO.getId())
//                .name(productRegDTO.getName())
//                .price(productRegDTO.getPrice())
//                .mainImgPath(productRegDTO.getMainImg().getOriginalFilename())
//                .description(productRegDTO.getDescription())
//                .displayed(productRegDTO.getIsDisplayed() == 1)
//                .sold(productRegDTO.getIsSold()==1)
//                .deliveryToday(productRegDTO.getIsDeliveryToday()==1)
//                .code(productRegDTO.getCode())
//                .discountRate(productRegDTO.getDiscountRate())
//                .categoryId(productRegDTO.getCategoryId())
//                .build();
//    }

}