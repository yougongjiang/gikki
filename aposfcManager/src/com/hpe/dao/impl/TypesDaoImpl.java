/**
 * 
 */
package com.hpe.dao.impl;

import java.util.List;

import com.hpe.dao.ITypesDao;
import com.hpe.pojo.Admin;
import com.hpe.pojo.Types;
import com.hpe.util.DBUtil;

/** 
 * 类描述：
 * 作者： shenqiutong 
 * 创建日期：2019年9月3日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class TypesDaoImpl implements ITypesDao {

	private DBUtil dbutil=new DBUtil();
	@Override
	public List<Types> getTypesAll() {
		String sql="select * from types";
		List list=null;
		try {
			list = dbutil.getQueryList(Types.class, sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int addTypes(Types types) {
		String sql="insert into types(name) values(?)";
		Object[] param={types.getName()};
		int result=0;
		try {
			result = dbutil.execute(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	@Override
	public Types getTypesByName(String name) {
		String sql="select * from admin where name=?";
		Object[] param={name};
		Types types=null;
		try {
			types = (Types) dbutil.getObject(Types.class, sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return types;
	}

	/* (non-Javadoc)
	 * @see com.hpe.dao.ITypesDao#updateTypes(com.hpe.pojo.Types)
	 */
	@Override
	public int updateTypes(Types types) {
		// TODO Auto-generated method stub
		String sql="update types set name=?where id=?";
		Object[] param={types.getName(),types.getId()};
		int result=0;
		try {
			result = dbutil.execute(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hpe.dao.ITypesDao#getTypesbyId(int)
	 */
	@Override
	public Types getTypesbyId(int id) {
		// TODO Auto-generated method stub
		String sql="select *from types where id=?";
		Object[] param={id};
		Types types=null;
		try {
			types = (Types) dbutil.getObject(Types.class, sql, param);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hpe.dao.ITypesDao#deleteTypes(int)
	 */
	@Override
	public int deleteTypes(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
