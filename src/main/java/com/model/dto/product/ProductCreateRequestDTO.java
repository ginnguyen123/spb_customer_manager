package com.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreateRequestDTO {

    private String title;
    private String price;
    private String description;
    private Long unitId;
    private Long categoryId;
    private MultipartFile avatar;
}
