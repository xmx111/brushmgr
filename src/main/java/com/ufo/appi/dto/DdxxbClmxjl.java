package com.ufo.appi.dto;

import java.util.ArrayList;

/**
 * 材料明细记录
 * 
 * @author appdev
 *
 */
public class DdxxbClmxjl extends AbstractApiDto {

	private static String[] SOURCEPROP = {"id", "material.id", "material.code", "material.name", "material.describes", "requireNum", "manufacturers", "batch", "actParameter", "acceptanceExplain"};

	private static String[] TARGETPROP = {"ddclmxid", "ddclid", "clbh", "clmc", "clms", "slyq", "sccj", "scpj", "sjclcs", "shyssm"};

	@Override
	protected String[] sourceStrs() {
		return SOURCEPROP;
	}

	@Override
	protected String[] targetStrs() {
		return TARGETPROP;
	}

	private Integer ddclmxid;// 定单材料明细标识 integer
	private Integer ddclid;// 定单材料标识 integer
	private String clbh;// 材料编号 varchar(10)
	private String clmc;// 材料名称 varchar(80)
	private String clms;// 材料描述 varchar(500)
	private String slyq;// 数量要求 varchar(80)
	private String sccj;// 生产厂家 varchar(120)
	private String scpj;// 生产批次 varchar(120)
	private String sjclcs;// 实际材料参数 varchar(500)
	private Integer shyszt;// 收货验收状态 integer
	private String shyssm;// 收货验收说明 varchar(500)
	private ArrayList<DdxxbClmxjlPic> shcszplist;// 收货验收照片列表 JSON 数组、多条记录

	public DdxxbClmxjl() {

	}


    public Integer getDdclmxid() {
        return ddclmxid;
    }

    public void setDdclmxid(Integer ddclmxid) {
        this.ddclmxid = ddclmxid;
    }

    public Integer getDdclid() {
		return ddclid;
	}

	public void setDdclid(Integer ddclid) {
		this.ddclid = ddclid;
	}

	public String getClbh() {
		return clbh;
	}

	public void setClbh(String clbh) {
		this.clbh = clbh;
	}

	public String getClmc() {
		return clmc;
	}

	public void setClmc(String clmc) {
		this.clmc = clmc;
	}

	public String getClms() {
		return clms;
	}

	public void setClms(String clms) {
		this.clms = clms;
	}

	public String getSlyq() {
		return slyq;
	}

	public void setSlyq(String slyq) {
		this.slyq = slyq;
	}

	public String getSccj() {
		return sccj;
	}

	public void setSccj(String sccj) {
		this.sccj = sccj;
	}

	public String getScpj() {
		return scpj;
	}

	public void setScpj(String scpj) {
		this.scpj = scpj;
	}

	public String getSjclcs() {
		return sjclcs;
	}

	public void setSjclcs(String sjclcs) {
		this.sjclcs = sjclcs;
	}

	public Integer getShyszt() {
		return shyszt;
	}

	public void setShyszt(Integer shyszt) {
		this.shyszt = shyszt;
	}

	public String getShyssm() {
		return shyssm;
	}

	public void setShyssm(String shyssm) {
		this.shyssm = shyssm;
	}

	public ArrayList<DdxxbClmxjlPic> getShcszplist() {
		return shcszplist;
	}

	public void setShcszplist(ArrayList<DdxxbClmxjlPic> shcszplist) {
		this.shcszplist = shcszplist;
	}

}
