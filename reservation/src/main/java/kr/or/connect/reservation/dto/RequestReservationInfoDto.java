package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestReservationInfoDto {
	private int productId;
	private int displayInfoId;
	private String reservationName;
	private String reservationTelephone;
	private String reservationEmail;
	private String reservationYearMonthDay;
	private List<RequestReservationInfoPriceDto> prices;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	
	
	public RequestReservationInfoDto() {
		prices = new ArrayList<>();
		this.createDate = LocalDateTime.now();
		this.modifyDate = LocalDateTime.now();
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

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationYearMonthDay() {
		return reservationYearMonthDay;
	}

	public void setReservationYearMonthDay(String reservationYearMonthDay) {
		this.reservationYearMonthDay = reservationYearMonthDay;
	}

	public List<RequestReservationInfoPriceDto> getPrices() {
		return prices;
	}

	public void setPrices(List<RequestReservationInfoPriceDto> prices) {
		this.prices = prices;
	}
	public void setReservationInfoId(int reservationInfoId) {
		for(RequestReservationInfoPriceDto price : this.prices) {
			price.setReservationInfoId(reservationInfoId);
		}
	}
	public void setReservationInfoPriceId(int reservationInfoPriceId, int index) {
		this.prices.get(index).setReservationInfoPriceId(reservationInfoPriceId);
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
		return "RequestReservationInfoDto [productId=" + productId + ", displayInfoId=" + displayInfoId
				+ ", reservationName=" + reservationName + ", reservationTelephone=" + reservationTelephone
				+ ", reservationEmail=" + reservationEmail + ", reservationYearMonthDay=" + reservationYearMonthDay
				+ ", prices=" + prices + "]";
	}
	
	
}
