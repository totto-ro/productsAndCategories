package com.codingdojo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
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
	
	@Query( value = "SELECT products.name, categories.name"+
					"FROM products JOIN categories_products ON products.id = categories_products.product_id"
				  				+ "JOIN categories ON categories.id = categories_products.category_id;", nativeQuery = true )
	List<Object[]> selectProductsAndAuthors();
	
	List<Category> findByName( String Name );
	
	//Category thisCategory = findCategoryById(categoryId);
	
	//thisCategory.getProducts().add( thisProduct );
	
	//categoryRepository.save(thisCategory);
	
	
	
	
}