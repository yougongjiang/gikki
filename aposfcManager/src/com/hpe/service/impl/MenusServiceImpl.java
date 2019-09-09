/**
 * 
 */
package com.hpe.service.impl;

import com.hpe.dao.IMenusDao;
import com.hpe.dao.impl.MenusDaoImpl;
import com.hpe.pojo.Menus;
import com.hpe.service1.IMenusService;
import com.hpe.util.Page;

/** 
 * 类描述：
 * 作者： shenqiutong 
 * 创建日期：2019年9月4日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class MenusServiceImpl implements IMenusService{
	private IMenusDao menusDao=new MenusDaoImpl();
	
	@Override
	public int addMenu(Menus menus) {
		Menus menu=menusDao.getMenusByName(menus.getName());
		if(menu!=null){
			return -1;
		}
		return menusDao.addMenus(menus);
	}
	
	@Override
	public Page getMenu(Page page) {
		// TODO Auto-generated method stub
		return menusDao.getMenus(page);
	}
	@Override
	
	public Menus getMenuById(int id){
		return menusDao.getMenusById(id);
	}
}
