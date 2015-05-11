package com.ufo.appi.dto;

import java.io.Serializable;

/**
 * gcxq	工程详情	JSON	工程详情	否	
qyjs(json)
xmgk	项目概况	HTML富文本			
xmxgt	效果图	HTML富文本			
xmzjgh	资金规划	HTML富文本			
xmtsyq	特殊要求	HTML富文本			
xmyzjy	业主寄语	HTML富文本			
xmyzjylx	业主寄语录像	varchar	录像文件URL		

 * @author appdev
 *
 */
public class Gcxq extends AbstractApiDto implements Serializable {

	private static final long serialVersionUID = -4891506184449963984L;

	private static String[] SOURCEPROP = {"about", "drawing","planning", "special", "word", "video"};

	private static String[] TARGETPROP = {"xmgk", "xmxgt", "xmzjgh", "xmtsyq", "xmyzjy", "xmyzjylx"};

	@Override
	protected String[] sourceStrs() {
		return SOURCEPROP;
	}

	@Override
	protected String[] targetStrs() {
		return TARGETPROP;
	}

	private String xmgk;//	项目概况	HTML富文本
	private String 	xmxgt;//	效果图	HTML富文本			
	private String 	xmzjgh;//	资金规划	HTML富文本			
	private String 	xmtsyq;//	特殊要求	HTML富文本			
	private String 	xmyzjy;//	业主寄语	HTML富文本			
	private String 	xmyzjylx;//	业主寄语录像	varchar	录像文件URL

	public Gcxq() {
		// TODO Auto-generated constructor stub
	}

	public String getXmgk() {
		return xmgk;
	}
	public void setXmgk(String xmgk) {
		this.xmgk = xmgk;
	}
	public String getXmxgt() {
		return xmxgt;
	}
	public void setXmxgt(String xmxgt) {
		this.xmxgt = xmxgt;
	}
	public String getXmzjgh() {
		return xmzjgh;
	}
	public void setXmzjgh(String xmzjgh) {
		this.xmzjgh = xmzjgh;
	}
	public String getXmtsyq() {
		return xmtsyq;
	}
	public void setXmtsyq(String xmtsyq) {
		this.xmtsyq = xmtsyq;
	}
	public String getXmyzjy() {
		return xmyzjy;
	}
	public void setXmyzjy(String xmyzjy) {
		this.xmyzjy = xmyzjy;
	}
	public String getXmyzjylx() {
		return xmyzjylx;
	}
	public void setXmyzjylx(String xmyzjylx) {
		this.xmyzjylx = xmyzjylx;
	}

}
