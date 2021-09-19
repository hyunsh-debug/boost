package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;

public class ReservationInfoDto {
	private int id;
	private int productId;
	private int displayInfoId;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private String reservationDate;
	private boolean cancelFlag;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTel() {
		return reservationTel;
	}
	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public boolean isCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
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
	@Override
	public String toString() {
		return "ReservationInfoDto [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
				+ ", reservationName=" + reservationName + ", reservationTel=" + reservationTel + ", reservationEmail="
				+ reservationEmail + ", reservationDate=" + reservationDate + ", cancelFlag=" + cancelFlag
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
	
	public void inputReservationInfoField(RequestReservationInfoDto requestReservationInfoDto) {
		this.displayInfoId = requestReservationInfoDto.getDisplayInfoId();
		this.productId = requestReservationInfoDto.getProductId();
		this.displayInfoId = requestReservationInfoDto.getDisplayInfoId();
		this.reservationName = requestReservationInfoDto.getReservationName();
		this.reservationTel = requestReservationInfoDto.getReservationTelephone();
		this.reservationEmail = requestReservationInfoDto.getReservationEmail();
		this.reservationDate = requestReservationInfoDto.getReservationYearMonthDay();
		this.createDate = requestReservationInfoDto.getCreateDate();
		this.modifyDate = requestReservationInfoDto.getModifyDate();
	}
	
}
