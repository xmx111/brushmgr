package com.ufo.appi.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 定单信息表
 * 
 * @author appdev
 *
 */
public class Ddxxb extends AbstractApiDto implements Serializable {

	private static final long serialVersionUID = -6483833538640885739L;

	private static String[] SOURCEPROP = {"contract.engineeringCode", "contract.engineeringName", "code", "name", "time", "explains", "createOperator.loginName", "createOperator.name", "supplier.code", "supplier.name", "supplierContacts", "supplierTel"};

	private static String[] TARGETPROP = {"gcbh", "gcmc", "ddbh", "ddmc", "ddsj", "ddsm", "xdrzh", "xdrxm", "ghsbh", "ghsmc", "ghslxr", "ghslxdh"};

	@Override
	protected String[] sourceStrs() {
		return SOURCEPROP;
	}

	@Override
	protected String[] targetStrs() {
		return TARGETPROP;
	}

	private String gcbh;// 工程编号 varchar(10)
	private String gcmc;// 工程名称 varchar(40)
	private String ddbh;// 定单编号 varchar(30)
	private String ddmc;// 定单名称 varchar(30)
	private String ddsj;// 定单时间 Datetime
	private String ddsm;// 定单说明 varchar(100)
	private String xdrzh;// 下单人账号 varchar(30)
	private String xdrxm;// 下单人姓名 varchar(30)
	private Integer ddzt;// 定单状态 Integer
	private Integer ddzl;// 定单种类 Integer
	private ArrayList<DdxxbClmxjl> clmxjllist;// 材料明细记录 JSON
	private String ghsbh;// 供货商编号 varchar(20)
	private String ghsmc;// 供货商名称 varchar(80)
	private String ghslxr;// 供货商联系人 varchar(30)
	private String ghslxdh;// 供货商联系电话 varchar(20)
	private Integer wlzt;// 物流状态 Integer
	private String wlsj;// 物流时间 Datetime
	private String wlmc;// 物流名称 varchar(80)
	private String wldh;// 物流单号 varchar(50)
	private String wllxr;// 物流联系人 varchar(30)
	private String wllxdh;// 物流联系电话 varchar(30)
	private ArrayList<Ddxxbwl> wlgzjllist;// 物流跟踪记录列表 JSON
	private Integer yszt;// 验收状态 Integer
	private String yssj;// 验收时间 Datetime
	private String ysrzh;// 验收人账号 varchar(30)
	private String ysrxm;// 验收人姓名 varchar(30)
	private String ysrdh;// 验收人电话 varchar(30)
	private String ysqksm;// 验收情况说明 varchar(500)
	private Integer azzt;// 安装状态 Integer
	private String azsj;// 安装时间 Datetime
	private String azrxm;// 安装人 varchar(30)
	private String azrdh;// 安装人电话 varchar(30)
	private String azqksm;// 安装情况说明 varchar(500)
	private ArrayList<Ddxxbazpic> aztplist;// 安装图片列表 JSON

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

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }

    public String getDdmc() {
        return ddmc;
    }

    public void setDdmc(String ddmc) {
        this.ddmc = ddmc;
    }

    public String getDdsj() {
        return ddsj;
    }

    public void setDdsj(String ddsj) {
        this.ddsj = ddsj;
    }

    public String getDdsm() {
        return ddsm;
    }

    public void setDdsm(String ddsm) {
        this.ddsm = ddsm;
    }

    public String getXdrzh() {
        return xdrzh;
    }

    public void setXdrzh(String xdrzh) {
        this.xdrzh = xdrzh;
    }

    public String getXdrxm() {
        return xdrxm;
    }

    public void setXdrxm(String xdrxm) {
        this.xdrxm = xdrxm;
    }

    public Integer getDdzt() {
        return ddzt;
    }

    public void setDdzt(Integer ddzt) {
        this.ddzt = ddzt;
    }

    public Integer getDdzl() {
        return ddzl;
    }

    public void setDdzl(Integer ddzl) {
        this.ddzl = ddzl;
    }

    public ArrayList<DdxxbClmxjl> getClmxjllist() {
        return clmxjllist;
    }

    public void setClmxjllist(ArrayList<DdxxbClmxjl> clmxjllist) {
        this.clmxjllist = clmxjllist;
    }

    public String getGhsbh() {
        return ghsbh;
    }

    public void setGhsbh(String ghsbh) {
        this.ghsbh = ghsbh;
    }

    public String getGhsmc() {
        return ghsmc;
    }

    public void setGhsmc(String ghsmc) {
        this.ghsmc = ghsmc;
    }

    public String getGhslxr() {
        return ghslxr;
    }

    public void setGhslxr(String ghslxr) {
        this.ghslxr = ghslxr;
    }

    public String getGhslxdh() {
        return ghslxdh;
    }

    public void setGhslxdh(String ghslxdh) {
        this.ghslxdh = ghslxdh;
    }

    public Integer getWlzt() {
        return wlzt;
    }

    public void setWlzt(Integer wlzt) {
        this.wlzt = wlzt;
    }

    public String getWlsj() {
        return wlsj;
    }

    public void setWlsj(String wlsj) {
        this.wlsj = wlsj;
    }

    public String getWlmc() {
        return wlmc;
    }

    public void setWlmc(String wlmc) {
        this.wlmc = wlmc;
    }

    public String getWldh() {
        return wldh;
    }

    public void setWldh(String wldh) {
        this.wldh = wldh;
    }

    public String getWllxr() {
        return wllxr;
    }

    public void setWllxr(String wllxr) {
        this.wllxr = wllxr;
    }

    public String getWllxdh() {
        return wllxdh;
    }

    public void setWllxdh(String wllxdh) {
        this.wllxdh = wllxdh;
    }

    public ArrayList<Ddxxbwl> getWlgzjllist() {
        return wlgzjllist;
    }

    public void setWlgzjllist(ArrayList<Ddxxbwl> wlgzjllist) {
        this.wlgzjllist = wlgzjllist;
    }

    public Integer getYszt() {
        return yszt;
    }

    public void setYszt(Integer yszt) {
        this.yszt = yszt;
    }

    public String getYssj() {
        return yssj;
    }

    public void setYssj(String yssj) {
        this.yssj = yssj;
    }

    public String getYsrzh() {
        return ysrzh;
    }

    public void setYsrzh(String ysrzh) {
        this.ysrzh = ysrzh;
    }

    public String getYsrxm() {
        return ysrxm;
    }

    public void setYsrxm(String ysrxm) {
        this.ysrxm = ysrxm;
    }

    public String getYsrdh() {
        return ysrdh;
    }

    public void setYsrdh(String ysrdh) {
        this.ysrdh = ysrdh;
    }

    public String getYsqksm() {
        return ysqksm;
    }

    public void setYsqksm(String ysqksm) {
        this.ysqksm = ysqksm;
    }

    public Integer getAzzt() {
        return azzt;
    }

    public void setAzzt(Integer azzt) {
        this.azzt = azzt;
    }

    public String getAzsj() {
        return azsj;
    }

    public void setAzsj(String azsj) {
        this.azsj = azsj;
    }

    public String getAzrxm() {
        return azrxm;
    }

    public void setAzrxm(String azrxm) {
        this.azrxm = azrxm;
    }

    public String getAzrdh() {
        return azrdh;
    }

    public void setAzrdh(String azrdh) {
        this.azrdh = azrdh;
    }

    public String getAzqksm() {
        return azqksm;
    }

    public void setAzqksm(String azqksm) {
        this.azqksm = azqksm;
    }

    public ArrayList<Ddxxbazpic> getAztplist() {
        return aztplist;
    }

    public void setAztplist(ArrayList<Ddxxbazpic> aztplist) {
        this.aztplist = aztplist;
    }
	
	
}
