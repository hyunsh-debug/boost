package kr.or.connect.reservation.controller.api;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.PromotionDto;
import kr.or.connect.reservation.service.PromotionService;

@RestController
@RequestMapping(path = "/api/promotions")
public class PromotionApiController {
	private PromotionService promotionService;
	
	public PromotionApiController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}
	
	@GetMapping
	public List<PromotionDto> promotionList(){
		return promotionService.getPromotion();
	}
	
}
