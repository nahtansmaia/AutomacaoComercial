package com.automacao.automacao.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
public class Sale {

    @Id
    private String id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate IssueDate;
    private Integer code;
    private Employee employee;
    private Double salePrice;
    private List<ProductSale> productSale;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        IssueDate = issueDate;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getSalePrice() {
        return productSale.stream().mapToDouble(ProductSale::getTotalItem).sum();
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public List<ProductSale> getProductSale() {
        return productSale;
    }

    public void setProductSale(List<ProductSale> productSale) {
        this.productSale = productSale;
    }
}
