package kr.or.connect.reservation.dto;

public class ReservationUserCommentImageDto {
	private int reservationInfoId;
	private int reservationUserCommentId;
	private int fileId;
	
	public ReservationUserCommentImageDto() {}
	
	public ReservationUserCommentImageDto(int reservationInfoId, int reservationUserCommentid, int fileId) {
		this.reservationInfoId = reservationInfoId;
		this.reservationUserCommentId = reservationUserCommentid;
		this.fileId = fileId;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(int reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
}
