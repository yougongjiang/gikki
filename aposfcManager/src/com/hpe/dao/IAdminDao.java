/**
 * 
 */
package com.hpe.dao;

import com.hpe.pojo.Admin;

/** 
 * 类描述：管理员dao接口
 * 作者： shenqiutong 
 * 创建日期：2019年9月2日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public interface IAdminDao {
	/**
	 * 
	 * 方法描述：管理员登陆
	 * @param name 用户名
	 * @param pwd 密码
	 * @return 管理员信息
	 */
	Admin login(String name,String pwd);
	/**
	 * 
	 * 
	 * 方法描述：管理员登陆
	 * @param admin  
	 * @return
	 */
	int updateAdmin(Admin admin);
	
	Admin getAdminByName(String name);
}//Admin login(String name,String pwd);
//int updateAdmin(Admin admin);
