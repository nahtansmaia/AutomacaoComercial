package com.automacao.automacao.resource;


import com.automacao.automacao.dto.CategoryDTO;
import com.automacao.automacao.dto.CategoryResponseDTO;
import com.automacao.automacao.models.Category;
import com.automacao.automacao.repository.CategoryRepository;
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
public class CategoryResource {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/category")
    @ApiOperation(value = "Lista todos as categorias")
    public List<Category> findAll(){
        return this.categoryRepository.findAll();
    }

    @GetMapping("/category/{code}")
    @ApiOperation(value = "Lista uma categoria")
    public Optional<Category> categoryUnique(@PathVariable(value="code")String code){
        return categoryRepository.findById(code);
    }
    @GetMapping("/maxCodeCategory")
    @ApiOperation(value = "Lista a ultima categoria")
    public Integer findLastCategory(){
        return this.categoryRepository.maxCodeCategory();
    }

    @PostMapping("/category")
    @ApiOperation(value = "Insere uma categoria")
    public ResponseEntity<CategoryResponseDTO> insertCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        Category category = categoryRepository.save(categoryDTO.category());
        return new ResponseEntity<>(CategoryResponseDTO.categoryResponseDTO(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/category")
    @ApiOperation(value = "Apaga uma categoria")
    public void deleteCategory(@RequestBody Category category){
        categoryRepository.delete(category);
    }

    @PutMapping("/category")
    @ApiOperation(value = "Atualiza uma categoria")
    public Category updateCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }
}
