package kr.or.connect.reservation.dto;

import java.util.ArrayList;
import java.util.List;

public class ReservationInquiryDto {
	private List<ReservationInquiryListDto> reservations;
	private int size;
	
	public ReservationInquiryDto() {
		this.reservations = new ArrayList<ReservationInquiryListDto>();
	}
	
	public List<ReservationInquiryListDto> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationInquiryListDto> reservations) {
		this.reservations = reservations;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ReservationInquiryDto [reservations=" + reservations + ", size=" + size + "]";
	}
	
	public void addReservationInquiryListDto(ReservationInquiryListDto reservationInquiryListDto) {
		this.reservations.add(reservationInquiryListDto);
		this.size += 1;
	}
	
}
