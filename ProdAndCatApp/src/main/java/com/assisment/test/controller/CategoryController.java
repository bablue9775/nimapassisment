package com.assisment.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assisment.test.dto.CategoryDto;
import com.assisment.test.response.ApiResponse;
import com.assisment.test.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto category = this.categoryService.createCategory(categoryDto);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(category);
		apiResponse.setMessage("category is created");
		apiResponse.setStatus(true);
		apiResponse.setError(false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable	Integer categoryId,@RequestBody CategoryDto categoryDto) {
		
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryId, categoryDto);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(updateCategory);
		apiResponse.setMessage("category is updated");
		apiResponse.setStatus(true);
		apiResponse.setError(false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	@GetMapping("/get/{categoryId}")
	public ResponseEntity<ApiResponse> getByIdCategory(@PathVariable Integer categoryId) {
		
		CategoryDto categoryById = this.categoryService.getCategoryById(categoryId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(categoryById);
		apiResponse.setMessage("single category data is fetch");
		apiResponse.setStatus(true);
		apiResponse.setError(false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<ApiResponse> getAllCategory() {
		
		List<CategoryDto> categories = this.categoryService.getCategories();
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(categories);
		apiResponse.setMessage("all category data is fetch");
		apiResponse.setStatus(true);
		apiResponse.setError(false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		
		String deleteCategoryById = this.categoryService.deleteCategoryById(categoryId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(deleteCategoryById);
		apiResponse.setMessage("category data is deleted");
		apiResponse.setStatus(true);
		apiResponse.setError(false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	

}
