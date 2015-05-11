package com.ufo.appi.web.custom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.appi.core.AppiResponsePacket;
import com.ufo.appi.dto.QyNews;
import com.ufo.appi.dto.Qyjs;
import com.ufo.appi.dto.Qywh;
import com.ufo.appi.dto.Qyzyk;
import com.ufo.basedata.news.entity.News;
import com.ufo.basedata.news.service.interfaces.INewsService;
import com.ufo.basedata.publicity.entity.Publicity;
import com.ufo.basedata.publicity.entity.Publicity.PublicityEnum;
import com.ufo.basedata.publicity.service.interfaces.IPublicityService;
import com.ufo.core.pagination.PropertyFilter;
import com.ufo.core.utils.StringUtils;

/**
 * 企业动态的控制类 企业动态接口：下载企业介绍、下载企业文化、下载新闻资讯列表、下载新闻资讯内容、下载企业资源库。
 * 
 * @author appdev
 *
 */
@Controller("appi.custom.dynamic.Controller")
@RequestMapping("/appi/custom/dynamic")
public class AppiCustomDynamicController extends AppiCustomController {

    @Autowired
    private IPublicityService publicityService;
    @Autowired
    private INewsService newsService;

	/**
	 * 5.3.1. 下载企业介绍 从后台下载企业介绍中的内容。 5.3.1.1. 请求地址
	 * /appi/custom/dynamic/getCompanyIntroduction.ws 5.3.1.2. SJSONP
	 * 
	 * 5.3.1.3. 请求参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 无 示例：
	 * 
	 * 5.3.1.4. 响应参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 sdata(json) qyjs 企业介绍 JSON
	 * 企业介绍对象 否 qyjs(json) gsjj 公司简介 HTML富文本 否 csrjj 创始人简介 HTML富文本 yyms 运营模式
	 * HTML富文本
	 * 
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getCompanyIntroduction.ws")
	@ResponseBody
	public Object getCompanyIntroduction(HttpServletRequest request) {

		AppiResponsePacket responsePacket = succ("操作成功！");

		Publicity publicity=null;
		
		//公司介绍
		publicity=publicityService.findByPublicityType(PublicityEnum.INTRODUCE);
		Qyjs qyjs = new Qyjs();
		qyjs.setGsjj(resourceContextHolder.replaceContent(publicity.getContent()));
		qyjs.setCsrjj(resourceContextHolder.replaceContent(publicity.getContent1()));
		qyjs.setYyms(resourceContextHolder.replaceContent(publicity.getContent2()));
		responsePacket.putSdata("qyjs", qyjs);

		//企业文化
		publicity=publicityService.findByPublicityType(PublicityEnum.CORPORATE);
		Qywh qywh = new Qywh();
		qywh.setZjlzc(resourceContextHolder.replaceContent(publicity.getContent()));
		qywh.setWhp(resourceContextHolder.replaceContent(publicity.getContent1()));
		responsePacket.putSdata("qywh", qywh);
		
		//企业资源库
		publicity=publicityService.findByPublicityType(PublicityEnum.RESOURCES);
		Qyzyk qyzyk = new Qyzyk();
		qyzyk.setQysjs(resourceContextHolder.replaceContent(publicity.getContent()));
		qyzyk.setQyzxgs(resourceContextHolder.replaceContent(publicity.getContent1()));
		qyzyk.setQypps(resourceContextHolder.replaceContent(publicity.getContent2()));

		responsePacket.putSdata("qyzyk", qyzyk);
		
		
		return responsePacket;
	}

	/**
	 * 5.3.3. 下载新闻资讯列表
	 * 
	 * 5.3.3.1. 请求地址 /appi/custom/dynamic/getCompanyNewsList.ws 5.3.3.2. SJSONP
	 * 
	 * 5.3.3.3. 请求参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 无 示例：
	 * 
	 * 5.3.3.4. 响应参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 sdata(json) dsjjslist
	 * 大事件纪实列表 JSON 数组、多条记录 否 zxzxlist 最新资讯列表 JSON 数组、多条记录 否 dsjjslist (json) id
	 * id integer title 标题 varchar(200) time 时间 varchar(20) zxzxlist (json) id
	 * id integer title 标题 varchar(200) time 时间 varchar(20)
	 * 
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getCompanyNewsList.ws")
	@ResponseBody
	public Object getCompanyNewsList(HttpServletRequest request) {
		AppiResponsePacket responsePacket = succ("操作成功！");
		
		String type=request.getParameter("type");
        if (StringUtils.isBlank(type))
            return fail("资讯类型不能为空");
        
        News.TypeEnum ntype=News.TypeEnum.COMPANY;
        if("1".equals(type))
            ntype=News.TypeEnum.INDUSTRY;
	      
		ArrayList<QyNews> newslist = new ArrayList<QyNews>();
		
		Page<News> page=newsService.findPageByType(ntype, PropertyFilter.buildAppPageableFromHttpRequest(request));
		List<News> list=page.getContent();
		for (Iterator<News> iterator = list.iterator(); iterator.hasNext();) {
            News news = (News) iterator.next();
            QyNews qyews = new QyNews();
            qyews.transformObject(news, qyews);
            newslist.add(qyews);
        }

		responsePacket.setTotal(page.getTotalElements());
		responsePacket.putSdata("newslist", newslist);

		return responsePacket;
	}

	/**
	 * 5.3.4. 下载新闻资讯内容
	 * 
	 * 5.3.4.1. 请求地址 /appi/custom/dynamic/getCompanyNewsContent.ws 5.3.4.2.
	 * SJSONP
	 * 
	 * 5.3.4.3. 请求参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 id id integer
	 * 新闻列表中的ID。后台根据该ID查询内容。 示例：
	 * 
	 * 5.3.4.4. 响应参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 sdata(json) news 新闻内容 JSON
	 * 单条新闻内容 否
	 * 
	 * news (json) id id integer title 标题 varchar(200) time 时间 varchar(20)
	 * content 内容 富HTMl文本
	 * 
	 * 
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getCompanyNewsContent.ws")
	@ResponseBody
	public Object getCompanyNewsContent(HttpServletRequest request) {
		AppiResponsePacket responsePacket = succ("操作成功！");

        String id = request.getParameter("id");
        if (StringUtils.isBlank(id))
            return fail("资讯id不能为空");
	        
		QyNews qyews = new QyNews();
		News news=newsService.findOne(Integer.parseInt(request.getParameter("id")));
		qyews.transformObject(news, qyews);
		qyews.setContent(resourceContextHolder.replaceContent(news.getContent()));
		responsePacket.putSdata("news", qyews);

		return responsePacket;
	}
	
}
