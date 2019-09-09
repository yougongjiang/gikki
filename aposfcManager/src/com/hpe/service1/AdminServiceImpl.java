/**
 * 
 */
package com.hpe.service1;

import com.hpe.pojo.Admin;
import com.hpe.pojo.Types;

/** 
 * 类描述：管理员service接口
 * 作者： shenqiutong 
 * 创建日期：2019年9月2日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public interface AdminServiceImpl {
	Admin login(String name,String pwd);
	int updateAdmin(Admin admin);
 
}
