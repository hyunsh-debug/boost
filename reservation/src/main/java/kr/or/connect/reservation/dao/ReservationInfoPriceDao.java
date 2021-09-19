package kr.or.connect.reservation.dao;

import java.util.Collections;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationInfoPriceDto;
import static kr.or.connect.reservation.dao.Sqls.*;

@Repository
public class ReservationInfoPriceDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public ReservationInfoPriceDao(DataSource dataSource) {
		  this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	      this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info_price").usingGeneratedKeyColumns("id");   
	}

	public int insertReservationInfoPrice(ReservationInfoPriceDto reservationInfoPriceDto) {	
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfoPriceDto);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public int selectReservationInfoPriceByDisplayInfoId(int reservationInfoId){
		Map<String, Integer> params = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.queryForObject(SELECT_TOTAL_PRICE_BY_RESERVATIONID, params, Integer.class);
	}
	
}
