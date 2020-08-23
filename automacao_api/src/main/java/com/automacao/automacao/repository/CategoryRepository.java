package com.automacao.automacao.repository;

import com.automacao.automacao.models.Category;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    @Override
    Optional<Category> findById(String s);

    @Aggregation("{ '$group': { '_id': null, 'maxCode': { $max: '$code' } } }")
    Integer maxCodeCategory();
}
