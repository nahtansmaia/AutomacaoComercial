package com.automacao.automacao.repository;

import com.automacao.automacao.models.Product;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Override
    Optional<Product> findById(String s);

    @Aggregation("{ '$group': { '_id': null, 'maxCode': { $max: '$code' } } }")
    Integer maxCodeProduct();
}
