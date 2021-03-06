package com.automacao.automacao.resource;

import com.automacao.automacao.dto.ProductDTO;
import com.automacao.automacao.dto.ProductResponseDTO;
import com.automacao.automacao.models.Product;
import com.automacao.automacao.repository.ProductRepository;
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
public class ProductResource {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product")
    @ApiOperation(value = "Lista todos os produtos")
    public List<Product> findAll(){
        return this.productRepository.findAll();
    }

    @GetMapping("/product/{code}")
    @ApiOperation(value = "Lista um produto")
    public Optional<Product> productUnique(@PathVariable(value="code")String code){
        return productRepository.findById(code);
    }

    @GetMapping("/maxCodeProduct")
    @ApiOperation(value = "Lista a ultima categoria")
    public Integer findLastProduct(){
        return this.productRepository.maxCodeProduct();
    }

    public Product insertProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PostMapping("/product")
    @ApiOperation(value = "Insere um produto")
    public ResponseEntity<ProductResponseDTO> insertProduct(@Valid @RequestBody ProductDTO productDTO){
        Product product = productRepository.save(productDTO.product());
        return new ResponseEntity<>(ProductResponseDTO.productResponseDTO(product), HttpStatus.CREATED);
    }

    @PostMapping("/productSaveAll")
    @ApiOperation(value= "Salva vários produtos")
    public List<Product> saveAll(@RequestBody List<Product> products){
        return productRepository.saveAll(products);
    }

    @DeleteMapping("/product")
    @ApiOperation(value = "Apaga um produto")
    public void deleteProduct(@RequestBody Product product){
        productRepository.delete(product);
    }

    @PutMapping("/product")
    @ApiOperation(value = "Atualiza um produto")
    public Product updateProduct(@RequestBody Product product){
        return productRepository.save(product);
    }
}
