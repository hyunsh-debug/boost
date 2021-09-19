package kr.or.connect.reservation.dao;


import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.FileInfoDto;
import static kr.or.connect.reservation.dao.Sqls.*;

@Repository
public class FileInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<FileInfoDto> rowMapper = BeanPropertyRowMapper.newInstance(FileInfoDto.class);
	public FileInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("file_info").usingGeneratedKeyColumns("id");
	}
	public int insertFileInfo(FileInfoDto fileInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(fileInfo);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	public FileInfoDto selectFileInfoByImageId(int reservationUserCommentImageId) {
		Map<String, Integer> params = Collections.singletonMap("reservationUserCommentImageId", reservationUserCommentImageId);
		return jdbc.queryForObject(SELECT_FILE_INFO_BY_USER_COMMENT_IMAGEID, params, rowMapper);
	}
}
