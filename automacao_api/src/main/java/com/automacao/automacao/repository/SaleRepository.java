package com.automacao.automacao.repository;

import com.automacao.automacao.models.Sale;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {

    @Override
    Optional<Sale> findById(String s);

    @Aggregation("{ '$group': { '_id': null, 'maxCode': { $max: '$code' } } }")
    Integer maxCodeSale();
}
