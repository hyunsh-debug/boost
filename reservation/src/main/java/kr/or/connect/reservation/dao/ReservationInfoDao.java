package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.Sqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationInfoDto;

@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfoDto> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfoDto.class);
	private SimpleJdbcInsert insertAction;
	
	public ReservationInfoDao(DataSource dataSource) {
		  this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	      this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info").usingGeneratedKeyColumns("id");   
	}
	
	public int insertReservationInfo(ReservationInfoDto reservationInfoDto) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfoDto);
		return insertAction.executeAndReturnKey(params).intValue();
	}

	public List<ReservationInfoDto> selectReservationInfoByEmail(String resrvEmail) {
		Map<String, String> params = Collections.singletonMap("resrvEmail", resrvEmail);	
		return jdbc.query(SELECT_RESERVATION_INFO_BY_EMAIL, params, rowMapper);
	}

	public int updateReservationInfo(int reservationInfoId) {
		Map<String, Integer> params = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.update(UPDATE_BY_ID, params);
	}
}
