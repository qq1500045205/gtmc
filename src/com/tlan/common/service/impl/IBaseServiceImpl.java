package com.tlan.common.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlan.common.dao.IBaseDao;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.Page;

@Service("baseService")
public class IBaseServiceImpl<T> implements IBaseService<T> {

	@Autowired
	private IBaseDao<T> baseDao;

	private Page page;

	public void setPage(Page page) {
		this.page = page;
	}

	public Page getPage(Class<T> clazz) {
		// TODO Auto-generated method stub
		int offset = (page.getCurrentPage() - 1) * page.getPageSize();
		String hql = "From " + clazz.getSimpleName();
		List<T> list = baseDao.findByPage(hql, offset, page.getPageSize());
		page.setList(list);
		page.setRowCount(list.size());
		page.setTotalPage(page.getRowCount() / page.getPageSize()
				+ page.getRowCount() % page.getPageSize());
		page.setHasNextPage(page.getTotalPage() > page.getCurrentPage() ? true
				: false);
		page.setNextPage(page.getCurrentPage() + 1);
		page.setHasPreviousPage(page.getCurrentPage() > 1 ? true : false);
		page.setPrePage(page.getCurrentPage() - 1);
		return page;
	}

	public void save(T t) {
		baseDao.save(t);
	}

	public void saveList(List<T> t) {
		baseDao.saveList(t);
	}

	/**
	 * 
	 * 保存对象
	 * 
	 * @param entity
	 *            保存的实体对象
	 */
	public void saveOrUpdate(T t) {
		baseDao.saveOrUpdate(t);
	}

	public void update(T t) {
		// TODO Auto-generated method stub
		baseDao.update(t);
	}

	public void delete(T t) {
		// TODO Auto-generated method stub
		baseDao.delete(t);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		baseDao.delete(id);
	}

	public void delete(Class<T> clazz, Integer id) {
		// TODO Auto-generated method stub
		baseDao.delete(clazz, id);
	}

	public void delete(Class<T> clazz, String fieldname, Object value) {
		// TODO Auto-generated method stub
		baseDao.delete(clazz, fieldname, value);
	}

	public void delete(Class<T> clazz, String[] fieldname, Object[] value) {
		// TODO Auto-generated method stub
		baseDao.delete(clazz, fieldname, value);
	}

	public List<T> findPage(Class<T> clazz, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.findByPage(clazz, offset, pageSize);
	}

	public List<T> findPage(Class<T> clazz, int offset, int pageSize,
			String[] field, Object[] value) {
		// TODO Auto-generated method stub
		return baseDao.findByPage(clazz, offset, pageSize, field, value);
	}

	public List<T> getAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return baseDao.getAll(clazz);
	}

	public long getCount(Class<T> clazz) {
		// TODO Auto-generated method stub
		return baseDao.getCount(clazz);
	}

	public T get(Integer id) {
		// TODO Auto-generated method stub
		return (T) baseDao.get(id);
	}

	public T get(Class<T> clazz, Integer id) {
		// TODO Auto-generated method stub
		return (T) baseDao.get(clazz, id);
	}

	public List<T> getAll(Class<T> clazz, String field, Object value) {
		// TODO Auto-generated method stub
		String hql = "From " + clazz.getSimpleName() + " where " + field
				+ "= ? ";
		return this.baseDao.createQuery(hql, value).list();
	}

	public List<T> getAll(Class<T> clazz, String[] filename, Object[] value) {
		// TODO Auto-generated method stub
		if (filename.length != value.length) {
			return null;
		}
		String hql = "From " + clazz.getSimpleName();
		if (filename.length > 0) {
			hql += " where ";
			for (int i = 0; i < filename.length; i++) {
				hql += filename[i] + "=?";
				if (i < filename.length - 1) {
					hql += " and ";
				}
			}
		}

		return this.baseDao.createQuery(hql, value).list();
	}

	public List<T> getAll(Class<T> clazz, String[] filename, Object[] value,
			String orderfield, String order) {
		// TODO Auto-generated method stub

		String hql = "From " + clazz.getSimpleName();
		if (null != filename && filename.length > 0) {
			hql += " where ";
			for (int i = 0; i < filename.length; i++) {
				hql += filename[i] + "=?";
				if (i < filename.length - 1) {
					hql += " and ";
				}
			}

		}

		if (null != orderfield) {
			hql += " order by " + orderfield + " " + order;
		}

		if (null != value) {
			return this.baseDao.createQuery(hql, value).list();
		} else {
			return this.baseDao.createQuery(hql).list();
		}
	}

	public T get(Class<T> clazz, String filename, Object value) {
		// TODO Auto-generated method stub
		return this.baseDao.get(clazz, filename, value);
	}

	public void update(Class<T> t, String[] field, Object[] value,
			String condition) {
		// TODO Auto-generated method stub
		this.baseDao.update(t, field, value, condition);
	}

	public void deleteAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		this.baseDao.deleteAll(clazz);
	}

	public List<T> getAll(Class<T> clazz, String field, String value, String ch) {
		// TODO Auto-generated method stub
		return baseDao.getAll(clazz, field, value, ch);
	}

	public List<T> getAll(Class<T> clazz, String[] field, Object[] value,
			String[] ch) {
		// TODO Auto-generated method stub
		return baseDao.getAll(clazz, field, value, ch);
	}

	public List<T> getAll(Class<T> clazz, String[] field, Object[] value,
			String[] ch, String order) {
		// TODO Auto-generated method stub
		return baseDao.getAll(clazz, field, value, ch, order);
	}

	public List<T> findPage(Class<T> clazz, int offset, int pageSize,
			String[] field, Object[] value, String order) {
		// TODO Auto-generated method stub
		return baseDao.findByPage(clazz, offset, pageSize, field, value, order);
	}

	public List<T> findPage(Class<T> clazz, int offset, int pageSize,
			String[] field, Object[] value, String[] ch, String order) {
		// TODO Auto-generated method stub
		return baseDao.findByPage(clazz, offset, pageSize, field, value, ch,
				order);
	}

	public List<T> findPage(Class<T> clazz, int offset, int pageSize,
			String order) {
		// TODO Auto-generated method stub
		return baseDao.findPage(clazz, offset, pageSize, order);
	}

	@Override
	public long getCount(Class<T> clazz, String[] field, Object[] value,
			String[] ch) {
		// TODO Auto-generated method stub
		return baseDao.getCount(clazz, field, value, ch);
	}

	@Override
	public boolean executeSql(List<String> sql) {
		// TODO Auto-generated method stub
		return baseDao.executeSql(sql);
	}

	@Override
	public List<T> getAll(Class<T> clazz, String headers) {
		// TODO Auto-generated method stub
		return baseDao.getAll(clazz, headers);
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria,
			int offset, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.findByCriteria(detachedCriteria, offset, pageSize);
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return baseDao.findByCriteria(detachedCriteria);
	}

	@Override
	public int findCountByCriteria(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return baseDao.findCountByCriteria(detachedCriteria);
	}

	@Override
	public List<T> hqlQuery(String hql) {
		// TODO Auto-generated method stub
		return baseDao.hqlQuery(hql);
	}

	@Override
	public List<T> findByCriteria(T t1, T t2, Object param, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.findByCriteria(t1, t2, param, offset, pageSize);
	}

	@Override
	public long getCount(T t1, T t2, Object param) {
		// TODO Auto-generated method stub
		return baseDao.getCount(t1, t2, param);
	}
	
	
	
}
