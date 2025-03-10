package com.jdc.ps.service;

import java.util.List;

/**
 * Base is base for services for each entity
 * T stands for each entity
 * @author kerien
 * @since 1.0
 */
public interface BaseService<T> {

	/**
	 * @param entity
	 * @return generated primary key
	 * @author kerien
	 */
	int insert(T entity);
	
	/**
	 * @param entity list for insert
	 * @return record count that inserted
	 * @author kerien
	 */
	int insert(List<T> entities);
	
	void update(int id, T entity);
	
	void delete(int id);
	
	List<T> selectAll();
	
	T selectById(int id);
	
	long count();
}