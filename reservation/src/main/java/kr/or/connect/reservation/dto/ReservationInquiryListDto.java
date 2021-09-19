package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;

public class ReservationInquiryListDto {
	private int reservationInfoId;
	private int productId;
	private int displayInfoId;
	private String reservationDate;
	private String reservationEmail;
	private String reservationName;
	private String reservationTelephone;
	private boolean cancelYn;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private DisplayInfoDto displayInfo;
	private int totalPrice;
	
	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public boolean isCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(boolean cancelYn) {
		this.cancelYn = cancelYn;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public DisplayInfoDto getDisplayInfo() {
		return displayInfo;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setDisplayInfo(DisplayInfoDto displayInfo) {		
		this.displayInfo = displayInfo;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString() {
		return "ReservationInquiryListDto [reservationInfoId=" + reservationInfoId + ", productId=" + productId
				+ ", displayInfoId=" + displayInfoId + ", reservationDate=" + reservationDate + ", reservationEmail="
				+ reservationEmail + ", reservationName=" + reservationName + ", reservationTelephone="
				+ reservationTelephone + ", cancelYn=" + cancelYn + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", displayInfo=" + displayInfo + ", totalPrice=" + totalPrice + "]";
	}

	public void insertReservationInfo(ReservationInfoDto list) {
		this.reservationInfoId = list.getId();
		this.productId = list.getProductId();
		this.displayInfoId = list.getDisplayInfoId();
		this.reservationDate = list.getReservationDate();
		this.reservationEmail = list.getReservationEmail();
		this.reservationName = list.getReservationName();
		this.reservationTelephone = list.getReservationTel();
		this.cancelYn = list.isCancelFlag();
		this.createDate = list.getCreateDate();
		this.modifyDate = list.getModifyDate();
	}
}
