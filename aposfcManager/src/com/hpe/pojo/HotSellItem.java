/**
 * 
 */
package com.hpe.pojo;

/** 
 * 类描述：
 * 作者： qinliyu 
 * 创建日期：2019年9月6日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class HotSellItem {

	private int id;
	private String name;//
	private Double sellnum;//销售数量
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSellnum() {
		return sellnum;
	}
	public void setSellnum(Double sellnum) {
		this.sellnum = sellnum;
	}
	
	
	
}
