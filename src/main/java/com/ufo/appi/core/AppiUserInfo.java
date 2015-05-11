package com.ufo.appi.core;

/**
 * 存储用户基本信息的对象
 * @author appdev
 *
 */
public class AppiUserInfo {
	
	//用户ID
	private Integer id;
	//用户账号名
	private String loginName;
	//用户类型
	private Integer type;
	//用户关联标识
	private String code;
	//用户姓名
	private String name;

	public AppiUserInfo() {
		 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
