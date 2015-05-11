package com.ufo.appi.web.custom;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.appi.context.AppiSession;
import com.ufo.appi.context.SessionCheckException;
import com.ufo.appi.core.AppiRequestPacket;
import com.ufo.appi.core.AppiResponsePacket;
import com.ufo.appi.dto.Bjxx;
import com.ufo.appi.dto.Gcxq;
import com.ufo.appi.dto.Glxx;
import com.ufo.appi.dto.Tzbb;
import com.ufo.appi.dto.Tzxx;
import com.ufo.core.utils.StringUtils;
import com.ufo.project.drawing.entity.Drawing;
import com.ufo.project.drawing.service.interfaces.IDrawingService;
import com.ufo.project.engineering.entity.EngineeringInfo;
import com.ufo.project.engineering.entity.RelatedPersonal;
import com.ufo.project.engineering.service.interfaces.IEngineeringInfoService;
import com.ufo.project.engineering.service.interfaces.IRelatedPersonalService;
import com.ufo.project.quote.entity.Quote;
import com.ufo.project.quote.service.interfaces.IQuoteService;

/**
 * 我的工程控制类 我的工程接口：下载工程详情、下载关联信息、下载设计图纸版本列表、下载设计图纸清单、下载设计图纸详情、下载报价信息列表、下载报价信息详情。
 * 
 * @author appdev
 *
 */
@Controller("appi.custom.project.Controller")
@RequestMapping("/appi/custom/project")
public class AppiCustomProjectController extends AppiCustomController {

	@Autowired
	private IEngineeringInfoService engineeringInfoService;

	@Autowired
	private IRelatedPersonalService relatedPersonalService;

	@Autowired
	private IDrawingService drawingService;

	@Autowired
	private IQuoteService quoteService;

