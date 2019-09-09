/**
 * 
 */
package com.hpe.dao.impl;

import com.hpe.dao.IAdminDao;
import com.hpe.pojo.Admin;
import com.hpe.util.DBUtil;

/** 
 * 类描述：管理员接口实现类
 * 作者： shenqiutong 
 * 创建日期：2019年9月2日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class AdminDaoImpl implements IAdminDao {
	private DBUtil dbutil=new DBUtil();
	/* (non-Javadoc)
	 * @see com.hpe.dao.AdminDao#login(java.lang.String, java.lang.String)
	 */
	@Override
	public Admin login(String name, String pwd) {
		// TODO Auto-generated method stub
		//sql语句
		String sql="select * FROM admin where name=? and pwd=?";
		//参数列表
		Object[] param={name,pwd};
		//定义实体类 接收数据库返回结果
		Admin admin=null;
		try {
			//执行查询
			admin = (Admin)dbutil.getObject(Admin.class, sql, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回查询结果
		return admin;
	}
	/* (non-Javadoc)
	 * @see com.hpe.dao.IAdminDao#updateAdmin(com.hpe.pojo.Admin)
	 */
	@Override
	public int updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		String sql="update admin set name=?,pwd=? where id=?";
		Object[] param={admin.getName(),admin.getPwd(),admin.getId()};
		int result=0;
		try {
			result = dbutil.execute(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	/* (non-Javadoc)
	 * @see com.hpe.dao.IAdminDao#getAdminByName(java.lang.String)
	 */
	@Override
	public Admin getAdminByName(String name) {
		// TODO Auto-generated method stub
		String sql="select * from admin where name=?";
		Object[] param={name};
		Admin admin=null;
		try {
			admin = (Admin) dbutil.getObject(Admin.class, sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return admin;
	}

}
