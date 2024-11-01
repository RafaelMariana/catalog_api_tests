package com.catalog.repositories;

import com.catalog.entities.Category;
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
public class CategoryRepositoryTests {
    @Autowired

    private CategoryRepository repository;

    @Test
    public void deleteShoulderObejctWhenIdExists() {
        long existingId = 1l;
        repository.deleteById(existingId);
        Optional<Category> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());

    }

    @Test
    public void testSaveCategory() {
        Category category = new Category();
        category.setName("Test Categoria");
        category.setCreatedAt(Instant.now());
        category.setUpdatedAt(Instant.now());

        Category savedCategoria = repository.save(category);
        assertThat(savedCategoria).isNotNull();
        assertThat(savedCategoria.getName()).isEqualTo("Test Categoria");


    }

    @Test
    @DisplayName("")
    public void updateShowIdChangeAndPersistDataWhenIdExist() {
        //preparar os dados
        Category category = new Category("Livros", 1L, Instant.now(), Instant.now());
        //executar a ação
        category.setName("Update Categoria");
        category = repository.save(category);
        //verificar se ação ocorreu como o esperado
        Assertions.assertEquals("Update Categoria", category.getName());

    }

    @Test
    public void findyIdShouldReturnNonEmptyOptionalWhenExists() {
        //preparar os dados
        long existingId = 1L;
        //executar a ação
        Optional<Category> result = repository.findById(existingId);
        //certificar se deu certo
        //verificar se retornou um objeto
        Assertions.assertTrue(result.isPresent());
    }

}