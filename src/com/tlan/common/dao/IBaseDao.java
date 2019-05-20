package com.tlan.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

/**
 * 扩展HibernateDaoSupport的泛型基类
 * 
 * @param <T>
 *            实体类型
 */
public interface IBaseDao<T> {

	public static final String CONTAINS = "CONTAINS";
	public static final String IN = "in";
	public static final String IS = "is";

	public Session getSession();

	/**
	 * 根据实体类与ID获得对象
	 * 
	 * @param clazz
	 *            实体类
	 * @param id
	 *            主键ID
	 */
	public <X> X get(final Class<X> clazz, final Serializable id);

	/**
	 * 根据条件获取对象
	 * 
	 * @param clazz
	 * @param filename
	 * @param value
	 * 
	 */
	public <X> X get(final Class<T> clazz, String filename, Object value);

	/**
	 * 条件查询
	 * 
	 * @param clazz
	 * @param field
	 * @param value
	 * @param ch
	 * @return
	 */
	public List<T> getAll(Class<T> clazz, String field, String value, String ch);

	public List<T> getAll(Class<T> clazz, String headers);

	/**
	 * 条件查询
	 * 
	 * @param clazz
	 * @param field
	 * @param value
	 * @param ch
	 *            符号
	 * @return
	 */
	public List<T> getAll(Class<T> clazz, String[] fields, Object[] values,
			String[] chs);

	/**
	 * 条件查询
	 * 
	 * @param clazz
	 * @param field
	 * @param value
	 * @param ch
	 * @param order
	 *            符号
	 * @return
	 */
	public List<T> getAll(Class<T> clazz, String[] fields, Object[] values,
			String[] chs, String order);

	/**
	 * 根据id获得对象
	 * 
	 * @param id
	 *            主键ID
	 */

	public T get(Serializable id);

	/**
	 * 删除对象
	 * 
	 * @param entity
	 *            实体类
	 */
	public void delete(final Object entity);

	/**
	 * 删除对象
	 * 
	 * @param entity
	 *            实体类
	 */
	public void deleteAll(Class<T> clazz);

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 *            主键ID
	 */
	public void delete(final Serializable id);

	/**
	 * 根据实体类与ID删除对象
	 * 
	 * @param clazz
	 *            实体类
	 * @param id
	 *            主键ID
	 */
	public void delete(final Class clazz, final Serializable id);

	/**
	 * 根据条件删除
	 * 
	 * @param clazz
	 *            实体类
	 * @param filedname
	 * @param value
	 */
	public void delete(Class<T> clazz, String fieldname, Object value);

	/**
	 * 根据多条件删除
	 * 
	 * @param clazz
	 *            实体类
	 * @param filedname
	 * @param value
	 */
	public void delete(Class<T> clazz, String[] fieldnames, Object[] values);

	/**
	 * 
	 * 保存对象
	 * 
	 * @param entity
	 *            保存的实体对象
	 */
	public void save(final Object entity);

	/**
	 * 
	 * 保存对象
	 * 
	 * @param entity
	 *            保存的实体对象
	 */
	public void saveList(final List<T> t);

	/**
	 * 
	 * 保存对象
	 * 
	 * @param entity
	 *            保存的实体对象
	 */
	public void saveOrUpdate(final Object entity);

	/**
	 * 
	 * 更新对象
	 * 
	 * @param entity
	 *            更新的实体对象
	 */
	public void update(final Object entity);

	/**
	 * 指定更新对象
	 * 
	 * @param t
	 * @param field
	 * @param name
	 * @param condition
	 */
	public void update(Class<T> clazz, String[] field, Object[] value,
			String condition);

	/**
	 * 执行sql
	 * 
	 * @param sql
	 * @return
	 */
	public boolean executeSql(List<String> sql);

	/**
	 * 获取所有数据
	 * 
	 * @param entityClass
	 *            参数T的反射类型
	 */
	public <X> List<X> getAll(final Class<X> entityClass);

	/**
	 * 获取所有数据条数
	 * 
	 * @param entityClass
	 *            参数T的反射类型
	 */
	public long getCount(final Class<T> entityClass);

	/**
	 * 
	 * 根据条件获取数据
	 * 
	 * @param criterions
	 *            数量可变的Criterion
	 */
	public List<T> query(final Criterion... criterions);

	/**
	 * 
	 * HQL方式查询
	 * 
	 * @param hql
	 *            符合HQL语法的查询语句
	 * 
	 * @param values
	 *            数量可变的条件值,按顺序绑定
	 */
	public Query createQuery(final String hql, final Object... values);

	/**
	 * 
	 * SQL方式查询
	 * 
	 * @param sql
	 *            符合SQL语法的查询语句
	 * 
	 * @param values
	 *            数量可变的条件值,按顺序绑定
	 */
	public SQLQuery createSQLQuery(final String sql, final Object... values);

	/**
	 * 
	 * 根据类型创建查询对象
	 * 
	 * @param clazz
	 *            类型
	 */
	public Criteria createCriteria(final Class clazz);

