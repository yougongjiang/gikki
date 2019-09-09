/**
 * 
 */
package com.hpe.dao.impl;

import com.hpe.dao.IUsersDao;
import com.hpe.pojo.Admin;
import com.hpe.pojo.Users;
import com.hpe.util.DBUtil;

/** 
 * 类描述：
 * 作者： shenqiutong 
 * 创建日期：2019年9月4日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class UsersDaoImpl implements IUsersDao {
	private DBUtil dbutil=new DBUtil();
	
	@Override
	public  Users login(String name, String pwd) {
		//sql语句
				String sql="select * FROM users where name=? and pwd=?";
				//参数列表
				Object[] param={name,pwd};
				//定义实体类 接收数据库返回结果
				Users users=null;
				try {
					//执行查询
					users = (Users)dbutil.getObject(Users.class, sql, param);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//返回查询结果
				return users;
	}
	
	public int update(Users user) {
		String sql ="update users set name=?, pwd=?,realname=?,sex=?,age=?,card=?,address=?,phone=?,email=?,code=? where name=?";
		Object[] objects={ user.getName(), user.getPwd(), user.getRealname(), user.getSex(), user.getAge(),
				user.getCard(), user.getAddress(), user.getPhone(), user.getEmail(), user.getCode(),user.getName()};
		int temp = 0;
			try {
				temp = dbutil.execute(sql, objects);
			} catch (Exception e) {
				e.printStackTrace();
		}
		return temp;
	}
	
	@Override
	public int reg(Users users) {
		String sql="insert into users(`name`,pwd,realname,sex,age,card,address,phone,email,code) values(?,?,?,?,?,?,?,?,?,?)";
		Object[] param={users.getName(),users.getPwd(),users.getRealname(),users.getSex(),users.getAge(),
				users.getCard(),users.getAddress(),users.getPhone(),users.getEmail(),users.getCode()};
		int result=0;
		try {
			result=dbutil.execute(sql, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Users findByName(String name) {
		String sql="select * from users where name=?";
		Object[] param={name};
		Users user=null;
		try {
			user=(Users) dbutil.getObject(Users.class, sql, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}	
	

}
