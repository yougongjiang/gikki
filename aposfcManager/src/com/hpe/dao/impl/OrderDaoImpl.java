/**
 * 
 */
package com.hpe.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.hpe.dao.IOrderDao;
import com.hpe.pojo.Order;
import com.hpe.pojo.Orders;
import com.hpe.pojo.OrdersInfo;
import com.hpe.util.DBUtil;
import com.hpe.util.Page;

/** 
 * 类描述：
 * 作者： shenqiutong 
 * 创建日期：2019年9月6日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class OrderDaoImpl implements IOrderDao {
	private DBUtil dbutil=new DBUtil();

	@Override
	public int addOrder(Order order) {
		String sql="insert into Orders(menuid,userid,menusum,times,delivery)values(?,?,?,?,?)";
		Object[] param={order.getMenuid(),order.getUserid(),order.getMenusum(),order.getTimes(),order.getDelivery()};
		int result=0;
		try {
			result = dbutil.execute(DBUtil.getConnection(), sql, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Page getOrderSearch(Page page, OrdersInfo ordersInfo) {
		String sql="select u.id as userid,realname,phone,address,m.name as menuname,menusum,price,times,delivery "
				+ " from menus as m INNER JOIN orders as o ON  m.id=o.menuid"
				+" INNER JOIN users as u ON u.id=o.userid"
				+" WHERE 1=1";
		List<Object> list=new ArrayList<Object>();
		if(ordersInfo !=null){
			//用户id
			int userid=ordersInfo.getUserid();
			if(userid>0){
				sql+=" and u.id=?";
				list.add(userid);
			}
			//菜品名称
			String menuname=ordersInfo.getMenuname();
			if(menuname!=null&&menuname.length()>0){
				sql+=" and m.name like ?";
				list.add("%"+menuname+"%");
			}
			//根据时间查询
			String date=ordersInfo.getDate();
			if(date!=null&& date.length()>0){
				sql+=" and times like ?";
				list.add("%"+date+"%");
			}
			//根据派送状态查询
			int delivery=ordersInfo.getDelivery();
			if(delivery>=0 && delivery<=1){
				sql+=" and delivery =?";
				list.add(delivery);
			}
		}
		sql+=" order by times desc";
		Page page1=null;
		page1=dbutil.getQueryPage(Orders.class, sql, list.toArray(), page);

		return null;
	}

	
	public Page query(Page page, OrdersInfo orderInfo) {
		String sql="select o.id as id, u.id AS userid,realname,phone,address,m.name as menuname,"
				+ "menusum,price,times,delivery  from orders as o "
				+ "INNER JOIN menus as m ON menuid=m.id "
				+ "INNER JOIN users as u ON userid=u.id where 1=1";
		List<Object> list=new ArrayList<Object>();
		if(orderInfo!=null){
			//获取用户编号
			int userid=orderInfo.getUserid();
			if(userid>0){
				sql+=" and u.id=?";
				list.add(userid);
			}
			//根据菜品名称查询
			String menuname=orderInfo.getMenuname();
			if(menuname!=null && menuname.length()>0){
				sql+=" and m.name like ?";
				list.add("%"+menuname+"%");
			}
			//根据下单时间模糊查询
			String date=orderInfo.getDate();
			if(date!=null && date.length()>0){
				sql+=" and times like ?";
				list.add("%"+date+"%");
			}
			//根据派送情况查询
			int delivery=orderInfo.getDelivery();
			if(delivery>=0 && delivery<=1){
				sql+=" and delivery=?";
				list.add(delivery);
			}
		}
		sql+=" order by times desc";
		Page page1=null;
		try {
			page1=dbutil.getQueryPage(Orders.class, sql, list.toArray(), page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page1;
	}

}
