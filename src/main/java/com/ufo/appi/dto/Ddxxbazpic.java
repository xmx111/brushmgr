package com.ufo.appi.dto;

import java.io.Serializable;

/**
 * 定单信息所属的安装图片
 * 
 * @author appdev
 *
 */
public class Ddxxbazpic extends AbstractApiDto implements Serializable {

	private static final long serialVersionUID = -1900001716177500124L;

	private static String[] SOURCEPROP = {"installationInfo.order.code", "id"};

	private static String[] TARGETPROP = {"ddbh", "tpid"};

	@Override
	protected String[] sourceStrs() {
		return SOURCEPROP;
	}

	@Override
	protected String[] targetStrs() {
		return TARGETPROP;
	}

	private String ddbh;// 定单编号 varchar(30)
	private Integer tpid;// 图片标识 Integer
	private String tpdz;// 图片地址 varchar(200)

	public Ddxxbazpic() {
		// TODO Auto-generated constructor stub
	}

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public String getTpdz() {
        return tpdz;
    }

    public void setTpdz(String tpdz) {
        this.tpdz = tpdz;
    }

	
}
