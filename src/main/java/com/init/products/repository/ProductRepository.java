package com.init.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.products.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	

}
