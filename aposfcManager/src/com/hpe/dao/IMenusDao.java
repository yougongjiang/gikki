/**
 * 
 */
package com.hpe.dao;

import com.hpe.pojo.Menus;
import com.hpe.util.Page;

/** 
 * 类描述：
 * 作者：shenqiutong 
 * 创建日期：2019年9月4日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public interface IMenusDao {

	/**
	 * 
	 * 方法描述：分页查询
	 * @param page
	 * @return  分页后的数据
	 */
	Page getMenus(Page page);
	/**
	 * 
	 * 方法描述：添加
	 * @param  menus
	 * @return  影响的行数
	 */
	int addMenus(Menus menus);
	/**
	 * 
	 * 方法描述：根据name查询
	 * @param name
	 * @return 菜品信息
	 */
	Menus getMenusByName(String name);
	
	Menus getMenusById(int id);
}
