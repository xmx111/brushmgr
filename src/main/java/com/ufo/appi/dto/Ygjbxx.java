package com.ufo.appi.dto;

public class Ygjbxx extends AbstractApiDto {

    private static String[] SOURCEPROP = {"code", "name","phone", "email", "nickName"};

    private static String[] TARGETPROP = {"ygbh", "ygxm", "yglxdh", "ygyx", "nc"};

    @Override
    protected String[] sourceStrs() {
        return SOURCEPROP;
    }

    @Override
    protected String[] targetStrs() {
        return TARGETPROP;
    }
    
	private String ygbh;// 员工编号 varchar(8)
	private String ygxm;// 员工姓名 varchar(30)
	private Integer ygxb;// 员工性别 Integer
	private String yglxdh;// 员工联系电话 varchar(20)
	private String ygyx;// 员工邮箱 varchar(50)
	private Integer yglx;// 员工类型 Integer
	private String nc;// 呢称 varchar(30)
	private String txurl;// 头像URL varchar(300)

	public Ygjbxx() {
		// TODO Auto-generated constructor stub
	}

    public String getYgbh() {
        return ygbh;
    }

    public void setYgbh(String ygbh) {
        this.ygbh = ygbh;
    }

    public String getYgxm() {
        return ygxm;
    }

    public void setYgxm(String ygxm) {
        this.ygxm = ygxm;
    }

    public Integer getYgxb() {
        return ygxb;
    }

    public void setYgxb(Integer ygxb) {
        this.ygxb = ygxb;
    }

    public String getYglxdh() {
        return yglxdh;
    }

    public void setYglxdh(String yglxdh) {
        this.yglxdh = yglxdh;
    }

    public String getYgyx() {
        return ygyx;
    }

    public void setYgyx(String ygyx) {
        this.ygyx = ygyx;
    }

    public Integer getYglx() {
        return yglx;
    }

    public void setYglx(Integer yglx) {
        this.yglx = yglx;
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
