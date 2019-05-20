package com.tlan.common.dao.impl;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlan.common.dao.IBaseDao;
import com.tlan.common.model.WxProject;
import com.tlan.common.utils.ReflectUtils;

/**
 * 扩展HibernateDaoSupport的泛型基类
 * 
 * @param <T>
 *            实体类型
 */
@Repository
public class IBaseDaoImpl<T> implements IBaseDao<T> {

	private Log log = LogFactory.getLog(IBaseDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	protected Class<T> entityClass;

	/**
	 * 在构造函数中利用反射机制获得参数T的具体类
	 */
	public IBaseDaoImpl() {
		entityClass = ReflectUtils.getClassGenricType(getClass());
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 根据实体类与ID获得对象
	 * 
	 * @param clazz
	 *            实体类
	 * @param id
	 *            主键ID
	 */
	public <X> X get(final Class<X> clazz, final Serializable id) {
		try {
			log.info("get " + clazz.getSimpleName() + " id=" + id);
			return (X) getSession().get(clazz, id);
		} catch (Exception e) {
			log.error("get " + clazz.getSimpleName() + " error:id=" + id + ",message=" + e.getMessage());
			return null;
		}
	}

	/**
	 * 根据id获得对象
	 * 
	 * @param id
	 *            主键ID
	 */

	public T get(Serializable id) {
		try {
			log.info("get id=" + id);
			return get(entityClass, id);
		} catch (Exception e) {
			log.error("get error:id=" + id + ",message=" + e.getMessage());
			return null;
		}
	}

	/**
	 * 删除对象
	 * 
	 * @param entity
	 *            实体类
	 */
	public void delete(final Object entity) {
		try {
			log.info("delete " + entity);
			getSession().delete(entity);
		} catch (Exception e) {
			log.error("delete error message :" + e.getMessage());
		}
	}

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 *            主键ID
	 */
	public void delete(final Serializable id) {
		try {
			log.info("delete id=" + id);
			delete(get(id));
		} catch (Exception e) {
			log.error("delete error id: " + id);
			log.error("delete error message :" + e.getMessage());
		}
	}

	/**
	 * 根据实体类与ID删除对象
	 * 
	 * @param clazz
	 *            实体类
	 * @param id
	 *            主键ID
	 */
	public void delete(final Class clazz, final Serializable id) {
		delete(get(clazz, id));
	}

	/**
	 * 根据条件删除
	 * 
	 * @param clazz
	 *            实体类
	 * @param filedname
	 * @param value
	 */
	public void delete(Class<T> clazz, String fieldname, Object value) {
		// TODO Auto-generated method stub
		try {
			log.info("delete fieldname=" + fieldname + ",value=" + value);

			String sql = "delete from " + clazz.getSimpleName() + " where " + fieldname + " = '" + value + "'";
			getSession().createQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			log.error("delete error message :" + e.getMessage());
		}
	}

	/**
	 * 根据多条件删除
	 * 
	 * @param clazz
	 *            实体类
	 * @param filedname
	 * @param value
	 */
	public void delete(Class<T> clazz, String[] fieldname, Object[] value) {
		// TODO Auto-generated method stub

		try {
			log.info("delete " + clazz.getSimpleName() + "," + Arrays.toString(fieldname) + "="
					+ Arrays.toString(value));
			String hql = "delete from " + clazz.getSimpleName() + " where ";
			for (int i = 0; i < value.length; i++) {
				hql += fieldname[i] + "= '" + value[i] + "'";
				if (i < value.length - 1) {
					hql += " and ";
				}
			}
			log.info("delete sql:" + hql);
			getSession().createQuery(hql).executeUpdate();
		} catch (HibernateException e) {
			log.error("delete " + clazz.getSimpleName() + " error:" + Arrays.toString(fieldname) + "="
					+ Arrays.toString(value));
		}
	}

	/**
	 * 
	 * 保存对象
	 * 
	 * @param entity
	 *            保存的实体对象
	 */
	public void save(final Object entity) {
		try {
			log.info("save " + entity);
			getSession().save(entity);
		} catch (Exception e) {
			log.error("save error:" + e.getMessage());
		}
	}

	/**
	 * 
	 * 保存对象
	 * 
	 * @param entity
	 *            保存的实体对象
	 */
	public void saveList(final List<T> list) {
		try {
			log.info("saveList : " + Arrays.toString(list.toArray()));
			Session session = getSession();

			for (Object object : list) {
				session.merge(object);
			}
			session.flush();
			session.clear();
			log.info("saveList success size : " + list.size());
		} catch (HibernateException e) {
			log.error("saveList error  : " + e.getMessage());
		}
	}

	/**
	 * 
	 * 保存对象
	 * 
	 * @param entity
	 *            保存的实体对象
	 */
	public void saveOrUpdate(final Object entity) {
		try {
			log.info("saveOrUpdate " + entity);
			getSession().merge(entity);
			log.info("saveOrUpdate success");
		} catch (Exception e) {
			log.error("saveOrUpdate error:" + e.getMessage());
		}
	}

	/**
	 * 
	 * 更新对象
	 * 
	 * @param entity
	 *            更新的实体对象
	 */
	public void update(final Object entity) {
		try {
			log.info("update " + entity);
			getSession().update(entity);
			log.info("update success");
		} catch (Exception e) {
			log.error("update error:" + e.getMessage());
		}
	}

	/**
	 * 获取所有数据
	 * 
	 * @param entityClass
	 *            参数T的反射类型
	 */
	public <X> List<X> getAll(final Class<X> entityClass) {
		try {
			log.info("getAll " + entityClass.getSimpleName());
			return createCriteria(entityClass).list();
		} catch (HibernateException e) {
			log.error("getAll " + entityClass.getSimpleName() + " error:" + e.getMessage());
			return null;
		}
	}

	/**
	 * 获取所有数据条数
	 * 
	 * @param entityClass
	 *            参数T的反射类型
	 */
	public long getCount(final Class<T> entityClass) {
		try {
			log.info("getCount " + entityClass.getSimpleName());
			return createCriteria(entityClass).list().size();
		} catch (HibernateException e) {
			log.error("getCount " + entityClass.getSimpleName() + ":" + e.getMessage());
			return 0;
		}
	}

	/**
	 * 
	 * 根据条件获取数据
	 * 
	 * @param criterions
	 *            数量可变的Criterion
	 */
	public List<T> query(final Criterion... criterions) {
		try {
			log.info("query " + criterions);
			return createCriteria(criterions).list();
		} catch (HibernateException e) {
			log.error("query error : " + e.getMessage());
			return null;
		}
	}

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
	public Query createQuery(final String hql, final Object... values) {
		try {
			log.info("createQuery hql:" + hql + ",values:" + Arrays.toString(values));
			Query query = getSession().createQuery(hql);
			int j = values.length;
			for (int i = 0; i < j; i++)
				query.setParameter(i, values[i]);
			return query;
		} catch (HibernateException e) {
			log.error("createQuery error:" + e.getMessage());
			return null;
		}
	}

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
	public SQLQuery createSQLQuery(final String sql, final Object... values) {
		try {
			log.info("createSQLQuery sql:" + sql + ",values=" + Arrays.toString(values));
			SQLQuery query = getSession().createSQLQuery(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			return query;
		} catch (HibernateException e) {
			log.error("createSQLQuery error:" + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * 根据类型创建查询对象
	 * 
	 * @param clazz
	 *            类型
	 */
	public Criteria createCriteria(final Class clazz) {
		try {
			log.info("createCriteria:" + clazz.getSimpleName());
			return getSession().createCriteria(clazz);
		} catch (Exception e) {
			log.error("createCriteria error:" + e.getMessage());
			return null;
		}
	}

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
	public Criteria createCriteria(final Class clazz, final Criterion... criterions) {
		try {
			log.info("createCriteria " + clazz.getSimpleName() + ",criterions=" + criterions);
			Criteria criteria = getSession().createCriteria(clazz);
			for (Criterion c : criterions) {
				criteria.add(c);
			}
			return criteria;
		} catch (Exception e) {
			log.error("createCriteria error:" + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * 对象化查询
	 * 
	 * @param criterions
	 *            数量可变的Criterion
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		try {
			log.info("createCriteria " + criterions);
			return createCriteria(entityClass, criterions);
		} catch (Exception e) {
			log.error("createCriteria error :" + e.getMessage());
			return null;
		}
	}

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
	public <X> List<X> findByPage(final Class<X> entityClass, final int offset, final int pageSize) {
		try {
			log.info("findByPage " + entityClass.getSimpleName() + ",offset=" + offset + ",pageSize=" + pageSize);
			return createCriteria(entityClass).setFirstResult(offset).setMaxResults(pageSize).list();
		} catch (HibernateException e) {
			log.error("findByPage " + entityClass.getSimpleName() + " error :" + e.getMessage());
			return null;
		}
	}

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
	public List<T> findByPage(final String hql, final int offset, final int pageSize) {

		try {
			log.info("findByPage hql=" + hql + ",offset=" + offset + ",pageSize=" + pageSize);
			return createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
		} catch (HibernateException e) {
			log.error("findByPage error :" + e.getMessage());
			return null;
		}
	}

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
	public List<T> findByPage(final String hql, final String value, final int offset, final int pageSize) {

		try {
			log.info("findByPage hql=" + hql + ",value=" + value + ",offset=" + offset + ",pageSize=" + pageSize);
			return createQuery(hql).setParameter(0, value).setFirstResult(offset).setMaxResults(pageSize).list();
		} catch (HibernateException e) {
			log.error("findByPage error :" + e.getMessage());
			return null;
		}
	}

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
	public List<T> findByPage(final String hql, final String[] value, final int offset, final int pageSize) {

		try {
			log.info("findByPage hql=" + hql + ",value=" + Arrays.toString(value) + ",offset=" + offset + ",pageSize="
					+ pageSize);
			Query query = createQuery(hql);
			for (int i = 0; i < value.length; i++) {
				query.setParameter(i, value[i]);
			}
			return query.setFirstResult(offset).setMaxResults(pageSize).list();
		} catch (HibernateException e) {
			log.error("findByPage error :" + e.getMessage());
			return null;
		}
	}

	/**
	 * 根据条件获取对象
	 * 
	 * @param clazz
	 * @param filename
	 * @param value
	 * 
	 */
	public <X> X get(Class<T> clazz, String filename, Object value) {

		try {
			log.info("get " + clazz.getSimpleName() + ",filename=" + filename + ",value=" + value);
			String hql = "From " + clazz.getSimpleName() + " where " + filename + " = '" + value + "'";
			List list = getSession().createQuery(hql).list();
			if (list.size() > 0) {
				return (X) list.get(0);
			}
			return null;
		} catch (HibernateException e) {
			log.error("get " + clazz.getSimpleName() + " error:" + e.getMessage());
			return null;
		}
	}

	/**
	 * 指定更新对象
	 * 
	 * @param t
	 * @param field
	 * @param name
	 * @param condition
	 */
	public void update(Class<T> clazz, String[] field, Object[] value, String condition) {
		// TODO Auto-generated method stub
		try {
			log.info("update " + clazz.getSimpleName() + ",field=" + Arrays.toString(field) + ",value="
					+ Arrays.toString(value));
			if (field.length == value.length) {
				String hql = " update " + clazz.getSimpleName() + " set ";
				for (int i = 0; i < value.length; i++) {
					if (value[i] instanceof String) {
						hql += field[i] + "='" + value[i] + "'";
					} else {
						hql += field[i] + "=" + value[i];
					}

					if (i < value.length - 1) {
						hql += ",";
					}
				}

				if (condition != null) {
					hql += " where " + condition;
				}
				getSession().createQuery(hql).executeUpdate();
			}
		} catch (HibernateException e) {
			log.error("update " + clazz.getSimpleName() + " error " + e.getMessage());
		}

	}

	public void deleteAll(Class<T> clazz) {
		try {
			log.info("deleteAll " + clazz.getSimpleName());
			getSession().createQuery("delete from " + clazz.getSimpleName()).executeUpdate();
		} catch (HibernateException e) {
			log.error("deleteAll error : " + e.getMessage());
		}
	}

	public <X> List<X> findByPage(Class<X> entityClass, int offset, int pageSize, String[] field, Object[] value) {
		try {
			log.info("findByPage " + entityClass.getSimpleName() + ",offset=" + offset + ",pageSize=" + pageSize
					+ ",field=" + Arrays.toString(field) + ",value=" + Arrays.toString(value));
			if (field.length != value.length) {
				return null;
			}

			String hql = "From " + entityClass.getSimpleName() + " where ";
			for (int i = 0; i < value.length; i++) {
				hql += field[i] + "= '" + value[i] + "'";
				if (i < value.length - 1) {
					hql += " and ";
				}
			}
			return createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
		} catch (HibernateException e) {
			log.error("findByPage " + entityClass.getSimpleName() + " error " + e.getMessage());
			return null;
		}
	}

	public List<T> findByPage(Class<T> clazz, int offset, int pageSize, String[] field, Object[] value, String[] ch,
			String order) {

		log.info("findByPage " + clazz.getSimpleName() + ",offset=" + offset + ",pageSize=" + pageSize + ",field="
				+ Arrays.toString(field) + ",value=" + Arrays.toString(value) + ",ch=" + Arrays.toString(ch) + ",order="
				+ order);

		if (field.length != value.length) {
			return null;
		}

		try {
			String hql = "From " + clazz.getSimpleName() + " where ";
			for (int i = 0; i < value.length; i++) {

				if (IS.equalsIgnoreCase(ch[i])) {
					hql += field[i] + " is " + value[i] + "";
				} else if (IN.equalsIgnoreCase(ch[i])) {
					String[] ary = ((String) value[i]).split(",");
					String tmp = "";
					for (int j = 0; j < ary.length; j++) {
						tmp += "'" + ary[j] + "'";
						if (j < ary.length - 1) {
							tmp += ",";
						}
					}
					hql += field[i] + " in (" + tmp + ")";
				} else if (CONTAINS.equalsIgnoreCase(ch[i])) {
					hql += " CONTAINS (" + field[i] + ",'" + value[i] + "')";
				} else {
					hql += field[i] + "= '" + value[i] + "'";
				}

				if (i < value.length - 1) {
					hql += " and ";
				}
			}
			hql += " order by " + order;
			return getSession().createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
		} catch (HibernateException e) {
			log.error("findByPage " + clazz.getSimpleName() + " error " + e.getMessage());
			return null;
		}
	}

	public List<T> findByPage(Class<T> clazz, int offset, int pageSize, String[] field, Object[] value, String order) {

		log.info("findByPage " + clazz.getSimpleName() + ",offset=" + offset + ",pageSize=" + pageSize + ",field="
				+ Arrays.toString(field) + ",value=" + Arrays.toString(value));

		if (field.length != value.length) {
			return null;
		}

		try {
			String hql = "From " + clazz.getSimpleName() + " where ";
			for (int i = 0; i < value.length; i++) {
				hql += field[i] + "= '" + value[i] + "'";
				if (i < value.length - 1) {
					hql += " and ";
				}
			}
			hql += " order by " + order;
			return getSession().createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
		} catch (HibernateException e) {
			log.error("findByPage " + clazz.getSimpleName() + " error " + e.getMessage());
			return null;
		}
	}

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
	public List<T> getAll(Class<T> clazz, String field, String value, String ch) {

		try {

			log.info("getAll " + clazz.getSimpleName() + ",field=" + field + ",value=" + value + ",ch=" + ch);

			String hql = "From " + clazz.getSimpleName() + " where " + field + " ";
			if ("in".equals(ch)) {
				hql += " in (" + value + ")";
			} else if ("=".equals(ch)) {
				hql += "='" + value + "'";
			} else if ("like_".equals(ch)) {
				hql += "like'" + value + "_'";
			} else if ("like%".equals(ch)) {
				hql += "like'%" + value + "'";
			} else if ("like%%".equals(ch)) {
				hql += "like'%" + value + "%'";
			} else if ("is".equalsIgnoreCase(ch)) {
				hql += "is " + value;
			} else {
				return null;
			}
			log.info("hql:" + hql);

			return getSession().createQuery(hql).list();
		} catch (HibernateException e) {
			log.error("getAll " + clazz.getSimpleName() + " error :" + e.getMessage());
			return null;
		}
	}

	public List<T> getAll(Class<T> clazz, String headers) {
		try {
			log.info("get All " + clazz.getSimpleName() + ",headers=" + headers);
			String hql = "select " + headers + " From " + clazz.getSimpleName();
			log.info("hql:" + hql);
			return getSession().createQuery(hql).list();
		} catch (HibernateException e) {
			log.error("get All " + clazz.getSimpleName() + " error : " + e.getMessage());
			return null;
		}
	}

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
	public List<T> getAll(Class<T> clazz, String[] fields, Object[] values, String[] chs) {
		log.info("get All " + clazz.getSimpleName() + ",fields=" + Arrays.toString(fields) + ",values="
				+ Arrays.toString(values) + ",chs=" + Arrays.toString(chs));
		try {
			String hql = "From " + clazz.getSimpleName() + " where ";
			for (int i = 0; i < fields.length; i++) {
				String field = fields[i];
				hql += field + " ";
				if ("in".equals(chs[i])) {
					hql += " in (" + values[i] + ")";
				} else if ("=".equals(chs[i])) {
					hql += "='" + values[i] + "'";
				} else if ("like_".equals(chs[i])) {
					hql += "like'" + values[i] + "_'";
				} else if ("like%".equals(chs[i])) {
					hql += "like'%" + values[i] + "'";
				} else if ("like%%".equals(chs[i])) {
					hql += "like'%" + values[i] + "%'";
				} else if ("is".equalsIgnoreCase(chs[i])) {
					hql += "is " + values[i];
				} else {

				}
				if (i < fields.length - 1) {
					hql += " and ";
				}
			}
			log.info("hql:" + hql);
			return getSession().createQuery(hql).list();
		} catch (HibernateException e) {
			log.error("get All " + clazz.getSimpleName() + " error : " + e.getMessage());
			return null;
		}
	}

	public List<T> getAll(Class<T> clazz, String[] fields, Object[] values, String[] chs, String order) {
		log.info("get All " + clazz.getSimpleName() + ",fields=" + Arrays.toString(fields) + ",values="
				+ Arrays.toString(values) + ",chs=" + Arrays.toString(chs) + ",order" + order);

		try {
			String hql = "From " + clazz.getSimpleName();
			if (fields.length > 0)
				hql += " where ";
			for (int i = 0; i < fields.length; i++) {
				String field = fields[i];
				hql += field + " ";
				if ("in".equals(chs[i])) {
					hql += " in (" + values[i] + ")";
				} else if ("=".equals(chs[i])) {
					hql += "='" + values[i] + "'";
				} else if ("like_".equals(chs[i])) {
					hql += "like'" + values[i] + "_'";
				} else if ("like%".equals(chs[i])) {
					hql += "like'%" + values[i] + "'";
				} else if ("like%%".equals(chs[i])) {
					hql += "like'%" + values[i] + "%'";
				} else if ("is".equalsIgnoreCase(chs[i])) {
					hql += "is " + values[i];
				} else {

				}
				if (i < fields.length - 1) {
					hql += " and ";
				}
			}
			hql += " order by  " + order;
			log.info("hql:" + hql);
			return getSession().createQuery(hql).list();
		} catch (HibernateException e) {
			log.error("get All " + clazz.getSimpleName() + " error : " + e.getMessage());
			return null;
		}
	}

	public List<T> findPage(Class<T> clazz, int offset, int pageSize, String order) {
		try {
			log.info("findPage " + clazz.getSimpleName() + ",offset=" + offset + ",pageSize=" + pageSize + ",order"
					+ order);
			String hql = "From " + clazz.getSimpleName() + " order by " + order;
			log.info("hql:" + hql);
			return getSession().createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
		} catch (HibernateException e) {
			log.error("findPage " + clazz.getSimpleName() + " error : " + e.getMessage());
			return null;
		}
	}

	@Override
	public long getCount(Class<T> clazz, String[] field, Object[] value, String[] ch) {
		log.info("get All " + clazz.getSimpleName() + ",fields=" + Arrays.toString(field) + ",values="
				+ Arrays.toString(value) + ",chs=" + Arrays.toString(ch));
		try {
			String sql = "select count(*) from " + clazz.getSimpleName();
			if (null != field) {
				sql += " where ";
				for (int i = 0; i < field.length; i++) {
					if (IS.equalsIgnoreCase(ch[i])) {
						sql += field[i] + " is " + value[i] + "";
					} else if (CONTAINS.equalsIgnoreCase(ch[i])) {
						sql += " CONTAINS (" + field[i] + ",'" + value[i] + "')";
					} else if (IN.equalsIgnoreCase(ch[i])) {
						String[] ary = ((String) value[i]).split(",");
						String tmp = "";
						for (int j = 0; j < ary.length; j++) {
							tmp += "'" + ary[j] + "'";
							if (j < ary.length - 1) {
								tmp += ",";
							}
						}
						sql += field[i] + " in (" + tmp + ")";
					} else {
						sql += field[i] + "= '" + value[i] + "'";
					}
					if (i < field.length - 1) {
						sql += " and ";
					}
				}
			}
			log.info("sql:" + sql);
			return ((Number) getSession().createQuery(sql).uniqueResult()).intValue();
		} catch (HibernateException e) {
			log.error("getCount " + clazz.getSimpleName() + " error : " + e.getMessage());
			return 0;
		}
	}

	@Override
	public boolean executeSql(List<String> sqlList) {
		// TODO Auto-generated method stub
		log.info("executeSql sqlList : " + sqlList.size());
		Session session = getSession();
		try {
			for (String sql : sqlList) {
				session.createSQLQuery(sql).executeUpdate();
			}
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			log.error("executeSql error : " + e.getMessage());
			return false;
		}
	}

	/****** DetachedCriteria ********/
	/**
	 * 分页查询
	 * 
	 * @Title: findByCriteria
	 * @Description: TODO
	 * @param @param
	 *            detachedCriteria
	 * @param @param
	 *            first
	 * @param @param
	 *            max
	 * @param @return
	 * @return List<T>
	 * @throws @Date
	 *             2014年3月3日 上午10:23:04
	 */
	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria, int offset, int pageSize) {
		log.info("findByCriteria offset=" + offset + ",pageSize:" + pageSize);
		try {
			Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setFirstResult(offset);
			criteria.setMaxResults(pageSize);
			return criteria.list();
		} catch (HibernateException e) {
			log.error("findByCriteria error :" + e.getMessage());
			return null;
		}
	}

	/**
	 * 查询
	 * 
	 * @Title: findByCriteria
	 * @Description: TODO
	 * @param @param
	 *            detachedCriteria
	 * @param @param
	 *            first
	 * @param @param
	 *            max
	 * @param @return
	 * @return List<T>
	 * @throws @Date
	 *             2014年3月3日 上午10:23:04
	 */
	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		log.info("findByCriteria");
		try {
			Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria.list();
		} catch (HibernateException e) {
			log.error("findByCriteria error :" + e.getMessage());
			return null;
		}
	}

	/**
	 * 总数查询
	 * 
	 * @Title: findCountByCriteria
	 * @Description: TODO
	 * @param @param
	 *            detachedCriteria
	 * @param @return
	 * @return int
	 * @throws @Date
	 *             2014年3月3日 上午10:23:22
	 */
	@Override
	public int findCountByCriteria(DetachedCriteria detachedCriteria) {
		log.info("findCountByCriteria");
		try {
			Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
			criteria.setProjection(Projections.rowCount());
			int count = Integer.valueOf(criteria.uniqueResult().toString());
			// Clean count
			criteria.setProjection(null);
			return count;
		} catch (HibernateException e) {
			log.error("findCountByCriteria error :" + e.getMessage());
			return 0;
		}
	}

	/**
	 * 
	 * HQL方式查询
	 * 
	 * @param hql
	 *            符合HQL语法的查询语句
	 */
	public List<T> hqlQuery(String hql) {
		// TODO Auto-generated method stub
		log.info("hqlQuery");
		try {
			Query query = getSession().createQuery(hql);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			log.error("hqlQuery error :" + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @Title: getDetachedCriteria 使用离线查询对象查询
	 *  @Description: 
	 *  @param t1 查询对象1 
	 *  @param t2 查询条件2 
	 *  @param param 额外参数
	 *  @return DetachedCriteria 
	 *  @throws
	 */
	public List<T> getDetachedCriteria(T t1, T t2, Object param, int offset, int pageSize) {
		if (t1 != null) {
			if (t1 instanceof WxProject) {
				WxProject wxProject1 = (WxProject) t1;
				WxProject wxProject2 = (WxProject) t2;
				try {
					DetachedCriteria detachedCriteria = DetachedCriteria.forClass(WxProject.class);
					Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
					criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
					criteria.add(Restrictions.like("projectGuid", wxProject1.getProjectGuid() + "%"));
					//判断是否为空
					if(StringUtils.isNotBlank(wxProject1.getCreatedOn())) {
						criteria.add(Restrictions.between("createdOn",wxProject1.getCreatedOn(),wxProject2.getCreatedOn()));
					}
					
					criteria.setFirstResult(offset);
					criteria.setMaxResults(pageSize);
					return criteria.list();
				} catch (HibernateException e) {
					log.error("findByCriteria error :" + e.getMessage());
					return null;
				}
				/*
				 * DetachedCriteria detachedCriteria=DetachedCriteria.forClass(WxProject.class);
				 * if(StringUtils.isNotBlank(wxProject.getProjectGuid())) { //只匹配开头的
				 * detachedCriteria.add(Restrictions.like("projectGuid",
				 * wxProject.getProjectGuid(), MatchMode.END));
				 * 
				 * } return detachedCriteria;
				 */
			}
		}
		return null;
	}
	
	/*private Date getDate(String source) {
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begin=df.format(begintime);
		String end=df.format(endtime);
	}
*/
	/**
	 * 
	 * @Title: findByCriteria @Description: @param t1 查询对象1 @param t2 查询对象2 @param
	 *         param 参数 @param offset 页数 @param pageSize 页面大小 @return
	 *         List<T> @throws
	 */
	public List<T> findByCriteria(T t1, T t2, Object param, int offset, int pageSize) {
		return getDetachedCriteria(t1, t2, param, offset, pageSize);

	}

	@Override
	public long getCount(T t1, T t2, Object param) {
		if (t1 != null) {
			if (t1 instanceof WxProject) {
				WxProject wxProject1 = (WxProject) t1;
				WxProject wxProject2 = (WxProject) t2;
				try {
					DetachedCriteria detachedCriteria = DetachedCriteria.forClass(WxProject.class);
					Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
					criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
					criteria.add(Restrictions.like("projectGuid", wxProject1.getProjectGuid() + "%"));
					//判断是否为空
					if(StringUtils.isNotBlank(wxProject1.getCreatedOn())) {
						criteria.add(Restrictions.between("createdOn",wxProject1.getCreatedOn(),wxProject2.getCreatedOn()));
					}
					// 设置页码 
					criteria.setProjection(Projections.rowCount());
					Long num = (Long) criteria.uniqueResult();
					return num;
				} catch (HibernateException e) {
					log.error("findByCriteria error :" + e.getMessage());
					return 0;
				}

			}
		}
		return 0;
	}

	/*
	 * @Override public List<T> findByCriteria(Class<T> clazz1, Class<T> clazz2, int
	 * offset, int pageSize) {
	 * 
	 * return null; }
	 */
}
