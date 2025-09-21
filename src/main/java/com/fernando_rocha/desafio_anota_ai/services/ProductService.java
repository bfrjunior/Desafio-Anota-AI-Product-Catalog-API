package com.fernando_rocha.desafio_anota_ai.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fernando_rocha.desafio_anota_ai.domain.category.exceptions.CategoryNotFoundException;
import com.fernando_rocha.desafio_anota_ai.domain.category.product.Product;
import com.fernando_rocha.desafio_anota_ai.domain.category.product.ProductDTO;
import com.fernando_rocha.desafio_anota_ai.domain.category.product.exceptions.ProductNotFoundException;
import com.fernando_rocha.desafio_anota_ai.repositories.ProductRepository;
import com.fernando_rocha.desafio_anota_ai.services.aws.AwsSnsService;
import com.fernando_rocha.desafio_anota_ai.services.aws.MessageDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository repository;
    private final AwsSnsService snsService;

    public Product insert(ProductDTO productData) {
        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);

        this.repository.save(newProduct);

        this.snsService.publish(new MessageDTO(newProduct.toString()));

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

        this.snsService.publish(new MessageDTO(product.toString()));

        return product;
    }

    public void delete(String id) {
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.repository.delete(product);
        this.snsService.publish(new MessageDTO(product.deleteToString()));
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }
}
