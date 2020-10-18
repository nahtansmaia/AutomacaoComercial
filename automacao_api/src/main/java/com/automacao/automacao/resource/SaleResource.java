package com.automacao.automacao.resource;

import com.automacao.automacao.models.Sale;
import com.automacao.automacao.repository.SaleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(value = "Automação")
@CrossOrigin(origins = "*")
public class SaleResource {
    @Autowired
    SaleRepository saleRepository;

    @GetMapping("/sale")
    @ApiOperation(value = "Lista todas as vendas")
    public List<Sale> findAll(){
        return this.saleRepository.findAll();
    }

    @GetMapping("/maxCodeSale")
    @ApiOperation(value = "Lista a ultima venda")
    public Integer findLastSale(){
        return this.saleRepository.maxCodeSale();
    }

    @GetMapping("/sale/{code}")
    @ApiOperation(value = "Lista uma venda ")
    public Optional<Sale> saleUnique(@PathVariable(value="code")String code){
        return saleRepository.findById(code);
    }

    @PostMapping("/sale")
    @ApiOperation(value = "Insere uma venda")
    public Sale insertSale(@RequestBody Sale sale){
        return saleRepository.save(sale);

    }

    @DeleteMapping("/sale")
    @ApiOperation(value = "Apaga uma venda")
    public void deleteSale(@RequestBody Sale sale){
        saleRepository.delete(sale);
    }

    @PutMapping("/sale")
    @ApiOperation(value = "Atualiza uma venda")
    public Sale updateSale(@RequestBody Sale sale){
        return saleRepository.save(sale);
    }
}
