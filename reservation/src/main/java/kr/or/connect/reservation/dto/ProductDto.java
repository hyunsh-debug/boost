package kr.or.connect.reservation.dto;


public class ProductDto {
	enum Type{
		th
	}
	private int displayInfoId;
	private int productId;
	private String description;
	private String placeName; 
	private String content; 
	private String saveFileName;
	private Type type; 
	
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "ProductDto [displayInfoId=" + displayInfoId + ", productId=" + productId + ", description="
				+ description + ", placeName=" + placeName + ", content=" + content + ", saveFileName=" + saveFileName
				+ ", type=" + type + "]";
	}
}
