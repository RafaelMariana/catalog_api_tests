package com.catalog.repositories;

import com.catalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest

public class ProductRepositoryTests {
    @Autowired

    private ProductRepository repository;

    @Test
    public void deleteShoulderObejctWhenIdExists(){
        long existingId = 1l;
        repository.deleteById(existingId);
        Optional<Product> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());

    }
    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("testando");
        product.setPrice(5000.00);
        product.setImgUrl("localhost/image");
       // product.setDate(Instant.parse("2024-10--25T21:00:000+03:00"));
        Product savedProduct = repository.save(product);
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo("Test Product");
        product.setDate(Instant.now());

    }

}
