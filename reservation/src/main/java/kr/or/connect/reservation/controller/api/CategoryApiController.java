package kr.or.connect.reservation.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.CategoryDto;
import kr.or.connect.reservation.service.CategoryService;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryApiController {
	private CategoryService categoryService;
	
	public CategoryApiController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public List<CategoryDto> categoryList(){
		return categoryService.getCategory();
	}
}
