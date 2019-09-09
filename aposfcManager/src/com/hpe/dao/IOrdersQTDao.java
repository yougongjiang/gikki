/**
 * 
 */
package com.hpe.dao;

import com.hpe.pojo.OrdersQTInfo;
import com.hpe.util.Page;

/**
 * 类描述：订单查询
 * 作者：wangxiaoyu
 * 创建日期：2019年9月5日
 * 修改人：
 * 修改内容：
 * 版本号：1.0.0
 */
public interface IOrdersQTDao {

	/**
	 * 
	 * 方法描述：多条件搜索
	 * @param page
	 * @param orderInfo
	 * @return 
	 */
	Page getOrderSearch(Page page,OrdersInfo ordersInfo);
	
}
