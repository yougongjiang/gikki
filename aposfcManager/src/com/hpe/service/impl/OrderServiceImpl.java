/**
 * 
 */
package com.hpe.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hpe.dao.IOrderDao;
import com.hpe.dao.impl.OrderDaoImpl;
import com.hpe.pojo.Order;
import com.hpe.pojo.OrdersInfo;
import com.hpe.pojo.ShoppingCar;
import com.hpe.service1.IOrderService;
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
public class OrderServiceImpl implements IOrderService {

	private IOrderDao orderDao=new OrderDaoImpl();
	@Override
	public boolean addOrder(int userId, List<ShoppingCar> list) throws Exception {
		boolean flag = false;
		try {
			
			//开启事务
			DBUtil.beginTranscation();
			//遍历添加
			for (ShoppingCar car : list) {
				Order order = new Order();
				order.setUserid(userId);
				order.setMenuid(car.getMenuid());
				order.setMenusum(String.valueOf(car.getSum()));//或者给car.getSum()加双引号变string
				order.setDelivery("0");//设置为默认未配送
				order.setTimes(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
				orderDao.addOrder(order);
			}
			//提交事务
			DBUtil.endTranscation();
			flag = true;
		} catch (Exception e) {
			try {
				// 回滚事务
				DBUtil.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}finally{
			//关闭连接
			try {
				DBUtil.closeConn();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	
	public Page getOrderSearch(Page page, OrdersInfo orderInfo) {
		return orderDao.query(page, orderInfo);
	}

}
