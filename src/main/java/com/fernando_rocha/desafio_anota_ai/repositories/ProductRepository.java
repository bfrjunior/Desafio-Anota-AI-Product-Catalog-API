package com.fernando_rocha.desafio_anota_ai.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fernando_rocha.desafio_anota_ai.domain.category.product.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
