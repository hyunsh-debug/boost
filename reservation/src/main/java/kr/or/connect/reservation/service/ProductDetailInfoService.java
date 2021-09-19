package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dao.CommentImageDao;
import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.DisplayInfoImageDao;
import kr.or.connect.reservation.dao.ProductImageDao;
import kr.or.connect.reservation.dao.ProductPricesDao;
import kr.or.connect.reservation.dto.CommentDto;
import kr.or.connect.reservation.dto.CommentImageDto;
import kr.or.connect.reservation.dto.ProductDetailInfoDto;

@Service
public class ProductDetailInfoService {
	private DisplayInfoDao displayInfo;
	private ProductImageDao productImages;
	private DisplayInfoImageDao displayInfoImage;
	private ProductPricesDao productPrices;
	private CommentDao comments;
	private CommentImageDao commentImages;
	
	public ProductDetailInfoService(DisplayInfoDao displayInfo, ProductImageDao productImages, DisplayInfoImageDao displayInfoImage, ProductPricesDao productPrices, CommentDao comments, CommentImageDao commentImages) {
		this.displayInfo = displayInfo;
		this.productImages = productImages;
		this.displayInfoImage = displayInfoImage;
		this.productPrices = productPrices;
		this.comments = comments;
		this.commentImages = commentImages;
	}
	
	@Transactional(readOnly = true)
	public ProductDetailInfoDto getProductDetailInfo(int displayInfoId) {
		ProductDetailInfoDto productDetailInfoDto = new ProductDetailInfoDto();
		
		if(comments.selectCommentAvgByDisplayInfoId(displayInfoId) != null) {
			productDetailInfoDto.setAverageScore(comments.selectCommentAvgByDisplayInfoId(displayInfoId));	
		}
		
		productDetailInfoDto.setDisplayInfo(displayInfo.selectDisplayInfoByDisplayInfoId(displayInfoId));
		productDetailInfoDto.setProductImages(productImages.selectProductImageByDisplayInfoId(displayInfoId));
		productDetailInfoDto.setDisplayInfoImage(displayInfoImage.selectDisplayInfoImageByDisplayInfoId(displayInfoId));
		productDetailInfoDto.setProductPrices(productPrices.selectProductPricesByDisplayInfoId(displayInfoId));
		
		List<CommentDto> commentList = comments.selectCommentByDisplayInfoId(displayInfoId);

		
		for (int i = 0; i < commentList.size(); i++) {
			int commentId = commentList.get(i).getCommentId();
			
			if(!commentImages.selectCommentImageByDisplayInfoId(commentId).isEmpty()) {
				List<CommentImageDto> commentImageList = commentImages.selectCommentImageByDisplayInfoId(commentId);
				commentList.get(i).pushItmes(commentImageList);
				
			}
		}

		productDetailInfoDto.setComents(commentList);
		return productDetailInfoDto;
	}
}
