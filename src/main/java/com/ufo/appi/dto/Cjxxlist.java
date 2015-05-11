package com.ufo.appi.dto;

public class Cjxxlist extends AbstractApiDto{
    private static String[] SOURCEPROP = { "id", "contract.engineeringCode", "contract.engineeringName", "describes",
            "video", "createTime", "createOperator.loginName", "createOperator.employee.name" };

    private static String[] TARGETPROP = { "cjid", "gcbh", "gcmc", "wjms", "lyzl", "cjsj", "cjrzh", "cjrxm" };

    @Override
    protected String[] sourceStrs() {
        return SOURCEPROP;
    }

    @Override
    protected String[] targetStrs() {
        return TARGETPROP;
    }
    
	private Integer cjid;// 采集标识 Integer
	private String gcbh;// 工程编号 varchar(10)
	private String gcmc;// 工程名称 varchar(40)
	private String wjms;// 文字描述 varchar(2000)
	private String lyzl;// 录音资料 varchar(200)
	private String cjsj;// 采集时间 Datetime
	private String cjrzh;// 采集人账号 Integer
	private String cjrxm;// 采集人姓名 varchar(30)

	public Cjxxlist() {

	}

	public Integer getCjid() {
		return cjid;
	}

	public void setCjid(Integer cjid) {
		this.cjid = cjid;
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

	public String getWjms() {
		return wjms;
	}

	public void setWjms(String wjms) {
		this.wjms = wjms;
	}

	public String getLyzl() {
		return lyzl;
	}

	public void setLyzl(String lyzl) {
		this.lyzl = lyzl;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public String getCjrzh() {
		return cjrzh;
	}

	public void setCjrzh(String cjrzh) {
		this.cjrzh = cjrzh;
	}

	public String getCjrxm() {
		return cjrxm;
	}

	public void setCjrxm(String cjrxm) {
		this.cjrxm = cjrxm;
	}

}
