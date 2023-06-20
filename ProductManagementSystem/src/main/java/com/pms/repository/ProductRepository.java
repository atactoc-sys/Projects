package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
