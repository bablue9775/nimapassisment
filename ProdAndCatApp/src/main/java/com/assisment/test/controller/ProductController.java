package com.assisment.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assisment.test.dto.ProductDto;
import com.assisment.test.response.ApiResponse;
import com.assisment.test.service.ProductService;

@RestController
@RequestMapping("api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/category/{categoryId}/product")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto,
			@PathVariable Integer categoryId) {
		ProductDto product = this.productService.createProduct(productDto, categoryId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(product);
		apiResponse.setMessage("product created succesfully!!!");
		apiResponse.setStatus(true);
		apiResponse.setError(false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
	}

	@GetMapping("/update/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductDto productDto,
			@PathVariable Integer productId) {
		ProductDto updateProduct = this.productService.updateProduct(productId, productDto);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(updateProduct);
		apiResponse.setMessage("product update succesfully!!!");
		apiResponse.setStatus(true);
		apiResponse.setError(false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/get/{productId}")
	public ResponseEntity<ApiResponse> getProduct(@PathVariable Integer productId) {

		ProductDto productById = this.productService.getProductById(productId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(productById);
		apiResponse.setMessage("product get succesfully!!!");
		apiResponse.setStatus(true);
		apiResponse.setError(false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/products")
	public ResponseEntity<ApiResponse> getProducts(@RequestParam(value = "pageNumber") Integer pageNumber,
			@RequestParam(value = "pageSize") Integer pageSize) {

		List<ProductDto> products = this.productService.getProducts(pageNumber, pageSize);

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(products);
		apiResponse.setMessage("product get succesfully!!!");
		apiResponse.setStatus(true);
		apiResponse.setError(false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<ApiResponse> deleteProducts(@PathVariable Integer productId) {

		String deleteProductById = this.productService.deleteProductById(productId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(deleteProductById);
		apiResponse.setMessage("product delete succesfully!!!");
		apiResponse.setStatus(true);
		apiResponse.setError(false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

}
