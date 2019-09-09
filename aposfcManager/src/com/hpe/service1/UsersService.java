/**
 * 
 */
package com.hpe.service1;

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
public interface UsersService {
	Users login(String name,String pwd);
	int update(Users user);
	/**
	 * 
	 * 方法描述：注册
	 * @param users
	 * @return 影响行数
	 */
	int reg(Users users);
}
