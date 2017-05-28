package com.calogardev.pizzarella.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.calogardev.pizzarella.model.Product;

public interface ProductDao extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = :name AND p.status = 'ACTIVE'")
    public Product findByName(@Param("name") String name);

    @Override
    @Query("SELECT p FROM Product p WHERE p.status = 'ACTIVE'")
    public List<Product> findAll();

    @Query("SELECT p FROM Product p WHERE p.isIngredient = false AND p.status = 'ACTIVE'")
    public List<Product> findAllSellable();

    @Query("SELECT p FROM Product p WHERE p.isIngredient = true AND p.status = 'ACTIVE'")
    public List<Product> findAllIngredients();

    @Query("SELECT p FROM Product p WHERE p.isIngredient = false AND p.family = :family AND p.status = 'ACTIVE'")
    public List<Product> findAllSellableFromFamily(@Param("family") String family);

    @Query("SELECT p FROM Product p WHERE p.isIngredient = true AND p.family = :family AND p.status = 'ACTIVE'")
    public List<Product> findAllIngredientsFromFamily(@Param("family") String family);

    @Query("SELECT p FROM Product p WHERE p.id != :id")
    public List<Product> findAllExceptOne(@Param("id") Long id);
}
