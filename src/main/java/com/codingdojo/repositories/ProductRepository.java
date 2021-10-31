package com.codingdojo.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.codingdojo.models.Category;
import com.codingdojo.models.Product;

public interface ProductRepository extends Repository<Product, Long>{
	List<Product> findAll();
	
	// Retrieves a list of all products for a particular category
	List<Category> findAllByProducts(Product product);
	
	// Retrieves a list of any products a particular category does not belong to.
	List<Product> findByCategoriesNotContains(Category category);
	
	Product save( Product product );
}