	/**
	 * 5.3.6. 下载工程详情 5.3.6.1. 请求地址 /appi/custom/project/getProjectInfo.ws
	 * 5.3.6.2. 请求参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 gcbh 工程编号 varchar(10) 示例：
	 * 
	 * 5.3.6.3. 响应参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 sdata(json) gcxq 工程详情 JSON
	 * 工程详情 否 qyjs(json) xmgk 项目概况 HTML富文本 xmxgt 效果图 HTML富文本 xmzjgh 资金规划 HTML富文本
	 * xmtsyq 特殊要求 HTML富文本 xmyzjy 业主寄语 HTML富文本 xmyzjylx 业主寄语录像 varchar 录像文件URL
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getProjectInfo.ws")
	@ResponseBody
	public Object getCompanyResource(HttpServletRequest request) {
		AppiRequestPacket requestPacket = getRequestPacket(request);
		AppiSession session = null;
		try {
			session = ctx.checkSession(request);
		} catch (SessionCheckException ex) {
			return fail(ex.getMessage());
		}

		// gcbh 工程编号 varchar(10)
		String gcbh = requestPacket.getParameter("gcbh");
		if (StringUtils.isBlank(gcbh))
			return fail("工程编号不能为空");

		AppiResponsePacket responsePacket = succ("操作成功！");

		EngineeringInfo engineeringInfo = engineeringInfoService.findByEngineeringCode(gcbh);
		Gcxq gcxq = new Gcxq();
		gcxq.setXmgk(resourceContextHolder.replaceContent(engineeringInfo.getAbout()));
		gcxq.setXmxgt(resourceContextHolder.replaceContent(engineeringInfo.getDrawing()));
		gcxq.setXmzjgh(resourceContextHolder.replaceContent(engineeringInfo.getPlanning()));
		gcxq.setXmtsyq(resourceContextHolder.replaceContent(engineeringInfo.getSpecial()));
		gcxq.setXmyzjy(resourceContextHolder.replaceContent(engineeringInfo.getWord()));
		gcxq.setXmyzjylx(resourceContextHolder.replaceContent(engineeringInfo.getVideo()));

		responsePacket.putSdata("gcxq", gcxq);

		return responsePacket;
	}

	/**
	 * 5.3.7. 下载关联信息 5.3.7.1. 请求地址 /appi/custom/project/getProjectRelatedInfo.ws
	 * 5.3.7.2. 请求参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 gcbh 工程编号 varchar(10) xxlx
	 * 信息类型 integer 0物业信息、1设计师信息、2项目经理信息、3项目管家信息、4浑化设计师信息 示例：
	 * 
	 * 5.3.7.3. 响应参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 sdata(json) glxx 关联信息 JSON
	 * 关联信息 否 glxx(json) xxmc 名称 varchar(80) 姓名或物业公司名称 xxdh 联系电话 varchar(20)
	 * xxzp 照片URL varchar(80) zpjs 作品介绍 富文本 zpjsspurl 作品视频URL Varchar(200)
	 * 
	 * 
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getProjectRelatedInfo.ws")
	@ResponseBody
	public Object getProjectRelatedInfo(HttpServletRequest request) {
		AppiRequestPacket requestPacket = getRequestPacket(request);
		AppiSession session = null;
		try {
			session = ctx.checkSession(request);
		} catch (SessionCheckException ex) {
			return nologin();
		}

		// gcbh 工程编号 varchar(10)
		String gcbh = requestPacket.getParameter("gcbh");
		if (StringUtils.isBlank(gcbh))
			return fail("工程编号不能为空");
		// xxlx 信息类型 integer 0物业信息、1设计师信息、2项目经理信息、3项目管家信息、4浑化设计师信息
		String xxlx = requestPacket.getParameter("xxlx");

		AppiResponsePacket responsePacket = succ("操作成功！");

		List<RelatedPersonal> list = relatedPersonalService.findByEngineeringCode(gcbh);
		List<Glxx> glxxList = new ArrayList<Glxx>();
		for (RelatedPersonal relatedPersonal : list){
			Glxx glxx = new Glxx();
			glxx.transformObject(relatedPersonal, glxx);
	        glxx.setXxzp(resourceContextHolder.replaceContent(relatedPersonal.getPhoto()));
	        glxx.setZpjsspurl(resourceContextHolder.replaceContent(relatedPersonal.getVideo()));
	        glxx.setZpjs(relatedPersonal.getIntroduce());
			glxx.setXxlx(relatedPersonal.getType().ordinal());
			glxxList.add(glxx);
		}
		responsePacket.setTotal(glxxList.size());
		responsePacket.putSdata("glxx", glxxList);
//		ArrayList<Glxx> glxxList=new ArrayList<Glxx>();
//		Glxx glxx = new Glxx();
//		glxx.setXxmc("物业信息");
//		glxx.setXxdh("13977881122");
//		glxx.setXxzp("照片的URL地址");
//		glxx.setZpjs("作品介绍的富HTML文本");
//		glxx.setZpjsspurl("视频的URL地址");
//		glxx.setXxlx(0);
//		glxxList.add(glxx);
//
//		glxx = new Glxx();
//		glxx.setXxmc("设计师信息");
//		glxx.setXxdh("13977881122");
//		glxx.setXxzp("照片的URL地址");
//		glxx.setZpjs("作品介绍的富HTML文本");
//		glxx.setZpjsspurl("视频的URL地址");
//		glxx.setXxlx(1);
//		glxxList.add(glxx);
//
//		glxx = new Glxx();
//		glxx.setXxmc("项目经理信息");
//		glxx.setXxdh("13977881122");
//		glxx.setXxzp("照片的URL地址");
//		glxx.setZpjs("作品介绍的富HTML文本");
//		glxx.setZpjsspurl("视频的URL地址");
//		glxx.setXxlx(2);
//		glxxList.add(glxx);
//
//		glxx = new Glxx();
//		glxx.setXxmc("项目管家信息");
//		glxx.setXxdh("13977881122");
//		glxx.setXxzp("照片的URL地址");
//		glxx.setZpjs("作品介绍的富HTML文本");
//		glxx.setZpjsspurl("视频的URL地址");
//		glxx.setXxlx(3);
//		glxxList.add(glxx);
//
//		glxx = new Glxx();
//		glxx.setXxmc("浑化设计师信息");
//		glxx.setXxdh("13977881122");
//		glxx.setXxzp("照片的URL地址");
//		glxx.setZpjs("作品介绍的富HTML文本");
//		glxx.setZpjsspurl("视频的URL地址");
//		glxx.setXxlx(4);
//		glxxList.add(glxx);
//
//		responsePacket.putSdata("glxx", glxxList);

		return responsePacket;
	}

	/**
	 * 5.3.8. 下载设计图纸版本列表 5.3.8.1. 请求地址
	 * /appi/custom/project/getDrawingsVersionList..ws 5.3.8.2. 请求参数 参数 参数名称
	 * 类型（长度） 参数说明 是否为空 样例 公共参数 gcbh 工程编号 varchar(10) tzlb 图纸类别 integer
	 * 图纸类别：0平面方案图、1效果图、2施工图、3产品深化设计图。 示例：
	 * 
	 * 5.3.8.3. 响应参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 sdata(json) tzbblist 版本列表
	 * JSON 多条记录 否 tzbblist(json) tzbbh 图纸版本号 integer
	 * 
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getDrawingsVersionList.ws")
	@ResponseBody
	public Object getDrawingsVersionList(HttpServletRequest request) {
		AppiRequestPacket requestPacket = getRequestPacket(request);
		try {
			ctx.checkSession(request);
		} catch (SessionCheckException ex) {
		    return nologin();
		}

		// gcbh 工程编号 varchar(10)
		String gcbh = requestPacket.getParameter("gcbh");
		if (StringUtils.isBlank(gcbh))
			return fail("工程编号不能为空");
		// tzlb 图纸类别 integer 图纸类别：0平面方案图、1效果图、2施工图、3产品深化设计图。
		String tzlb = requestPacket.getParameter("tzlb");
		if (StringUtils.isBlank(tzlb))
			return fail("图纸类别不能为空");
		
		Drawing.DrawingEnum ntype=Drawing.DrawingEnum.PLAN;
        if("1".equals(tzlb))
            ntype=Drawing.DrawingEnum.RENDERING;
        else if("2".equals(tzlb))
            ntype=Drawing.DrawingEnum.WORK;
        else if("3".equals(tzlb))
            ntype=Drawing.DrawingEnum.DEEPEN;

		AppiResponsePacket responsePacket = succ("操作成功！");

		ArrayList<Tzbb> tzbblist = new ArrayList<Tzbb>();
		
		List<Integer> list = drawingService.findDistinctVersion(gcbh, ntype);
		for (Integer version : list){
			Tzbb tzbb = new Tzbb();
			tzbb.setTzbbh(version);
			tzbb.setTzbbmc("第"+version+"个版本");
			tzbblist.add(tzbb);
		}
		
		responsePacket.setTotal(tzbblist.size());
		responsePacket.putSdata("tzbblist", tzbblist);

		return responsePacket;
	}

	/**
	 * 5.3.9. 下载设计图纸清单 5.3.9.1. 请求地址 /appi/custom/project/getDrawingsPicList.ws
	 * 5.3.9.2. 请求参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 gcbh 工程编号 varchar(10) tzlb
	 * 图纸类别 integer 图纸类别：0平面方案图、1效果图、2施工图、3产品深化设计图。 tzbbh 图纸版本号 integer 示例：
	 * 
	 * 5.3.9.3. 响应参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 sdata(json) tzlist 图纸列表
	 * JSON 多条记录 否 tzlist(json) tzid 图纸ID integer gcbh 工程编号 varchar(10) gcmc
	 * 工程名称 varchar(40) tzlb 图纸类别 integer tzbbh 设计版本号 integer tzbh 图纸编号 integer
	 * tzmc 图纸名称 varchar(30) tzms 图纸描述 varchar(500) tzurl 图纸图片 varchar(200)
	 * 图纸的URL地址 sjsxm 设计师姓名 varchar(30) scrsj 上传时间 varchar(30)
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getDrawingsPicList.ws")
	@ResponseBody
	public Object getDrawingsPicList(HttpServletRequest request) {
		AppiRequestPacket requestPacket = getRequestPacket(request);
		AppiSession session = null;
		try {
			session = ctx.checkSession(request);
		} catch (SessionCheckException ex) {
		    return nologin();
		}

		// gcbh 工程编号 varchar(10)
		String gcbh = requestPacket.getParameter("gcbh");
		if (StringUtils.isBlank(gcbh))
			return fail("工程编号不能为空");
		// tzlb 图纸类别 integer 图纸类别：0平面方案图、1效果图、2施工图、3产品深化设计图。
		String tzlb = requestPacket.getParameter("tzlb");
		if (StringUtils.isBlank(tzlb))
			return fail("图纸类别不能为空");

		Drawing.DrawingEnum ntype=Drawing.DrawingEnum.PLAN;
        if("1".equals(tzlb))
            ntype=Drawing.DrawingEnum.RENDERING;
        else if("2".equals(tzlb))
            ntype=Drawing.DrawingEnum.WORK;
        else if("3".equals(tzlb))
            ntype=Drawing.DrawingEnum.DEEPEN;
		
		// tzbbh 图纸版本号 integer
		String tzbbhStr = requestPacket.getParameter("tzbbh");
		if (StringUtils.isBlank(tzbbhStr))
			return fail("图纸版本号不能为空");
		Integer tzbbh = 0;
		try {
			tzbbh = Integer.valueOf(tzbbhStr);
		} catch (Exception e){
			return fail("图纸版本号参数错误:" + e.getMessage());
		}
		AppiResponsePacket responsePacket = succ("操作成功！");

		ArrayList<Tzxx> tzlist = new ArrayList<Tzxx>();
		List<Drawing> list = drawingService.findByEngineeringCodeAndTypeAndVersion(gcbh, ntype, tzbbh);
		for (Drawing drawing : list){
			Tzxx tzxx = new Tzxx();
			tzxx.transformObject(drawing, tzxx);
			
			//原图
			String tzurl=resourceContextHolder.replaceContent(drawing.getUrl());    
			tzxx.setTzurl(tzurl);

			//缩略图
			if(StringUtils.isNotBlank(tzurl) && tzurl.length()>0){
		         int idx=tzurl.lastIndexOf("/");
		         tzxx.setTzurl1(tzurl.substring(0, idx)+"/small"+tzurl.substring(idx,tzurl.length()));
			}

			tzxx.setTzlb(drawing.getType().ordinal());
			tzlist.add(tzxx);
		}

		responsePacket.setTotal(tzlist.size());
		responsePacket.putSdata("tzlist", tzlist);

		return responsePacket;
	}

	/**
	 * 5.3.10. 下载报价信息列表 5.3.10.1. 请求地址 /appi/custom/project/getPricingList..ws
	 * 5.3.10.2. 请求参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 gcbh 工程编号 varchar(10) 示例：
	 * 
	 * 5.3.10.3. 响应参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 sdata(json) bjlist 报价列表
	 * JSON 多条记录 否 bjlist(json) bjid 报价ID integer gcbh 工程编号 varchar(10) gcmc
	 * 工程名称 varchar(40) bjbh 报价编号 integer bjmc 报价名称 varchar(30) bjms 报价描述
	 * varchar(500) bjurl 报价图片 varchar(200) 报价的URL地址 bjrxm 报价人姓名 varchar(30)
	 * scrsj 上传时间 varchar(30)
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getPricingList.ws")
	@ResponseBody
	public Object getPricingList(HttpServletRequest request) {
		AppiRequestPacket requestPacket = getRequestPacket(request);
		AppiSession session = null;
		try {
			session = ctx.checkSession(request);
		} catch (SessionCheckException ex) {
		    return nologin();
		}

		// gcbh 工程编号 varchar(10)
		String gcbh = requestPacket.getParameter("gcbh");
		if (StringUtils.isBlank(gcbh))
			return fail("工程编号不能为空");

		AppiResponsePacket responsePacket = succ("操作成功！");

		ArrayList<Bjxx> bjlist = new ArrayList<Bjxx>();

		List<Quote> list = quoteService.findByEngineeringCode(gcbh);
		for (Quote quote : list){
			Bjxx bjxx = new Bjxx();
			bjxx.transformObject(quote, bjxx);
			bjxx.setBjurl(resourceContextHolder.replaceContent(quote.getUrl()));
			bjlist.add(bjxx);
		}

//		Bjxx bjxx = new Bjxx();
//		// bjid 报价ID integer
//		bjxx.setBjid(1);
//		// gcbh 工程编号 varchar(10)
//		bjxx.setGcbh("1001");
//		// gcmc 工程名称 varchar(40)
//		bjxx.setGcmc("工程名称");
//		// bjbh 报价编号 integer
//		bjxx.setBjbh(1);
//		// bjmc 报价名称 varchar(30)
//		bjxx.setBjmc("报价名称");
//		// bjms 报价描述 varchar(500)
//		bjxx.setBjms("报价描述");
//		// bjurl 报价图片 varchar(200) 报价的URL地址
//		bjxx.setBjurl("报价图片URL地址");
//		// bjrxm 报价人姓名 varchar(30)
//		bjxx.setBjrxm("报价人姓名！");
//		// scrsj 上传时间 varchar(30)
//		bjxx.setScrsj("20150101020211");
//
//		bjlist.add(bjxx);
//
//		bjxx = new Bjxx();
//		// bjid 报价ID integer
//		bjxx.setBjid(2);
//		// gcbh 工程编号 varchar(10)
//		bjxx.setGcbh("1001");
//		// gcmc 工程名称 varchar(40)
//		bjxx.setGcmc("工程名称");
//		// bjbh 报价编号 integer
//		bjxx.setBjbh(1);
//		// bjmc 报价名称 varchar(30)
//		bjxx.setBjmc("报价名称");
//		// bjms 报价描述 varchar(500)
//		bjxx.setBjms("报价描述");
//		// bjurl 报价图片 varchar(200) 报价的URL地址
//		bjxx.setBjurl("报价图片URL地址");
//		// bjrxm 报价人姓名 varchar(30)
//		bjxx.setBjrxm("报价人姓名！");
//		// scrsj 上传时间 varchar(30)
//		bjxx.setScrsj("20150101020211");
//
//		bjlist.add(bjxx);
		responsePacket.setTotal(bjlist.size());
		responsePacket.putSdata("bjlist", bjlist);

		return responsePacket;
	}
}
