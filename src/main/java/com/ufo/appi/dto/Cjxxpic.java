package com.ufo.appi.dto;

public class Cjxxpic {
	private Integer cjid;// 采集标识//CJID//integer
	private Integer tpid; // 图片标识//TPID//integer
	private String tpdz; // 图片地址//TPDZ//varchar(200)

	public Cjxxpic() {
		// TODO Auto-generated constructor stub
	}

    public Integer getCjid() {
        return cjid;
    }

    public void setCjid(Integer cjid) {
        this.cjid = cjid;
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
