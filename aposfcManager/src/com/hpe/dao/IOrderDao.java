/**
 * 
 */
package com.hpe.dao;

import com.hpe.pojo.Order;
import com.hpe.pojo.OrdersInfo;
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
public interface IOrderDao {
	/**
	 * 
	 * 方法描述：添加订单
	 * @param order
	 * @return影响的行数
	 */
	int addOrder(Order order);
	/**
	 * 
	 * 方法描述：多条件查询
	 * @param page分页信息
	 * @param ordersInfo查询条件
	 * @return订单信息
	 */
	
	Page getOrderSearch(Page page,OrdersInfo ordersInfo);
	//分布式查询订单
	Page query(Page page,OrdersInfo orderInfo);
}
