package com.codingdojo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String createCategory( @Valid @ModelAttribute( "category" ) Category category, BindingResult result ) {
		if( result.hasErrors() ) {
			return "newCategory.jsp";
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
	public String createProduct( @Valid @ModelAttribute( "product" ) Product product, BindingResult result ) {
		if( result.hasErrors() ) {
			return "newProduct.jsp";
		}
		productCatService.createProduct( product );
		return "redirect:/";
	}
	
	//Show category
	@RequestMapping( value = "/categories/{id}", method = RequestMethod.GET )
	public String showCategory(@ModelAttribute( "category" ) Category category, 
							   @PathVariable( "id" ) Long id, Model model ) {
		//get product in category:
		Category currentCat = productCatService.getCategoryById( id );
		List<Product> productList = productCatService.getProductsInCategory( category );
		model.addAttribute( "category", currentCat );
		model.addAttribute( "productList", productList );
		
		
		//get products that are not in category (in currentCat):
		Product currentproduct = productCatService.getProductById( id );
		List<Product> otherProduc = productCatService.getProductsNotInCategory( currentCat );
		model.addAttribute( "product", currentproduct );
		model.addAttribute( "notInCategories", otherProduc);
				
		return "showCategory.jsp";
	}
	
	
	///////////////add products into categories/////////////////
	@RequestMapping(  value = "/categories/{id}", method = RequestMethod.PUT )
	public String addProductToCat(  @PathVariable("id") Long id, @RequestParam (value="id") Long pID, RedirectAttributes redirectAttributes ) {
		
		
		//get specific product
		Product produ = productCatService.getProductById( pID );
		
		//Get category:
		Category currentCateg = productCatService.getCategoryById( id );
		
		if ( currentCateg == null ) {
			redirectAttributes.addFlashAttribute( "message", "You forgot to add something" );
		}
		
		//Get products inside currentCateg (view of products in this category):
		//List of products currently in the category:
		List<Product> currentProductList = currentCateg.getProducts();
		//List<Product> productList = productCatService.getProductsInCategory( currentCateg );
		
		
		//add product to this product list
		currentProductList.add( produ );
		
		//set productList/save
		currentCateg.setProducts( currentProductList );
		System.out.println( currentProductList );
		
		//save
		productCatService.saveProduct( produ );
		System.out.println( produ );
		System.out.println( "heeeeerrrrrrrreeeeee" );
		return "redirect:/categories/"+ id;
	}
	
	
	//Show Product
	@RequestMapping( value = "/products/{id}", method = RequestMethod.GET )
	public String showProduct(@ModelAttribute( "product" ) Product product, 
							   @PathVariable( "id" ) Long id, Model model ) {
		
		
		Product currentPro = productCatService.getProductById( id );
		List<Category> categoryList = productCatService.getCategoriesInProduct(  product );
		
		model.addAttribute( "product", currentPro );
		model.addAttribute( "categorytList", categoryList );
		
		Category currentCateg = productCatService.getCategoryById( id );
		List<Category> otherCateg = productCatService.getCategoriesNotInProduct( currentPro );
		model.addAttribute( "category", currentCateg );
		model.addAttribute( "notInProducts", otherCateg );
		return "showProduct.jsp";
	}
	
	///////////////add categories into products/////////////////
	@RequestMapping(  value = "/products/{id}", method = RequestMethod.PUT )
	public String addCategoryToProduct(  @PathVariable("id") Long id, 
										 @RequestParam (value="id") Long catID, 
										 RedirectAttributes redirectAttributes ) {
		
		
		
		
		//get specific category
		Category category = productCatService.getCategoryById( catID );
		
		//Get product:
		Product currentProduct = productCatService.getProductById( id );
		
		if ( currentProduct == null ) {
			redirectAttributes.addFlashAttribute( "message", "You forgot to add something" );
		}
		
		//Get products inside currentCateg (view of products in this category):
		//List of products currently in the category:
		List<Category> currentCatList = currentProduct.getCategories();
		
		
		//add product to this product list
		currentCatList.add( category );
		
		//set productList/save
		currentProduct.setCategories( currentCatList );
		System.out.println( currentCatList );
		
		//save
		productCatService.saveCategory( category );
		System.out.println( category );
		System.out.println( "heeeeerrrrrrrreeeeee" );
		return String.format("redirect:/products/%d", id);
	}
	
	
	
	
	
}
