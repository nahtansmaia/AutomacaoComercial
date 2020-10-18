package com.automacao.automacao.dto;

import com.automacao.automacao.models.Employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeDTO {
    @NotNull(message = "Please enter the code")
    private Integer code;
    @NotBlank(message = "Please enter the name")
    @Size(min = 1, max = 50, message = "size must be between 1 and 50")
    private String name;
    @NotBlank(message = "Please enter the type")
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Employee employee(){
        return new Employee(code, name, type);
    }
}
