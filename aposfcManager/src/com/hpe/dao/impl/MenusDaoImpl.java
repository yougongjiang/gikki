/**
 * 
 */
package com.hpe.dao.impl;

import com.hpe.dao.IMenusDao;
import com.hpe.pojo.Menus;
import com.hpe.pojo.MenusInfo;
import com.hpe.util.DBUtil;
import com.hpe.util.Page;

/** 
 * 类描述：
 * 作者： zhoukeyu 
 * 创建日期：2019年9月4日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class MenusDaoImpl implements IMenusDao {

	private DBUtil dbutil=new DBUtil();

	@Override
	public Page getMenus(Page page) {
		String sql="SELECT menu.name,imgpath,burden,types.name AS typename,"
				+"brief,price,sumd,price1,sums1 FROM menu INNER JOIN types"
				+"ON types.id=typeid";
		Page page1=null;
		page1=dbutil.getQueryPage(MenusInfo.class,sql, null, page);
		return page1;
	}
	
    public Menus getMenusByName(String name){
    	String sql="select * from menu where name =?";
    	Object[] param={name};
    	Menus menu=null;
		try{
    		menu=(Menus) dbutil.getObject(Menus.class,sql,param);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return menu;
    }
    
    public Menus getMenusById(int id){
    	String sql="select * from menu where id =?";
    	Menus menu=null;
    	try{
    		menu=(Menus) dbutil.getObject(Menus.class,sql,null);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return menu;
    }

	@Override
	public int addMenus(Menus menus) {
		String sql="INSERT INTO menus(name,burden,price,price1,brief,typeid,imgpath) values(?,?,?,?,?,?,?)";
		Object[] param={menus.getName(),menus.getBurden(),menus.getPrice(),menus.getPrice1(),menus.getBrief(),menus.getTypeid(),menus.getImgpath()};
		int result=0;
		try{
    		result=dbutil.execute(sql,param);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    		return result;
	}
	@Override
	public int deleMenus(int id) {
		String sql="DELETE FROM menus WHERE id=?";
		Object[] param={id};
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
	public int updateMenus(Menus menus) {
		String sql="update menus set name=?,burden=?,price=?,price1=?,brief=?,typeid=?,imgpath=? where id=?";
		Object[] param={ menus.getName(),menus.getBurden(),menus.getPrice(),menus.getPrice1(),menus.getBrief(),menus.getTypeid(),menus.getImgpath(),menus.getId()};
		int result=0;
		try {
			result=dbutil.execute(sql, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
