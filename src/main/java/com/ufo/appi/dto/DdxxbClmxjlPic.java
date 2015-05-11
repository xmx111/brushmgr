package com.ufo.appi.dto;

import java.io.Serializable;

/**
 * 材料明细记录的验收照片
 * 
 * @author appdev
 *
 */
public class DdxxbClmxjlPic extends AbstractApiDto implements Serializable {

	private static final long serialVersionUID = 1279859665221058494L;

	private static String[] SOURCEPROP = {"id"};

	private static String[] TARGETPROP = { "tpid"};

	@Override
	protected String[] sourceStrs() {
		return SOURCEPROP;
	}

	@Override
	protected String[] targetStrs() {
		return TARGETPROP;
	}

	private Integer tpid;// 图片标识 Integer
	private String tpdz;// 图片地址 varchar(200)

	public DdxxbClmxjlPic() {

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
