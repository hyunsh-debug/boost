package kr.or.connect.reservation.dto;

public class ReservationInfoPriceDto {
	private int reservationInfoId;
	private int productPriceId;
	private int count;
	
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public int getProductPriceId() {
		return productPriceId;
	}
	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setReservationInfoPriceField(RequestReservationInfoPriceDto requestReservationInfoPriceDto) {
		this.count = requestReservationInfoPriceDto.getCount();
		this.productPriceId = requestReservationInfoPriceDto.getProductPriceId();
		this.reservationInfoId = requestReservationInfoPriceDto.getReservationInfoId();
	}
	
}
