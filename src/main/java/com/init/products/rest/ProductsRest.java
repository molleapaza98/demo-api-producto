package com.init.products.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.products.model.Product;
import com.init.products.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductsRest {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public ResponseEntity<List<Product>> getProduct() {
		List<Product> products = productRepository.findAll();
		return ResponseEntity.ok(products);
	}

	@RequestMapping(value = "{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
		Optional<Product> optionalproducts = productRepository.findById(productId);

		if (optionalproducts.isPresent()) {
			return ResponseEntity.ok(optionalproducts.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product newProduct = productRepository.save(product);
		return ResponseEntity.ok(newProduct);
	}
	
	@DeleteMapping(value = "{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
		productRepository.deleteById(productId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		Optional<Product> optionalProduct = productRepository.findById(product.getId());
		if (optionalProduct.isPresent()) {
			Product updateProduct = optionalProduct.get();
			updateProduct.setName(product.getName());
			productRepository.save(updateProduct);
			return ResponseEntity.ok(updateProduct);
		} else {
			return ResponseEntity.noContent().build();
		}
		
	}

}