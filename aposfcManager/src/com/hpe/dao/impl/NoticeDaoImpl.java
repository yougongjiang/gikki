/**
 * 
 */
package com.hpe.dao.impl;

import java.util.List;

import com.hpe.dao.INoticeDao;
import com.hpe.pojo.Notice;
import com.hpe.util.DBUtil;

/** 
 * 类描述：
 * 作者： qinliyu zhoukeyu wangxiaoyu
 * 创建日期：2019年9月6日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class NoticeDaoImpl implements INoticeDao {

	private DBUtil dbutil=new DBUtil();
	@Override
	public List<Notice> getNoticeAll() {
		String sql="select * from notice";
		List list=null;
		try {
			list=dbutil.getQueryList(Notice.class, sql, null);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int addNotice(Notice notice) {
		String sql="insert into notice(name,content,times) values(?,?,?)";
		Object[] param={notice.getName(),notice.getContent(),notice.getTimes()};
		int result=0;
		try {
			result=dbutil.execute(sql, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Notice getNoticeByName(String name) {
		String sql="select * from notice where name=?";
		Object[] param={name};
		Notice notice=null;
		try {
			notice=(Notice) dbutil.getObject(Notice.class, sql, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}
	
	public int delete(int id) {
		int result = 0;
		//编写SQL语句
		String sql = "DELETE FROM notice WHERE id=?";
		Object[] param = {id};
		try {
			result = dbutil.execute(sql, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
