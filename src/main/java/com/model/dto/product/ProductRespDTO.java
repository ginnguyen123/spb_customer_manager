package com.model.dto.product;

import com.model.dto.productAvatar.ProductAvatarDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRespDTO {

    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private String unit;
    private String categoryTitle;
    private ProductAvatarDTO avatar;


}
