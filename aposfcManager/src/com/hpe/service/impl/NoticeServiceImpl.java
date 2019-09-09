/**
 * 
 */
package com.hpe.service.impl;

import java.util.List;

import com.hpe.dao.INoticeDao;
import com.hpe.dao.impl.NoticeDaoImpl;
import com.hpe.pojo.Notice;
import com.hpe.service.INoticeService;

/** 
 * 类描述：
 * 作者： qinliyu zhoukeyu wangxiaoyu
 * 创建日期：2019年9月6日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class NoticeServiceImpl implements INoticeService {
	
	private INoticeDao noticeDao=new NoticeDaoImpl();
	@Override
	public List<Notice> getNoticeAll() {
		return noticeDao.getNoticeAll();
	}

	@Override
	public int addNotice(Notice notice) {
		Notice notice1=noticeDao.getNoticeByName(notice.getName());
		if(notice1!=null){
			return -1;
		}
		return noticeDao.addNotice(notice);
	}
	
	@Override
	public int delete(int id) {
		return noticeDao.delete(id);
	}
	

	@Override
	 public Notice getNoticeByName(String name) {
	  // TODO Auto-generated method stub
	  return noticeDao.getNoticeByName(name);
	 }

}
