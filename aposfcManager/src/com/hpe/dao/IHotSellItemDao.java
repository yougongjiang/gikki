/**
 * 
 */
package com.hpe.dao;

import java.util.List;

import com.hpe.pojo.HotSellItem;

/** 
 * 类描述：
 * 作者： qinliyu 
 * 创建日期：2019年9月6日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public interface IHotSellItemDao {
	/**
	 * 
	 * 方法描述：查询所有
	 * @return 所有类别信息
	 */
	List<HotSellItem> getHotSell();
}
