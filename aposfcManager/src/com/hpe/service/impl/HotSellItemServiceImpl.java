/**
 * 
 */
package com.hpe.service.impl;

import java.util.List;

import com.hpe.dao.IHotSellItemDao;
import com.hpe.dao.impl.HotSellItemDaoImpl;
import com.hpe.pojo.HotSellItem;
import com.hpe.service.IHotSellItemService;

/** 
 * 类描述：
 * 作者： qinliyu 
 * 创建日期：2019年9月6日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class HotSellItemServiceImpl implements IHotSellItemService {

	private IHotSellItemDao hotSellItemDao=new HotSellItemDaoImpl();
	@Override
	public List<HotSellItem> getHotSell() {
		return hotSellItemDao.getHotSell();
	}

}
