package com.ufo.appi.dto;

import java.io.Serializable;

/**
 * 图纸版本号
 * @author appdev
 *
 */
public class Tzbb implements Serializable {

	private static final long serialVersionUID = -9092751115708730151L;

	private Integer tzbbh;//	图纸版本号	integer
	private String tzbbmc;//图纸版本名称
	
	public Tzbb() {
		// 
	}

	public Integer getTzbbh() {
		return tzbbh;
	}

	public void setTzbbh(Integer tzbbh) {
		this.tzbbh = tzbbh;
	}

	public String getTzbbmc() {
		return tzbbmc;
	}

	public void setTzbbmc(String tzbbmc) {
		this.tzbbmc = tzbbmc;
	}
	
	

}
