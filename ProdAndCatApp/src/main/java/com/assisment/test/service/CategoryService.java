package com.assisment.test.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assisment.test.dto.CategoryDto;
import com.assisment.test.entities.Category;
import com.assisment.test.exception.ResourceNotFoundException;
import com.assisment.test.repo.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = modelMapper.map(categoryDto, Category.class);
		Category save = categoryRepo.save(category);

		return this.modelMapper.map(save, CategoryDto.class);

	}

	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

		category.setCategoryName(categoryDto.getCategoryName());

		Category category2 = this.categoryRepo.save(category);

		return this.modelMapper.map(category2, CategoryDto.class);
	}

	public CategoryDto getCategoryById(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

		return this.modelMapper.map(category, CategoryDto.class);

	}

	public List<CategoryDto> getCategories() {
		List<Category> all = this.categoryRepo.findAll();

		return all.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
	}
	
	public String deleteCategoryById(Integer categoryId)
	{
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		
		this.categoryRepo.delete(category);
		
		return "category is deleted";
	}
}
