package com.ufo.appi.dto;

/**
 * qyjs 企业介绍 JSON 企业介绍对象 qyjs(json) gsjj 公司简介 HTML富文本 csrjj 创始人简介 HTML富文本 yyms
 * 运营模式 HTML富文本
 * 
 * @author appdev
 *
 */
public class Qyjs {
	private String gsjj;// 公司简介 HTML富文本
	private String csrjj;// 创始人简介 HTML富文本
	private String yyms;// 运营模式 HTML富文本

	public Qyjs() {
		// TODO Auto-generated constructor stub
	}

	public String getGsjj() {
		return gsjj;
	}

	public void setGsjj(String gsjj) {
		this.gsjj = gsjj;
	}

	public String getCsrjj() {
		return csrjj;
	}

	public void setCsrjj(String csrjj) {
		this.csrjj = csrjj;
	}

	public String getYyms() {
		return yyms;
	}

	public void setYyms(String yyms) {
		this.yyms = yyms;
	}

}
