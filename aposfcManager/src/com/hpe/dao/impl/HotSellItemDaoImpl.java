/**
 * 
 */
package com.hpe.dao.impl;

import java.util.List;

import com.hpe.dao.IHotSellItemDao;
import com.hpe.pojo.HotSellItem;
import com.hpe.util.DBDataSource;
import com.hpe.util.DBUtil;

/** 
 * 类描述：
 * 作者： qinliyu 
 * 创建日期：2019年9月6日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class HotSellItemDaoImpl implements IHotSellItemDao {

	private DBUtil dbutil=new DBUtil();
	@Override
	public List<HotSellItem> getHotSell() {
		String sql="select menus.id as id,name,sum(orders.menusum) as sellnum"
				+ " from menus,orders WHERE menus.id=orders.menuid "
				+ " GROUP BY menus.id"
				+ " ORDER BY sum(orders.menusum) desc LIMIT 0,3";
		List list=null;
		try {
			list=dbutil.getQueryList(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
