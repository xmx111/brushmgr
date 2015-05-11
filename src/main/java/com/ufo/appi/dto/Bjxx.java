package com.ufo.appi.dto;

import java.io.Serializable;

/**
 * 报价信息
 * 
 * @author appdev
 *
 */
public class Bjxx extends AbstractApiDto implements Serializable {

	private static final long serialVersionUID = -3391101584049654209L;

	private static String[] SOURCEPROP = {"id", "contract.engineeringCode","contract.engineeringName", "code", "name", "describes", "quoter", "createTime"};

	private static String[] TARGETPROP = {"bjid", "gcbh", "gcmc", "bjbh", "bjmc", "bjms", "bjrxm", "scrsj"};

	@Override
	protected String[] sourceStrs() {
		return SOURCEPROP;
	}

	@Override
	protected String[] targetStrs() {
		return TARGETPROP;
	}

	private Integer bjid;// 报价ID integer
	private String gcbh;// 工程编号 varchar(10)
	private String gcmc;// 工程名称 varchar(40)
	private Integer bjbh;// 报价编号 integer
	private String bjmc;// 报价名称 varchar(30)
	private String bjms;// 报价描述 varchar(500)
	private String bjurl;// 报价图片 varchar(200) 报价的URL地址
	private String bjrxm;// 报价人姓名 varchar(30)
	private String scrsj;// 上传时间 varchar(30)

	public Bjxx() {
		//
	}

	public Integer getBjid() {
        return bjid;
    }

    public void setBjid(Integer bjid) {
        this.bjid = bjid;
    }

    public String getGcbh() {
		return gcbh;
	}

	public void setGcbh(String gcbh) {
		this.gcbh = gcbh;
	}

	public String getGcmc() {
		return gcmc;
	}

	public void setGcmc(String gcmc) {
		this.gcmc = gcmc;
	}

	public Integer getBjbh() {
		return bjbh;
	}

	public void setBjbh(Integer bjbh) {
		this.bjbh = bjbh;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getBjms() {
		return bjms;
	}

	public void setBjms(String bjms) {
		this.bjms = bjms;
	}

	public String getBjurl() {
		return bjurl;
	}

	public void setBjurl(String bjurl) {
		this.bjurl = bjurl;
	}

	public String getBjrxm() {
		return bjrxm;
	}

	public void setBjrxm(String bjrxm) {
		this.bjrxm = bjrxm;
	}

	public String getScrsj() {
		return scrsj;
	}

	public void setScrsj(String scrsj) {
		this.scrsj = scrsj;
	}

}
