package com.fernando_rocha.desafio_anota_ai.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fernando_rocha.desafio_anota_ai.domain.category.exceptions.CategoryNotFoundException;
import com.fernando_rocha.desafio_anota_ai.domain.category.product.Product;
import com.fernando_rocha.desafio_anota_ai.domain.category.product.ProductDTO;
import com.fernando_rocha.desafio_anota_ai.domain.category.product.exceptions.ProductNotFoundException;
import com.fernando_rocha.desafio_anota_ai.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository repository;

    public Product insert(ProductDTO productData) {
        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);

        this.repository.save(newProduct);

        return newProduct;
    }

    public Product update(String id, ProductDTO productData) {
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        if (!productData.title().isEmpty())
            product.setTitle(productData.title());
        if (!productData.description().isEmpty())
            product.setDescription(productData.description());
        if (!(productData.price() == null))
            product.setPrice(productData.price());
        if (!(productData.categoryId() == null))
            product.setCategory(productData.categoryId());

        this.repository.save(product);

        return product;
    }

    public void delete(String id) {
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.repository.delete(product);
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

}
