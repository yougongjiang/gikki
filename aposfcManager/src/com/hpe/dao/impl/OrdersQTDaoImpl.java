/**
 * 
 */
package com.hpe.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.hpe.dao.IOrdersQTDao;
import com.hpe.pojo.Orders;
import com.hpe.pojo.OrdersInfo;
import com.hpe.util.DBUtil;
import com.hpe.util.Page;

/**
 * 类描述
 * 作者：wangxiaoyu
 * 创建日期：2019年9月6日
 * 修改人：
 * 修改内容：
 * 版本号：1.0.0
 */
public class OrdersQTDaoImpl implements IOrdersQTDao{
	private DBUtil dbutil = new DBUtil();

	@Override
	public Page getOrderSearch(Page page, OrdersInfo ordersInfo) {
		String sql = "SELECT m.name as menuname,realname,phone,address,menusum,price,times,delivery"
				+ " FROM orders o INNER JOIN users u ON o.userid=u.id"
				+ " INNER JOIN menus m ON o.menuid=m.id"
				+ " WHERE 1=1";
		
		//搜素条件
		List<Object> list = new ArrayList<Object>();
		if(ordersInfo != null) {
			//用户id
			int userid = ordersInfo.getUsersid();
			if(userid > 0) {
				sql += " and u.id=?";
				list.add(userid);
			}
			
			//按菜品名查询
			String menuname = ordersInfo.getMenuname();
			if(menuname != null && menuname.length() > 0){
				sql += " and m.name like ?";
				list.add("%" + menuname + "%");
			}
			
			//按销售日期查询
			String date = ordersInfo.getDate();
			if(date != null && date.length() > 0){
				sql += " and times like ?";
				list.add("%" + date + "%");
			}
			
			//按派送情况查询
			int delivery = ordersInfo.getDelivery();
			if(delivery == 0 || delivery == 1) {
				sql += " and delivery=?";
				list.add(delivery);
			}
		}
		
		sql += " order by times desc";
		Page page1 = dbutil.getQueryPage(Orders.class, sql, list.toArray(), page);
		return page1;
	}

	
	

}
