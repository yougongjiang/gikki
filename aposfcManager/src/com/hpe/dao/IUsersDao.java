/**
 * 
 */
package com.hpe.dao;

import com.hpe.pojo.Admin;
import com.hpe.pojo.Users;

/** 
 * 类描述：
 * 作者： shenqiutong 
 * 创建日期：2019年9月4日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public interface IUsersDao {
	Users login(String name,String pwd);
	/**
	 * 
	 * 方法描述：用户中心，修改用户信息
	 * @param user
	 * @return
	 */
	int update(Users user);
	/**
	 * 
	 * 方法描述：注册
	 * @param users
	 * @return 影响行数
	 */
	int reg(Users users);
	/**
	 * 
	 * 方法描述：通过name查询
	 * @param name
	 * @return 用户信息
	 */
	Users findByName(String name);
}
