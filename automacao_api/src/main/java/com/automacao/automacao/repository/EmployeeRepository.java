package com.automacao.automacao.repository;

import com.automacao.automacao.models.Employee;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Override
    Optional<Employee> findById(String s);

    @Aggregation("{ '$group': { '_id': null, 'maxCode': { $max: '$code' } } }")
    Integer maxCodeEmployee();
}
