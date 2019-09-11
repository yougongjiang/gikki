/**
 * 
 */
package com.hpe.dao;

import java.util.List;

import com.hpe.pojo.Notice;

/** 
 * 类描述：
 * 作者： qinliyu zhoukeyu wangxiaoyu
 * 创建日期：2019年9月6日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public interface INoticeDao {
	
	/**
	 * 
	 * 方法描述：后台查询所有
	 * @return 所有公告信息
	 */
	List<Notice> getNoticeAll();
	
	/**
	 * 
	 * 方法描述：添加公告
	 * @param notice 公告信息
	 * @return 影响的行数
	 */
	int addNotice(Notice notice);
	
	/**
	 * 
	 * 方法描述：根据公告名查询
	 * @param name 公告名称
	 * @return 公告信息
	 */
	Notice getNoticeByName(String name);
	
	/**
	 * 
	 * 方法描述：删除公告
	 * @param notice
	 * @return 影响的行数
	 */
	int delete(int id);
}
