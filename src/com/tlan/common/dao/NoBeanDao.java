package com.tlan.common.dao;

import java.util.List;

import org.hibernate.Session;

/**
 * 扩展HibernateDaoSupport的泛型基类
 * 
 * 实体类型
 */
public interface NoBeanDao {

	public Session getSession();

	public List createSQL(String sql);

}
