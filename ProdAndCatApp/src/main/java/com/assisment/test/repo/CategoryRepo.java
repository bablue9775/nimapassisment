package com.assisment.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assisment.test.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
