/**
 * 
 */
package com.hpe.dao;

import java.util.List;

import com.hpe.pojo.Types;

/** 
 * 类描述：
 * 作者： shenqiutong 
 * 创建日期：2019年9月3日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public interface ITypesDao {
	//查询所有类别
	List<Types> getTypesAll();
	//添加类别
	int addTypes(Types types);
	//根据类别名称查询
	Types getTypesByName(String name);
	//修改
	int updateTypes(Types types);
	//根据id查询
	Types getTypesbyId(int id);
	//删除
	int deleteTypes(int id);
}
