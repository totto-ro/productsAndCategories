package com.codingdojo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.codingdojo.models.Category;
import com.codingdojo.models.Product;

public interface CategoryRepository extends Repository<Category, Long>{
	List<Category> findAll();
	
	// Retrieves a list of all categories for a particular product
	List<Category> findAllByProducts(Product product);
	
	// Retrieves a list of any categories a particular product does not belong to.
	List<Category> findByProductsNotContains(Product product);
	
	Category save( Category category );
	
	Optional<Category> findById( Long id );
}