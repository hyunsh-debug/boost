package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dao.ReservationInfoPriceDao;
import kr.or.connect.reservation.dto.RequestReservationInfoDto;
import kr.or.connect.reservation.dto.RequestReservationInfoPriceDto;
import kr.or.connect.reservation.dto.ReservationInquiryDto;
import kr.or.connect.reservation.dto.ReservationInfoDto;
import kr.or.connect.reservation.dto.ReservationInfoPriceDto;
import kr.or.connect.reservation.dto.ReservationInquiryListDto;

@Service
public class ReservationService {
	private ReservationInfoDao reservationInfoDao;
	private ReservationInfoPriceDao reservationInfoPriceDao;
	private DisplayInfoDao displayInfoDao;
	
	public ReservationService(ReservationInfoDao reservationDao, ReservationInfoPriceDao reservationInfoPriceDao, DisplayInfoDao displayInfoDao) {
		this.reservationInfoDao = reservationDao;
		this.reservationInfoPriceDao = reservationInfoPriceDao;
		this.displayInfoDao = displayInfoDao;
	}
	
	@Transactional
	public RequestReservationInfoDto setReservationParam(RequestReservationInfoDto requestReservationInfoDto) {
		int index = 0;
		ReservationInfoDto reservationInfoDto = new ReservationInfoDto();
		
		reservationInfoDto.inputReservationInfoField(requestReservationInfoDto);
		int reservationInfoId = reservationInfoDao.insertReservationInfo(reservationInfoDto);		
		requestReservationInfoDto.setReservationInfoId(reservationInfoId); 
		
		for(RequestReservationInfoPriceDto priceItem : requestReservationInfoDto.getPrices()){
			if(priceItem.getCount() > 0) {
				ReservationInfoPriceDto reservationInfoPriceDto = new ReservationInfoPriceDto();
				reservationInfoPriceDto.setReservationInfoPriceField(priceItem);
				int reservationInfoPriceId = reservationInfoPriceDao.insertReservationInfoPrice(reservationInfoPriceDto);
				requestReservationInfoDto.setReservationInfoPriceId(reservationInfoPriceId, index);
				index++;
			}
		};

		return requestReservationInfoDto;
	}
	
	@Transactional(readOnly = true)
	public boolean isEmailChk(String resrvEmail) {
		List<ReservationInfoDto> reservationInfoList = reservationInfoDao.selectReservationInfoByEmail(resrvEmail);
		
		return !reservationInfoList.isEmpty();
	}
	
	@Transactional(readOnly = true)
	public ReservationInquiryDto getReservationInfoAll(String reservationEmail) {
		ReservationInquiryDto reservationDto = new ReservationInquiryDto();
		List<ReservationInfoDto> reservationInfoDto = reservationInfoDao.selectReservationInfoByEmail(reservationEmail);
		
		for(ReservationInfoDto list : reservationInfoDto) {
			ReservationInquiryListDto reservationInquiryListDto = new ReservationInquiryListDto();
			reservationInquiryListDto.insertReservationInfo(list);
			reservationInquiryListDto.setDisplayInfo(displayInfoDao.selectDisplayInfoByDisplayInfoId(list.getDisplayInfoId()));
			reservationInquiryListDto.setTotalPrice(reservationInfoPriceDao.selectReservationInfoPriceByDisplayInfoId(list.getId()));
			reservationDto.addReservationInquiryListDto(reservationInquiryListDto);
		}
		
		return reservationDto;
	}
	@Transactional
	public int updateReservationInfo(int reservationInfoId) {
		return reservationInfoDao.updateReservationInfo(reservationInfoId);
	}
}
