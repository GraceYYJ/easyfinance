package com.easy.finance.jdbc.dao;

import java.util.List;

import com.easy.finance.jdbc.domain.Processor;

public interface ProcessorDao {
	public void add(Processor bean);

	public Processor getById(int id);
	
	public Processor getByName(String name);
	
	public boolean update(Processor bean);

	public boolean delete(Processor bean);
	
	public boolean delete(int id);
	
	public List<Processor> getList(int pageNum);
	
	public int getPageCount();
	
}
