package com.codingdojo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.models.Category;
import com.codingdojo.models.Product;
import com.codingdojo.services.ProductCatService;

@Controller
public class MainController {

	private final ProductCatService productCatService;
	
	public MainController( ProductCatService productCatService ) {
		this.productCatService = productCatService;
	}
	
	//Retrieving all products and categories
	@RequestMapping( value = "/", method = RequestMethod.GET )
	public String AllInfo( @ModelAttribute( "product" ) Product product,
						   @ModelAttribute( "category" ) Category category,	Model model ) {
	
		List<Product> productList = productCatService.getAllProducts();
		List<Category> categoryList = productCatService.getAllCategories();
		
		model.addAttribute( "productList", productList  );
		model.addAttribute( "categoryList", categoryList  );
		return "index.jsp";
	}
	
	
	//Render form to create new category
	@RequestMapping( value = "/categories/new", method = RequestMethod.GET )
	public String newCategory( @ModelAttribute( "category" ) Category category ) {
		return "newCategory.jsp";
	}
	
	//Create new category
	@RequestMapping( value = "/categories/new", method = RequestMethod.POST )
	public String createCategory( @ModelAttribute( "category" ) Category category, BindingResult result ) {
		if( result.hasErrors() ) {
			return "/categories/new";
		}
		productCatService.createCategory( category );
		return "redirect:/";
	}
	
	//Render form to create new Product
	@RequestMapping( value = "/products/new", method = RequestMethod.GET )
	public String newProduct( @ModelAttribute( "product" ) Product product ) {
		return "newProduct.jsp";
	}
		
	//Create new product
	@RequestMapping( value = "/products/new", method = RequestMethod.POST )
	public String createProduct( @ModelAttribute( "product" ) Product product, BindingResult result ) {
		if( result.hasErrors() ) {
			return "newProduct.jsp";
		}
		productCatService.createProduct( product );
		return "redirect:/";
	}
	
	//Show category
	@RequestMapping( value = "/categories/{id}", method = RequestMethod.GET )
	public String showCategory(@ModelAttribute( "categories_products" ) Category category, Product product, 
							   @PathVariable( "id" ) Long id, Model model ) {

		Category currentCat = productCatService.getCategoryById( id );
		List<Product> productList = productCatService.getProductsInCategory( category );
		model.addAttribute( "category", currentCat );
		model.addAttribute( "productList", productList );
		
		
		Product currentproduct = productCatService.getProductById( id );
		List<Product> otherProduc = productCatService.getProductsNotInCategory( currentCat );
		model.addAttribute( "product", currentproduct );
		model.addAttribute( "notInCategories", otherProduc );
		return "showCategory.jsp";
	}
	
//	//add products into categories
//	@RequestMapping( value = "/categories/new", method = RequestMethod.POST )
//	public String addProduct( @ModelAttribute( "product" ) Product product, @PathVariable( "id" ) Long id, Model model, BindingResult result ) {
//		if( result.hasErrors() ) {
//			return "showCategory.jsp";
//		}
//		Product currentproduct = productCatService.getProductById( id );
//		List<Category> addToCategoryList = productCatService.getCategoriesInProduct(  currentproduct );
//		model.addAttribute( "category", addToCategoryList );
//		return "redirect:/categories/{id}";
//	}
	
	//Show Product
	@RequestMapping( value = "/products/{id}", method = RequestMethod.GET )
	public String showProduct(@ModelAttribute( "categories_products" ) Category category, Product product, 
							   @PathVariable( "id" ) Long id, Model model ) {
		
		
		Product currentPro = productCatService.getProductById( id );
		List<Category> categoryList = productCatService.getCategoriesInProduct(  product );
		
		model.addAttribute( "product", currentPro );
		model.addAttribute( "categorytList", categoryList );
		
		Category currentCateg = productCatService.getCategoryById( id );
		List<Category> otherCateg = productCatService.getCategoriesNotInProduct( currentPro );
		model.addAttribute( "category", currentCateg );
		model.addAttribute( "notInCategories", otherCateg );
		return "showProduct.jsp";
	}
	
	//add categories into product
	
	
	
	
	
}
