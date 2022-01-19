package com.api.spring_restapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {
    private int id;
    private String name;
    private int price;
    private String description;
    private String detail;
    private String thumbnail;
    private String category_name;
}
