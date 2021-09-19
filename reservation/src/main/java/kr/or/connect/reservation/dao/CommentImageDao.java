package kr.or.connect.reservation.dao;

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

import kr.or.connect.reservation.dto.CommentImageDto;
import kr.or.connect.reservation.dto.ReservationUserCommentImageDto;

import static kr.or.connect.reservation.dao.Sqls.*;

@Repository
public class CommentImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentImageDto> rowMapper = BeanPropertyRowMapper.newInstance(CommentImageDto.class);
	private SimpleJdbcInsert insertAction;
	
	public CommentImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment_image").usingGeneratedKeyColumns("id");

	}
	
	public List<CommentImageDto> selectCommentImageByDisplayInfoId(int reservationUserCommentId){
		Map<String, Integer> params = Collections.singletonMap("reservationUserCommentId", reservationUserCommentId);
		return jdbc.query(SELECT_COMMENTIMAGE_BY_COMMENTID, params,rowMapper);
	}

	public int insertUserCommentImage(ReservationUserCommentImageDto userCommentImageDto) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(userCommentImageDto);
		return insertAction.executeAndReturnKey(params).intValue();
	}
}
