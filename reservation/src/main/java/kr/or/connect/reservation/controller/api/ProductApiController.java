package kr.or.connect.reservation.controller.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.ProductDetailInfoDto;
import kr.or.connect.reservation.dto.ProductListDto;
import kr.or.connect.reservation.service.ProductDetailInfoService;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping(path="/api/products")
public class ProductApiController {
	private ProductService productService;
	private ProductDetailInfoService productDetailInfoService;

	public ProductApiController(ProductService productService, ProductDetailInfoService productDetailInfoService) {
		this.productService = productService;
		this.productDetailInfoService = productDetailInfoService;
	}
	@GetMapping("/productall")
	public ProductListDto productlistAll(@RequestParam(value = "start", defaultValue = "0") int start){
		return productService.getProductAll(start);	
	}
	
	@GetMapping
	public ProductListDto productList(@RequestParam(value = "categoryId") int categoryId, @RequestParam(value = "start") int start) {
		return productService.getProductByCatogoryId(categoryId, start);
		
	}

	@GetMapping("/{displayInfoId}")
	public ProductDetailInfoDto productDetailInfo(@PathVariable(name="displayInfoId") int displayInfoId) {		
		return productDetailInfoService.getProductDetailInfo(displayInfoId);
	}

}
