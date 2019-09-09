/**
 * 
 */
package com.hpe.pojo;

/** 
 * 类描述：
 * 作者： shenqiutong 
 * 创建日期：2019年9月5日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class ShoppingCar {

	private int menuid;
	private String name;
	private float price;
	private int sum;
	          
	public int getMenuid() {
		return menuid;
	}
	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
}
