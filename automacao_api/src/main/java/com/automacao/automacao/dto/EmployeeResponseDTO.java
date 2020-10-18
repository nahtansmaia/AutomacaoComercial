package com.automacao.automacao.dto;

import com.automacao.automacao.models.Employee;

public class EmployeeResponseDTO {
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

    public static EmployeeResponseDTO employeeResponseDTO(Employee employee){
        return new EmployeeResponseDTO(employee.getCode(), employee.getName());
    }

    public EmployeeResponseDTO(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
