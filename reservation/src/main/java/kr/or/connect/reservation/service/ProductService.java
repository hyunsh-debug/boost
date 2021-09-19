package kr.or.connect.reservation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;

import kr.or.connect.reservation.dto.ProductListDto;

@Service
public class ProductService {
	private ProductDao productDao;
	
	public ProductService(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Transactional(readOnly = true)
	public ProductListDto getProductByCatogoryId(int categoryId, int start){
		ProductListDto list = new ProductListDto();

		list.setTotalCount(productDao.selectCountCategoryById(categoryId));
		list.pushItems(productDao.selectProductByCategoryId(categoryId, start));

		return list;
	}
	
	@Transactional(readOnly = true)
	public ProductListDto getProductAll(int start){
		ProductListDto list = new ProductListDto();

		list.setTotalCount(productDao.selectCountProductAll());
		list.pushItems(productDao.selectProductAll(start));


		return list;
	}
}
