/**
 * 
 */
package com.hpe.pojo;

/** 
 * 类描述：
 * 作者： shenqiutong 
 * 创建日期：2019年9月6日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class OrdersInfo {
	private int userid;//用户编号
	private String menuname;//菜品名
	private String date;//日期
	private int delivery;//派送状态
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public OrdersInfo(int userid, String menuname, String date, int delivery) {
		super();
		this.userid = userid;
		this.menuname = menuname;
		this.date = date;
		this.delivery = delivery;
	}
	public OrdersInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrdersInfo [userid=" + userid + ", menuname=" + menuname + ", date=" + date + ", delivery=" + delivery
				+ "]";
	}
	
}
