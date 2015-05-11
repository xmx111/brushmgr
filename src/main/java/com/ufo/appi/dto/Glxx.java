package com.ufo.appi.dto;

import java.io.Serializable;

/**
 * glxx 关联信息 JSON 关联信息 glxx(json) xxmc 名称 varchar(80) 姓名或物业公司名称 xxdh 联系电话
 * varchar(20) xxzp 照片URL varchar(80) zpjs 作品介绍 富文本 zpjsspurl 作品视频URL
 * Varchar(200)
 * 
 * 
 * @author appdev
 *
 */
public class Glxx extends AbstractApiDto implements Serializable {

	private static final long serialVersionUID = -6968860267497398094L;

	private static String[] SOURCEPROP = {"name", "phone"};

	private static String[] TARGETPROP = {"xxmc", "xxdh"};

	@Override
	protected String[] sourceStrs() {
		return SOURCEPROP;
	}

	@Override
	protected String[] targetStrs() {
		return TARGETPROP;
	}

	private String xxmc;// 名称 varchar(80) 姓名或物业公司名称
	private String xxdh;// 联系电话 varchar(20)
	private String xxzp;// 照片URL ;//varchar(80)
	private String zpjs;// 作品介绍 富文本
	private String zpjsspurl;// 作品视频URL Varchar(200)
	// xxlx 信息类型 integer 0物业信息、1设计师信息、2项目经理信息、3项目管家信息、4浑化设计师信息
	private Integer xxlx;//
	public Integer getXxlx() {
		return xxlx;
	}

	public void setXxlx(Integer xxlx) {
		this.xxlx = xxlx;
	}

	public Glxx() {
		//  
	}

	public String getXxmc() {
		return xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}

	public String getXxdh() {
		return xxdh;
	}

	public void setXxdh(String xxdh) {
		this.xxdh = xxdh;
	}

	public String getXxzp() {
		return xxzp;
	}

	public void setXxzp(String xxzp) {
		this.xxzp = xxzp;
	}

	public String getZpjs() {
		return zpjs;
	}

	public void setZpjs(String zpjs) {
		this.zpjs = zpjs;
	}

	public String getZpjsspurl() {
		return zpjsspurl;
	}

	public void setZpjsspurl(String zpjsspurl) {
		this.zpjsspurl = zpjsspurl;
	}
}