	/**
	 * 
	 * 对象化查询
	 * 
	 * @param entityClass
	 *            参数T的反射类型
	 * 
	 * @param criterions
	 *            数量可变的Criterion
	 */
	public Criteria createCriteria(final Class clazz,
			final Criterion... criterions);

	/**
	 * 
	 * 对象化查询
	 * 
	 * @param criterions
	 *            数量可变的Criterion
	 */
	public Criteria createCriteria(final Criterion... criterions);

	/**
	 * 
	 * 分页查询
	 * 
	 * @param hql
	 *            HQL查询语句
	 * @param offset
	 *            开始位置
	 * @param pageSize
	 *            查询数量
	 * 
	 */
	public <X> List<X> findByPage(final Class<X> entityClass, final int offset,
			final int pageSize);

	/**
	 * 带排序查询
	 * 
	 * @param clazz
	 * @param offset
	 * @param pageSize
	 * @param order
	 * @return
	 */
	public List<T> findPage(Class<T> clazz, int offset, int pageSize,
			String order);

	/**
	 * 分页查询
	 * 
	 * @param entityClass
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @param value
	 * @return
	 */
	public <X> List<X> findByPage(final Class<X> entityClass, final int offset,
			final int pageSize, String[] field, Object[] value);

	/**
	 * 
	 * 分页查询
	 * 
	 * @param hql
	 *            HQL查询语句
	 * @param offset
	 * 
	 *            开始位置
	 * @param pageSize
	 *            查询数量
	 * 
	 */
	public List<T> findByPage(final String hql, final int offset,
			final int pageSize);

	/**
	 * 
	 * 单参数分页查询
	 * 
	 * @param hql
	 *            HQL查询语句
	 * @param value
	 *            参数值
	 * @param offset
	 *            开始位置
	 * @param pageSize
	 *            查询数量
	 * 
	 */
	public List<T> findByPage(final String hql, final String value,
			final int offset, final int pageSize);

	/**
	 * 
	 * 多参数分页查询
	 * 
	 * @param hql
	 *            HQL查询语句
	 * @param value
	 *            [] 参数值
	 * @param offset
	 *            开始位置
	 * @param pageSize
	 *            查询数量
	 * 
	 */
	public List<T> findByPage(final String hql, final String[] value,
			final int offset, final int pageSize);

	/**
	 * 指定字段排序
	 * 
	 * @param clazz
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @param value
	 * @param order
	 * @return
	 */
	public List<T> findByPage(Class<T> clazz, int offset, int pageSize,
			String[] field, Object[] value, String order);

	/**
	 * 指定字段排序
	 * 
	 * @param clazz
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @param value
	 * @param order
	 * @return
	 */
	public List<T> findByPage(Class<T> clazz, int offset, int pageSize,
			String[] field, Object[] value, String[] ch, String order);

	public long getCount(Class<T> clazz, String[] field, Object[] value,
			String[] ch);

	/****** DetachedCriteria ********/
	/**
	 * 分页查询
	 * 
	 * @Title: findByCriteria
	 * @Description: TODO
	 * @param @param detachedCriteria
	 * @param @param first
	 * @param @param max
	 * @param @return
	 * @return List<T>
	 * @throws
	 * @Date 2014年3月3日 上午10:23:04
	 */
	public List<T> findByCriteria(DetachedCriteria detachedCriteria,
			int offset, int pageSize);

	/**
	 * 查询
	 * 
	 * @Title: findByCriteria
	 * @Description: TODO
	 * @param @param detachedCriteria
	 * @param @return
	 * @return List<T>
	 * @throws
	 * @Date 2014年3月3日 上午10:33:35
	 */
	public List<T> findByCriteria(DetachedCriteria detachedCriteria);

	/**
	 * 总数查询
	 * 
	 * @Title: findCountByCriteria
	 * @Description: TODO
	 * @param @param detachedCriteria
	 * @param @return
	 * @return int
	 * @throws
	 * @Date 2014年3月3日 上午10:23:22
	 */
	public int findCountByCriteria(DetachedCriteria detachedCriteria);
	/**
	 * 
	 * HQL方式查询
	 * 
	 * @param hql
	 *            符合HQL语法的查询语句
	 * 
	 */
	public List<T> hqlQuery(final String hql);
	
	
	/**
	 * 带条件的分页查询
	 * @Title: findByCriteria  
	 * @Description: 
	 * @param t1
	 * @param t3
	 * @param offset
	 * @param pageSize
	 * @return List<T>
	 * @throws
	 */
	public List<T> findByCriteria(T t1,T t2,Object param, int offset, int pageSize);
	
	
	/**
	 * 
	 * @Title: getCount  
	 * @Description: 统计分页数据总数
	 * @param t1
	 * @param t2
	 * @param param
	 * @return long
	 * @throws
	 */
	public long getCount(T t1,T t2,Object param);
}
