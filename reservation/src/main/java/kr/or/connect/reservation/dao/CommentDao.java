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

import kr.or.connect.reservation.dto.CommentDto;
import kr.or.connect.reservation.dto.ReservationUserCommentDto;

import static kr.or.connect.reservation.dao.Sqls.*;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentDto> rowMapper = BeanPropertyRowMapper.newInstance(CommentDto.class);
	private SimpleJdbcInsert insertAction;
	
	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment").usingGeneratedKeyColumns("id");
	}
	
	public List<CommentDto> selectCommentByDisplayInfoId(int displayInfoId){
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_COMMENT_BY_DISPLAYINFOID, params, rowMapper);
	}
	
	public Double selectCommentAvgByDisplayInfoId(int displayInfoId) {
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		Double avg = jdbc.queryForObject(SELECT_COMMENT_AVG_BY_DISPLAYINFOID, params, Double.class);
		
		if(avg == null) 
			return null;
		else 
			return avg;
	}

	public int insertUserComment(ReservationUserCommentDto userCommentDto) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(userCommentDto);
		return insertAction.executeAndReturnKey(params).intValue();
	}
}
