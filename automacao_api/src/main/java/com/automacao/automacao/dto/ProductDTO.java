package com.automacao.automacao.dto;

import com.automacao.automacao.models.Category;
import com.automacao.automacao.models.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDTO {
    @NotNull(message = "Please enter the code")
    private Integer code;
    @NotBlank(message = "Please enter the name")
    @Size(min = 1, max = 50, message = "size must be between 1 and 50")
    private String name;
    @NotNull(message = "Please enter the value")
    @Min(0)
    private Double value;
    @NotNull(message = "Please enter the stock")
    @Min(0)
    private Double stock;
    private Category category;

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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product product(){
        return new Product(code, name, value, stock, category);
    }
}
