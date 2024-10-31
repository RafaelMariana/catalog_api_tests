package com.catalog.repositories;

import com.catalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
    @Test
    @DisplayName("")
    public void updateShowIdChangeAndPersistDataWhenIdExist(){
        //preparar os dados
        Product product = new Product(1L,"Phone","Smartphone", 1200.00,"imgProduto",Instant.now());
        //executar a ação
        product.setName("Update Phone");
        product.setPrice(15000.00);
        product = repository.save(product);
        //verificar se ação ocorreu como o esperado
        Assertions.assertEquals("Update Phone",product.getName());
    }
    @Test
    public void findyIdShouldReturnNonEmptyOptionalWhenExists(){
        //preparar os dados
        long existingId = 1L;
        //executar a ação
        Optional<Product> result = repository.findById(existingId);
        //certificar se deu certo
        //verificar se retornou um objeto
        Assertions.assertTrue(result.isPresent());
    }

}
