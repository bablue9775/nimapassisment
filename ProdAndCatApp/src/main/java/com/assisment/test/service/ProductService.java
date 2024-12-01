package com.assisment.test.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assisment.test.dto.ProductDto;
import com.assisment.test.entities.Category;
import com.assisment.test.entities.Product;
import com.assisment.test.exception.ResourceNotFoundException;
import com.assisment.test.repo.CategoryRepo;
import com.assisment.test.repo.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private ModelMapper modelMapper;

	public ProductDto createProduct(ProductDto productDto, Integer categoryId) {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		Product product = modelMapper.map(productDto, Product.class);
		Product product2 = this.productRepo.save(product);

		product2.setCategory(category);
		return this.modelMapper.map(product2, ProductDto.class);

	}

	public ProductDto updateProduct(Integer productId, ProductDto productDto) {

		Product product = this.productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product", "productId", productId));

		product.setProductName(productDto.getProductName());
		product.setPrice(productDto.getPrice());

		return this.modelMapper.map(product, ProductDto.class);
	}

	public ProductDto getProductById(Integer productId) {

		Product product = this.productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product", "productId", productId));

		return this.modelMapper.map(product, ProductDto.class);

	}

	public List<ProductDto> getProducts(Integer pageNumber, Integer pageSize) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<Product> pagePost = this.productRepo.findAll(pageable);

		List<Product> product = pagePost.getContent();

		return product.stream().map((p) -> this.modelMapper.map(p, ProductDto.class)).collect(Collectors.toList());
	}

	public String deleteProductById(Integer productId) {
		Product product = this.productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product", "productId", productId));

		this.productRepo.delete(product);
		return "product is deleted";
	}

}
