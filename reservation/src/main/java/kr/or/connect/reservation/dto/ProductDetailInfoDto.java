package kr.or.connect.reservation.dto;

import java.util.List;

public class ProductDetailInfoDto {
	private DisplayInfoDto displayInfo;
	private List<ProductImageDto> productImages;
	private DisplayInfoImageDto displayInfoImage;
	private List<CommentDto> comments;
	private double averageScore;
	private List<ProductPricesDto> productPrices;
	
	public DisplayInfoDto getDisplayInfo() {
		return displayInfo;
	}
	public void setDisplayInfo(DisplayInfoDto displayInfo) {
		this.displayInfo = displayInfo;
	}
	public List<ProductImageDto> getProductImages() {
		return productImages;
	}
	public void setProductImages(List<ProductImageDto> productImages) {
		this.productImages = productImages;
	}
	public DisplayInfoImageDto getDisplayInfoImage() {
		return displayInfoImage;
	}
	public void setDisplayInfoImage(DisplayInfoImageDto displayInfoImage) {
		this.displayInfoImage = displayInfoImage;
	}
	public List<CommentDto> getComments() {
		return comments;
	}
	public void setComents(List<CommentDto> comments) {
		this.comments = comments;
	}
	public double getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}
	public List<ProductPricesDto> getProductPrices() {
		return productPrices;
	}
	public void setProductPrices(List<ProductPricesDto> productPrices) {
		this.productPrices = productPrices;
	}
	
	@Override
	public String toString() {
		return "ProductDetailInfoDto [displayInfo=" + displayInfo + ", productImages=" + productImages
				+ ", displayInfoImage=" + displayInfoImage + ", coments=" + comments + ", averageScore=" + averageScore
				+ ", productPrices=" + productPrices + "]";
	}
}
