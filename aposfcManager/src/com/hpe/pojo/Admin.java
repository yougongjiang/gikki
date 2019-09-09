/**
 * 
 */
package com.hpe.pojo;

/** 
 * 类描述：管理员实体类
 * 作者： shenqiutong 
 * 创建日期：2019年9月2日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class Admin {
	private int id;//管理员编号
	private String name;//用户名
	private String pwd;//密码
	private String authority;//权限
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", pwd=" + pwd + ", authority=" + authority + "]";
	}
	
}
