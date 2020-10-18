package com.automacao.automacao.dto;

import com.automacao.automacao.models.Product;

public class ProductResponseDTO {
    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ProductResponseDTO productResponseDTO(Product product){
        return new ProductResponseDTO(product.getCode(), product.getName());
    }

    public ProductResponseDTO(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
