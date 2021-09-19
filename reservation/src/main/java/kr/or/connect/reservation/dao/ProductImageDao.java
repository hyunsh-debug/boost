package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ProductImageDto;
import static kr.or.connect.reservation.dao.Sqls.*;

@Repository
public class ProductImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductImageDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductImageDto.class);
	
	public ProductImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductImageDto> selectProductImageByDisplayInfoId(int displayInfoId){
		Map<String, Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCTIMAGE_BY_DISPLAYINFOID, params, rowMapper);
	}
	
	
}
