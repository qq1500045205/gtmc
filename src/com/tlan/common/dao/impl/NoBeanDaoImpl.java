package com.tlan.common.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tlan.common.dao.NoBeanDao;

@Repository
public class NoBeanDaoImpl implements NoBeanDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
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
	public List createSQL(final String sql) {
		SQLQuery query = getSession().createSQLQuery(sql);
		return query.list();
	}

}
