package com.ufo.appi.dto;

import java.io.Serializable;

/**
 * 定单信息表的物流跟踪明细
 * 
 * @author appdev
 *
 */
public class Ddxxbwl extends AbstractApiDto implements Serializable {

	private static final long serialVersionUID = -6749506334860288281L;

	private static String[] SOURCEPROP = {"express.order.code", "id","time", "way"};

	private static String[] TARGETPROP = {"ddbh", "wlgxid", "wlgxsj", "wlgxzt"};

	@Override
	protected String[] sourceStrs() {
		return SOURCEPROP;
	}

	@Override
	protected String[] targetStrs() {
		return TARGETPROP;
	}

	private String ddbh;// 定单编号 varchar(30)
	private Integer wlgxid;// 物流更新标识 integer
	private String wlgxsj;// 物流更新时间 datetime
	private String wlgxzt;// 物流更新状态 varchar(100)

	public Ddxxbwl() {
		 
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public Integer getWlgxid() {
		return wlgxid;
	}

	public void setWlgxid(Integer wlgxid) {
		this.wlgxid = wlgxid;
	}

	public String getWlgxsj() {
		return wlgxsj;
	}

	public void setWlgxsj(String wlgxsj) {
		this.wlgxsj = wlgxsj;
	}

	public String getWlgxzt() {
		return wlgxzt;
	}

	public void setWlgxzt(String wlgxzt) {
		this.wlgxzt = wlgxzt;
	}

}
