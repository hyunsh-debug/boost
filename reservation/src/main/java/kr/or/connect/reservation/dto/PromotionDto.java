package kr.or.connect.reservation.dto;

public class PromotionDto {
	private int id;
	private int productId;
	private String saveFileImage;
	private String description;
	private String placeName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getSaveFileImage() {
		return saveFileImage;
	}
	public void setSaveFileImage(String saveFileImage) {
		this.saveFileImage = saveFileImage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	@Override
	public String toString() {
		return "PromotionDto [id=" + id + ", productId=" + productId + ", saveFileImage=" + saveFileImage
				+ ", description=" + description + ", placeName=" + placeName + "]";
	}
	
	
}
