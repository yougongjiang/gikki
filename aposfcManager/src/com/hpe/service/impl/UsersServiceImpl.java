/**
 * 
 */
package com.hpe.service.impl;

import com.hpe.dao.IAdminDao;
import com.hpe.dao.IUsersDao;
import com.hpe.dao.impl.AdminDaoImpl;
import com.hpe.dao.impl.UsersDaoImpl;
import com.hpe.pojo.Admin;
import com.hpe.pojo.Users;
import com.hpe.service1.UsersService;

/** 
 * 类描述：
 * 作者： shenqiutong 
 * 创建日期：2019年9月4日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class UsersServiceImpl implements UsersService {
	private IUsersDao usersDao=new UsersDaoImpl();//多态
	
	@Override
	public Users login(String name,String pwd){
		// TODO Auto-generated method stub
		return usersDao.login(name, pwd);
	}
	
	public int update(Users user) {
	
		return  usersDao.update(user);
	}

	
}