package com.assisment.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assisment.test.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
