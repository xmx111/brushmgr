package com.ufo.appi.dto;

import com.ufo.engineering.order.entity.Order;
import com.ufo.project.contract.entity.Contract;

public class Htxxb extends AbstractApiDto {

    private static String[] SOURCEPROP = {"custom.code", "custom.name","engineeringCode", "engineeringName", "engineeringCommunity"};

    private static String[] TARGETPROP = {"khbh", "khxm", "gcbh", "gcmc", "gcxq"};

	@Override
	protected String[] sourceStrs() {
		return SOURCEPROP;
	}

	@Override
	protected String[] targetStrs() {
		return TARGETPROP;
	}

	private String khbh;// 客户编号 varchar(20)
	private String khxm;// 客户姓名 varchar(30)
	private Integer htlx;// 合同类型 integer
	private String gcbh;// 工程编号 varchar(10)
	private String gcmc;// 工程名称 varchar(40)
	private String gcxq;// 工地小区 varchar(40)

	public Htxxb() {

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

	public Integer getHtlx() {
		return htlx;
	}

	public void setHtlx(Integer htlx) {
		this.htlx = htlx;
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

	public String getGcxq() {
		return gcxq;
	}

	public void setGcxq(String gcxq) {
		this.gcxq = gcxq;
	}

    public static void main(String[] args){
        Htxxb b = new Htxxb();

        Order order = new Order();
        Contract contract = new Contract();
        order.setCode("ddd");
        order.setName("客户1");
        contract.setId(1);
        contract.setEngineeringCode("编号");
        contract.setEngineeringName("名称");
//        contract.setEngineeringCommunityAddress("地址");
        order.setContract(contract);

        try {
            b.transformObject(order, b);
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println(1);
    }

}
