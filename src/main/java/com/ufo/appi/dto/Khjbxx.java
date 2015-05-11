package com.ufo.appi.dto;

/**
 * 客户基本信息
 * 
 * @author appdev
 *
 */
public class Khjbxx extends AbstractApiDto {

	private static String[] SOURCEPROP = {"code", "name","phone", "email", "qq", "address", "region.code", "nickName"};

	private static String[] TARGETPROP = {"khbh", "khxm", "khlxdh", "khyx", "khqqh", "khjtzz", "qybh", "nc"};

	@Override
	protected String[] sourceStrs() {
		return SOURCEPROP;
	}

	@Override
	protected String[] targetStrs() {
		return TARGETPROP;
	}

	private String khbh;// 客户编号 varchar(8)
	private String khxm;// 客户姓名 varchar(30)
	private Integer khxb;// 客户性别 Integer
	private String khlxdh;// 客户联系电话 varchar(20)
	private String khyx;// 客户邮箱 varchar(50)
	private String khqqh;// 客户QQ号 Integer
	private String khjtzz;// 客户家庭住址 varchar(120)
	private String qybh;// 区域编号 varchar(10)
	private String nc;// 呢称 varchar(30)
	private String txurl;// 头像URL varchar(300)

	public Khjbxx() {
		//
	}

	public String getKhbh() {
		return khbh;
	}

	public void setKhbh(String khbh) {
		this.khbh = khbh;
	}

	public String getKhxm() {
		return khxm;
	}

	public void setKhxm(String khxm) {
		this.khxm = khxm;
	}

	public Integer getKhxb() {
		return khxb;
	}

	public void setKhxb(Integer khxb) {
		this.khxb = khxb;
	}

	public String getKhlxdh() {
		return khlxdh;
	}

	public void setKhlxdh(String khlxdh) {
		this.khlxdh = khlxdh;
	}

	public String getKhyx() {
		return khyx;
	}

	public void setKhyx(String khyx) {
		this.khyx = khyx;
	}

	public String getKhqqh() {
		return khqqh;
	}

	public void setKhqqh(String khqqh) {
		this.khqqh = khqqh;
	}

	public String getKhjtzz() {
		return khjtzz;
	}

	public void setKhjtzz(String khjtzz) {
		this.khjtzz = khjtzz;
	}

	public String getQybh() {
		return qybh;
	}

	public void setQybh(String qybh) {
		this.qybh = qybh;
	}

	public String getNc() {
		return nc;
	}

	public void setNc(String nc) {
		this.nc = nc;
	}

	public String getTxurl() {
		return txurl;
	}

	public void setTxurl(String txurl) {
		this.txurl = txurl;
	}

}
