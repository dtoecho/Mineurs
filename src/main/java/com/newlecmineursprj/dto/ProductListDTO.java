package com.newlecmineursprj.dto;

import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductListDTO {
    private long id;
    private String name;
    @NumberFormat(pattern = "###,###")
    private int price;
    @NumberFormat(pattern = "###,###")
    private int discountPrice;
    private List<Color> colors;
    private List<Size> sizes;
    private boolean isDeliveryToday;
    private Boolean isSold;
    private LocalDate regDate;
    private Boolean isDisplayed;
    private String mainImgPath;
    private Boolean currentUserLiked;
    private Long likeCount;
}
