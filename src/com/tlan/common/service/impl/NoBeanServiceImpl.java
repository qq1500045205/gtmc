package com.tlan.common.service.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlan.common.dao.NoBeanDao;
import com.tlan.common.service.NoBeanService;

@Service("noBeanService")
public class NoBeanServiceImpl implements NoBeanService {
	@Autowired
	private NoBeanDao baseDao;
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
		return baseDao.createSQL(sql);
	}

}
