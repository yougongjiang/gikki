/**
 * 
 */
package com.hpe.service1;

import java.util.List;

import com.hpe.pojo.OrdersInfo;
import com.hpe.pojo.ShoppingCar;
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
public interface IOrderService {
	boolean addOrder(int userId,List<ShoppingCar> list)throws Exception;
	//分布式查询订单
	Page getOrderSearch(Page page,OrdersInfo orderInfo);

}
	@Override
	public int deleteOrder(int id) {
		// TODO Auto-generated method stub
		return orderDao.deleteOrder(id);
	}

	
	@Override
	public int changeDelivery(int id) {
		// TODO Auto-generated method stub
		return orderDao.changeDelivery(id);
	}


	@Override
	public List<GetMoneyAll> getMoneyAll(String times) {
		// TODO Auto-generated method stub
		return orderDao.getMoneyAll(times);
	}
