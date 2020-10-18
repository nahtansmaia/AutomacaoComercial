package com.automacao.automacao.dto;

import com.automacao.automacao.models.Category;

public class CategoryResponseDTO {
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

    public static CategoryResponseDTO categoryResponseDTO(Category category) {
        return new CategoryResponseDTO(category.getCode(), category.getName());
    }

    public CategoryResponseDTO(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
