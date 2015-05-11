package com.ufo.appi.dto;
/**
 * 	5.3.2.	下载企业文化

5.3.2.1.	请求地址
/appi/custom/dynamic/getCompanyCulture.ws
5.3.2.2.	SJSONP

5.3.2.3.	请求参数
参数	参数名称	类型（长度）	参数说明	是否为空	样例
公共参数	
无					
示例：

5.3.2.4.	响应参数
参数	参数名称	类型（长度）	参数说明	是否为空	样例
公共参数	
sdata(json)
qywh	企业文化	JSON	企业文化对象	否	
qyjs(json)
zjlzc	总经理致辞	HTML富文本			
whp	文化篇	HTML富文本		
 * @author appdev
 *
 */
public class Qywh {
private String	zjlzc;//	总经理致辞	HTML富文本			
private String	whp	;//文化篇	HTML富文本		
	 
	public String getZjlzc() {
	return zjlzc;
}

public void setZjlzc(String zjlzc) {
	this.zjlzc = zjlzc;
}

public String getWhp() {
	return whp;
}

public void setWhp(String whp) {
	this.whp = whp;
}

	public Qywh() {
		// TODO Auto-generated constructor stub
	}

}
