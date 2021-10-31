package com.codingdojo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.models.Category;
import com.codingdojo.models.Product;
import com.codingdojo.repositories.CategoryRepository;
import com.codingdojo.repositories.ProductRepository;

@Service
public class ProductCatService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	
	public ProductCatService( ProductRepository productRepository, CategoryRepository categoryRepository ) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		
	}
	
	//Retrieves all products
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	//Retrieves all categories
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	//Create product
	public Product createProduct( Product product ) {
		return productRepository.save( product );
	}
	
	//Create product
		public Category createCategory( Category category ) {
			return categoryRepository.save( category );
		}
	
	//Retrieve product not paired with category	
	public List<Product> getProductsNotInCategory(Category category) {
		return productRepository.findByCategoriesNotContains( category );
	}	
	
	//Retrieve category with product not associated with (not paired with)
	public List<Category> getCategoriesNotInProduct( Product product ) {
		return categoryRepository.findByProductsNotContains( product );
	}
	
	//Retrieve product not paired with category	
		public List<Product> getProductsInCategory(Category category) {
			return productRepository.findAllByCategories( category );
		}	
		
	//Retrieve category with product not associated with (not paired with)
	public List<Category> getCategoriesInProduct( Product product ) {
		return categoryRepository.findAllByProducts( product );
	}
	
	
	//Retrieve product by Id
	public Product getProductById( Long id ) {
		Optional<Product> optionalProduct = productRepository.findById( id );
		if( optionalProduct.isPresent() ) {
			return optionalProduct.get();
		}
		else {
			return null;
		}
	}
	
	//Retrieve product by Id
		public Category getCategoryById( Long id ) {
			Optional<Category> optionalCategory = categoryRepository.findById( id );
			if( optionalCategory.isPresent() ) {
				return optionalCategory.get();
			}
			else {
				return null;
			}
		}
	
	
}
