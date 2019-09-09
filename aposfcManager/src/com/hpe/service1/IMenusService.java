/**
 * 
 */
package com.hpe.service1;

import com.hpe.pojo.Menus;
import com.hpe.util.Page;

/** 
 * 类描述：
 * 作者： shenqiutong 
 * 创建日期：2019年9月5日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public interface IMenusService {
	int addMenu(Menus menus);
	Page getMenu(Page page);
	Menus getMenuById(int id);
	/**
	 * 
	 * 方法描述：删除菜品
	 * @param menus
	 * @return 影响行数
	 */
	int deleMenus(int id);
	
	/**
	 * 
	 * 方法描述：修改菜品
	 * @param id
	 * @return 影响行数
	 */
	int updateMenus(Menus menus);
	
	
	
}
