package com.tlan.common.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.tlan.common.utils.Page;

public interface IBaseService<T> {

	public void save(T t);

	public void saveList(List<T> t);

	public void saveOrUpdate(T t);

	public void update(T t);

	public void update(Class<T> entityClass, String[] field, Object[] value,
			String condition);

	public boolean executeSql(List<String> sql);

	public void delete(T t);

	public void delete(Integer id);

	public void delete(Class<T> clazz, Integer id);

	public void delete(Class<T> clazz, String fieldname, Object value);

	public void delete(Class<T> clazz, String[] filename, Object[] value);

	public void deleteAll(Class<T> clazz);

	public List<T> findPage(Class<T> clazz, int offset, int pageSize);

	public List<T> findPage(Class<T> clazz, int offset, int pageSize,
			String order);

	public List<T> findPage(Class<T> clazz, int offset, int pageSize,
			String[] field, Object[] value);

	public List<T> findPage(Class<T> clazz, int offset, int pageSize,
			String[] field, Object[] value, String order);

	public List<T> findPage(Class<T> clazz, int offset, int pageSize,
			String[] field, Object[] value, String[] ch, String order);

	public List<T> getAll(Class<T> clazz);

	public List<T> getAll(Class<T> clazz, String headers);

	public List<T> getAll(Class<T> clazz, String fieldname, Object value);

	public List<T> getAll(Class<T> clazz, String[] field, Object[] value);

	public List<T> getAll(Class<T> clazz, String[] field, Object[] value,
			String orderfield, String order);

	public List<T> getAll(Class<T> clazz, String[] field, Object[] value,
			String[] ch, String order);

	public List<T> getAll(Class<T> clazz, String field, String value, String ch);

	public List<T> getAll(Class<T> clazz, String[] field, Object[] value,
			String[] ch);

	public long getCount(Class<T> clazz, String[] field, Object[] value,
			String[] ch);

	public long getCount(Class<T> clazz);

	public T get(Integer id);

	public T get(Class<T> clazz, Integer id);

	public T get(Class<T> clazz, String fieldname, Object value);

	public Page getPage(Class<T> clazz);

	public void setPage(Page page);

	/************ add 2014-3-3 magenm *****************/
	public List<T> findByCriteria(DetachedCriteria detachedCriteria,
			int offset, int pageSize);

	public List<T> findByCriteria(DetachedCriteria detachedCriteria);

	public int findCountByCriteria(DetachedCriteria detachedCriteria);
	/************ add 2014-3-3 magenm *****************/

	public List<T> hqlQuery(String hql);
	
	/****************我的接口*********************/
	public List<T> findByCriteria(T t1,T t2,Object param,int offset, int pageSize);
	public long getCount(T t1,T t2,Object param);
	
}
