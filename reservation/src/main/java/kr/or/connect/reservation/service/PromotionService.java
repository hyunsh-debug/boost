package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.PromotionDto;

@Service
public class PromotionService {
	private PromotionDao promotionDao;
	
	public PromotionService(PromotionDao promotionDao) {
		this.promotionDao = promotionDao;
	}
	
	@Transactional(readOnly = true)
	public List<PromotionDto> getPromotion(){
		List<PromotionDto> list = promotionDao.selectPromotion();

		return list;
	}
}
