/**
 * 
 */
package com.hpe.service.impl;

import com.hpe.dao.IOrdersQTDao;
import com.hpe.dao.impl.OrdersQTDaoImpl;
import com.hpe.pojo.OrdersInfo;
import com.hpe.service.IOrdersQTService;
import com.hpe.util.Page;

/**
 * 类描述
 * 作者：wangxiaoyu
 * 创建日期：2019年9月6日
 * 修改人：
 * 修改内容：
 * 版本号：1.0.0
 */
public class OrdersQTServiceImpl implements IOrdersQTService {
	private IOrdersQTDao ordersDao = newOrdersQTDaoImpl();

	@Override
	public Page getOrderSearch(Page page, OrdersInfo ordersInfo) {
		
		return ordersDao.getOrderSearch(page, ordersInfo);
	}

}
