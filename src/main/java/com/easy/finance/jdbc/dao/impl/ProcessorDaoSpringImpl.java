package com.easy.finance.jdbc.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.easy.finance.jdbc.JdbcUtils;
import com.easy.finance.jdbc.domain.Processor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.easy.finance.jdbc.dao.ProcessorDao;

public class ProcessorDaoSpringImpl implements ProcessorDao {
	private SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(
			JdbcUtils.getDataSource());

	private static final int PAGESIZE = 10;
	
	@Override
	public void add(Processor bean) {
		String sql = "insert into Processor (sitename,domain,starturl,linkstr,bodytextstr,pubtimestr,sourcestr,titlestr,helpurlstr) values (:sitename,:domain,:starturl,:linkstr,:bodytextstr,:pubtimestr,:sourcestr,:titlestr,:helpurlstr)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(bean);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.simpleJdbcTemplate.getNamedParameterJdbcOperations().update(sql,
				param, keyHolder);
		bean.setId(keyHolder.getKey().intValue());
	}

	@Override
	public Processor getById(int id) {
		String sql = "select id,sitename,domain,starturl,linkstr,bodytextstr,pubtimestr,sourcestr,titlestr,helpurlstr  from Processor where id=?";
		return this.simpleJdbcTemplate.queryForObject(sql,ParameterizedBeanPropertyRowMapper.newInstance(Processor.class),id);
	}

	@Override
	public Processor getByName(String name) {
		String sql = "select id,sitename,domain,starturl,linkstr,bodytextstr,pubtimestr,sourcestr,titlestr,helpurlstr  from Processor where sitename=?";
		return this.simpleJdbcTemplate.queryForObject(sql,ParameterizedBeanPropertyRowMapper.newInstance(Processor.class),name);
	}

	@Override
	public boolean update(Processor bean) {
		String sql = "update Processor set sitename=:sitename, domain=:domain, starturl=:starturl,linkstr=:linkstr,bodytextstr=:bodytextstr,pubtimestr=:pubtimestr,sourcestr=:sourcestr,titlestr=:titlestr,helpurlstr=:helpurlstr where id=:id ";
		return (this.simpleJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(bean))>0);
	}

	@Override
	public boolean delete(Processor bean) {
		String sql = "delete from Processor where id=?";
		//受影响的行数是否大于0
		return (this.simpleJdbcTemplate.update(sql, bean.getId())>0);
	}

	@Override
	public boolean delete(int id) {
		String sql = "delete from Processor where id=?";
		return (this.simpleJdbcTemplate.update(sql, id)>0);
	}


	
	
	@Override
	public List<Processor> getList(int pageNum) {
		int num1 = pageNum*PAGESIZE;
		int num2 = (pageNum-1)*PAGESIZE+1;
		String sql = "select * from Processor limit " + (pageNum-1) * PAGESIZE+","+PAGESIZE;
		ArrayList<Processor> objList = new ArrayList<Processor>();
		objList = (ArrayList<Processor>) this.simpleJdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(Processor.class));
		return objList;
	}

	@Override
	public int getPageCount() {
		String sql = "SELECT COUNT(*) FROM Processor";
		int iPageCount = this.simpleJdbcTemplate.queryForInt(sql)/PAGESIZE+1;
		return iPageCount;
	}


}
