/**
 * 
 */
package com.hpe.service1;

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
public interface ITypesService {
	List<Types> getTypesAll();
	public int addTypes(Types types);
	public  int updateTypes(Types types);
	public Types getTypesbyId(int id);
}
