package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dto.CategoryDto;

@Service
public class CategoryService {
	private CategoryDao categoryDao;
	
	public CategoryService(CategoryDao categoryDao) {
		// TODO Auto-generated method stub
		this.categoryDao = categoryDao;
	}
	
	@Transactional(readOnly = true) 
	public List<CategoryDto> getCategory(){
		List<CategoryDto> list = categoryDao.selectCategory();
		return list;
	}
}
