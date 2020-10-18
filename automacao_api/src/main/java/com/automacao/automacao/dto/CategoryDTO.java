package com.automacao.automacao.dto;

import com.automacao.automacao.models.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDTO {
    @NotNull(message = "Please enter the code")
    private Integer code;
    @NotBlank(message = "Please enter the name")
    @Size(min = 1, max = 50, message = "size must be between 1 and 50")
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

    public Category category(){
        return new Category(code, name);
    }
}
