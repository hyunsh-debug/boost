package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.Sqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ProductDto;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);
	private static final int LIMIT = 4;
	
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public int selectCountProductAll() {
		return jdbc.queryForObject(SELECT_COUNT_PRODUCT_ALL, Collections.emptyMap(),Integer.class);
	}
	
	public int selectCountCategoryById(int categoryId) {
		Map<String, Integer> params = Collections.singletonMap("id", categoryId);
		return jdbc.queryForObject(SELECT_COUNT_CATEGORY_BY_ID, params, Integer.class);
	}

	public List<ProductDto> selectProductAll(int start){
		Map<String, Integer> params = new HashMap<>();
		params.put("limit", LIMIT);
		params.put("start", start);
		
		return jdbc.query(SELECT_PRODUCT_ALL, params, rowMapper);
	}
	
	public List<ProductDto> selectProductByCategoryId(int categoryId, int start){
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		return jdbc.query(SELECT_PRODUCT_BY_CATEGORY_ID, params, rowMapper);
	}
}
