package com.ufo.appi.dto;

import java.io.Serializable;

/**
 * 图纸信息
 * 
 * @author appdev
 *
 */
public class Tzxx extends AbstractApiDto implements Serializable {

	private static final long serialVersionUID = -3494155020875038894L;

	private static String[] SOURCEPROP = {"id", "contract.engineeringCode","contract.engineeringName", "version", "code", "name", "describes", "designer", "designerTime"};

	private static String[] TARGETPROP = {"tzid", "gcbh", "gcmc", "tzbbh", "tzbh", "tzmc", "tzms", "sjsxm", "scrsj"};

	@Override
	protected String[] sourceStrs() {
		return SOURCEPROP;
	}

	@Override
	protected String[] targetStrs() {
		return TARGETPROP;
	}

	private Integer tzid;// 图纸ID integer
	private String gcbh;// 工程编号 varchar(10)
	private String gcmc;// 工程名称 varchar(40)
	private Integer tzlb;// 图纸类别 integer
	private Integer tzbbh;// 设计版本号 integer
	private Integer tzbh;// 图纸编号 integer
	private String tzmc;// 图纸名称 varchar(30)
	private String tzms;// 图纸描述 varchar(500)
	private String tzurl;// 图纸图片 varchar(200) 图纸的URL地址
	private String tzurl1;// 图纸缩略图
	private String sjsxm;// 设计师姓名 varchar(30)
	private String scrsj;// 上传时间 varchar(30)

    public Integer getTzid() {
        return tzid;
    }

    public void setTzid(Integer tzid) {
        this.tzid = tzid;
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

    public Integer getTzlb() {
        return tzlb;
    }

    public void setTzlb(Integer tzlb) {
        this.tzlb = tzlb;
    }

    public Integer getTzbbh() {
        return tzbbh;
    }

    public void setTzbbh(Integer tzbbh) {
        this.tzbbh = tzbbh;
    }

    public Integer getTzbh() {
        return tzbh;
    }

    public void setTzbh(Integer tzbh) {
        this.tzbh = tzbh;
    }

    public String getTzmc() {
        return tzmc;
    }

    public void setTzmc(String tzmc) {
        this.tzmc = tzmc;
    }

    public String getTzms() {
        return tzms;
    }

    public void setTzms(String tzms) {
        this.tzms = tzms;
    }

    public String getTzurl() {
        return tzurl;
    }

    public void setTzurl(String tzurl) {
        this.tzurl = tzurl;
    }

    public String getTzurl1() {
        return tzurl1;
    }

    public void setTzurl1(String tzurl1) {
        this.tzurl1 = tzurl1;
    }

    public String getSjsxm() {
        return sjsxm;
    }

    public void setSjsxm(String sjsxm) {
        this.sjsxm = sjsxm;
    }

    public String getScrsj() {
        return scrsj;
    }

    public void setScrsj(String scrsj) {
        this.scrsj = scrsj;
    }
	
	
}
