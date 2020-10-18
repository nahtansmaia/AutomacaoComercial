package com.automacao.automacao.resource;

import com.automacao.automacao.dto.CategoryDTO;
import com.automacao.automacao.dto.CategoryResponseDTO;
import com.automacao.automacao.dto.EmployeeDTO;
import com.automacao.automacao.dto.EmployeeResponseDTO;
import com.automacao.automacao.models.Category;
import com.automacao.automacao.models.Employee;
import com.automacao.automacao.repository.EmployeeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(value = "Automação")
@CrossOrigin(origins = "*")
public class EmployeeResource {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    @ApiOperation(value = "Lista todos os clientes")
    public List<Employee> findAll(){
        return this.employeeRepository.findAll();
    }

    @GetMapping("/employee/{code}")
    @ApiOperation(value = "Lista um cliente")
    public Optional<Employee> employeeUnique(@PathVariable(value="code")String code){
        return employeeRepository.findById(code);
    }
    @GetMapping("/maxCodeEmployee")
    @ApiOperation(value = "Lista a ultima pessoa")
    public Integer findLastEmployee(){
        return this.employeeRepository.maxCodeEmployee();
    }

    @PostMapping("/employee")
    @ApiOperation(value = "Insere um cliente")
    public ResponseEntity<EmployeeResponseDTO> insertEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        Employee employee = employeeRepository.save(employeeDTO.employee());
        return new ResponseEntity<>(EmployeeResponseDTO.employeeResponseDTO(employee), HttpStatus.CREATED);
    }

    @DeleteMapping("/employee")
    @ApiOperation(value = "Apaga um cliente")
    public void deleteEmployee(@RequestBody Employee employee){
        employeeRepository.delete(employee);
    }

    @PutMapping("/employee")
    @ApiOperation(value = "Atualiza um cliente")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

}
