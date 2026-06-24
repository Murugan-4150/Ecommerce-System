package com.ecommerce_system.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestProductDto {

    private String name;

    private Double price;

    private Integer stock;

}
