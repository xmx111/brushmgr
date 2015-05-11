package com.ufo.appi.web.salesman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.appi.context.AppiSession;
import com.ufo.appi.context.SessionCheckException;
import com.ufo.appi.core.AppiBaseController;
import com.ufo.appi.core.AppiRequestPacket;
import com.ufo.appi.core.AppiResponsePacket;
import com.ufo.appi.dto.Cjxx;
import com.ufo.appi.dto.Cjxxlist;
import com.ufo.appi.dto.Cjxxpic;
import com.ufo.appi.dto.Ddxxb;
import com.ufo.appi.dto.DdxxbClmxjl;
import com.ufo.appi.dto.DdxxbClmxjlPic;
import com.ufo.appi.dto.Ddxxbazpic;
import com.ufo.appi.dto.Ddxxbwl;
import com.ufo.appi.dto.Htxxb;
import com.ufo.appi.dto.Ygjbxx;
import com.ufo.config.sys.entity.Manager.ManagerEnum;
import com.ufo.core.dto.FileInfoDTO;
import com.ufo.core.pagination.PropertyFilter;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.utils.CollectionUtils;
import com.ufo.core.utils.DateUtils;
import com.ufo.core.utils.StringUtils;
import com.ufo.engineering.order.entity.Acceptance;
import com.ufo.engineering.order.entity.AcceptanceImage;
import com.ufo.engineering.order.entity.Express;
import com.ufo.engineering.order.entity.ExpressWay;
import com.ufo.engineering.order.entity.InstallationImage;
import com.ufo.engineering.order.entity.InstallationInfo;
import com.ufo.engineering.order.entity.Order;
import com.ufo.engineering.order.entity.OrderDetails;
import com.ufo.engineering.order.service.interfaces.IAcceptanceImageService;
import com.ufo.engineering.order.service.interfaces.IAcceptanceService;
import com.ufo.engineering.order.service.interfaces.IInstallationInfoService;
import com.ufo.engineering.order.service.interfaces.IOrderDetailsService;
import com.ufo.engineering.order.service.interfaces.IOrderService;
import com.ufo.engineering.special.entity.SpecialImage;
import com.ufo.engineering.special.entity.SpecialInfo;
import com.ufo.engineering.special.service.interfaces.ISpecialImageService;
import com.ufo.engineering.special.service.interfaces.ISpecialInfoService;
import com.ufo.project.contract.entity.Contract;
import com.ufo.project.contract.service.interfaces.IContractService;
import com.ufo.project.engineering.service.interfaces.IEngineeringInfoService;

/**
 * 采集APP的接口类
 * 
 * @author appdev
 *
 */
@Controller("appi.salesmain.Controller")
@RequestMapping("/appi/salesman/")
public class AppiSalesmanController extends AppiBaseController<Contract, Integer> {

    @Autowired
    private IContractService contractService;
    @Autowired
    private ISpecialInfoService specialInfoService;
    @Autowired
    private ISpecialImageService specialImageService;
	@Autowired
	private IOrderDetailsService orderDetailsService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IEngineeringInfoService engineeringInfoService;
    @Autowired
    private IAcceptanceImageService acceptanceImageService;
    @Autowired
    private IAcceptanceService acceptanceService;
    @Autowired
    private IInstallationInfoService installationInfoService;
    
	@Override
	protected IBaseSpringDataService<Contract, Integer> getEntityService() {
		return null;
	}

	/**
	 * 5.2.1. 获取用户资料 手机端在登录成功以后或在打开用户中心时，从后台读取用户的基本资料。资料内容包括用户基本属性和所管理的工程清单。
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getUserInfoByUserName.ws")
	@ResponseBody
	public Object getUserInfoByUserName(HttpServletRequest request) {
		AppiSession session = null;
		try {
			session = ctx.checkSession(request);
		} catch (SessionCheckException ex) {
		    return nologin();
		}

		AppiResponsePacket responsePacket = succ("操作成功！");

		//获取登录时保存在会话中的基本信息
		Ygjbxx ygjbxx = (Ygjbxx) session.getAttribute("ygjbxx");
		if (ygjbxx != null) {
			responsePacket.putSdata("ygjbxx", ygjbxx);
		}

		// 读取该员工编号与关联管理的合同信息列表/
		// 根据工程关联信息表中存储的员工编号查询
		ArrayList<Htxxb> htxxbList = new ArrayList<Htxxb>();
		List<Contract> list=contractService.findByUser(ManagerEnum.EMPLOYEE, session.getUserInfo().getCode());
		for (Iterator<Contract> iterator = list.iterator(); iterator.hasNext();) {
            Contract contract = (Contract) iterator.next();
            Htxxb htxxb = new Htxxb();
            htxxb.transformObject(contract, htxxb);
            htxxb.setHtlx(contract.getType().ordinal());
            htxxbList.add(htxxb);
        }
		responsePacket.putSdata("htxxb", htxxbList);
		return responsePacket;
	}

	/**
	 * 5.2.2. 下载采集信息记录 采集APP从后台获取用户相关联的已经录入系统的项目采集信息列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getUserCollectInfoByProject.ws")
	@ResponseBody
	public Object getUserCollectInfoByProject(HttpServletRequest request) {
		AppiRequestPacket requestPacket = getRequestPacket(request);
		try {
		    ctx.checkSession(request);
		} catch (SessionCheckException ex) {
		    return nologin();
		}

		AppiResponsePacket responsePacket = succ("操作成功！");

		// 前台传递过来的工程编号
		String gcbh = requestPacket.getParameter("gcbh");
		ArrayList<Cjxxlist> cjxxlist = new ArrayList<Cjxxlist>();
		
        Page<SpecialInfo> page=specialInfoService.findPageByEngineeringCode(gcbh, PropertyFilter.buildAppPageableFromHttpRequest(request));
        List<SpecialInfo> list=page.getContent(); 
		for (Iterator<SpecialInfo> iterator = list.iterator(); iterator.hasNext();) {
            SpecialInfo specialInfo = (SpecialInfo) iterator.next();
            Cjxxlist cjxx = new Cjxxlist();
            cjxx.transformObject(specialInfo, cjxx);
            cjxx.setLyzl(resourceContextHolder.replaceContent(cjxx.getLyzl()));
            cjxxlist.add(cjxx);
        }
		
		responsePacket.setTotal(page.getTotalElements());
		responsePacket.putSdata("cjxxlist", cjxxlist);
		
		return responsePacket;
	}

	/**
	 * 5.2.3. 下载采集信息详情 5.2.3.1. 请求地址
	 * /appi/salesman/getUserCollectInfoByProjectMx.ws
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getUserCollectInfoByProjectMx.ws")
	@ResponseBody
	public Object getUserCollectInfoByProjectMx(HttpServletRequest request) {
		AppiRequestPacket requestPacket = getRequestPacket(request);
		try {
		    ctx.checkSession(request);
		} catch (SessionCheckException ex) {
		    return nologin();
		}

		AppiResponsePacket responsePacket = succ("操作成功！");

		// 前台传递过来的工程编号
		String gcbh = requestPacket.getParameter("gcbh");
		//采集标识
		String cjid = requestPacket.getParameter("cjid");
		
		Integer speicalId=Integer.parseInt(cjid);
        SpecialInfo specialInfo =specialInfoService.findOne(speicalId);
        
        //采集信息
        Cjxx cjxx = new Cjxx();
        cjxx.transformObject(specialInfo, cjxx);
        cjxx.setLyzl(resourceContextHolder.replaceContent(cjxx.getLyzl()));
        cjxx.setCjxxpic(new ArrayList<Cjxxpic>());
		
        //图片信息
		List<SpecialImage> list=specialImageService.findBySpecialId(speicalId);
		for (Iterator<SpecialImage> iterator = list.iterator(); iterator.hasNext();) {
            SpecialImage specialImage = (SpecialImage) iterator.next();
            
            Cjxxpic pic = new Cjxxpic();
            pic.setCjid(speicalId);
            pic.setTpid(specialImage.getId());
            pic.setTpdz(resourceContextHolder.replaceContent(specialImage.getUrl()));
            cjxx.getCjxxpic().add(pic);
        }
		
		responsePacket.putSdata("cjxx", cjxx);
		return responsePacket;
	}
	
	
	   /**
     * 5.2.4. 上传采集信息. 请求地址
     * /appi/salesman/saveUserCollectInfoByProject.ws
     * 
     * 
     * @param request
     * @return
     */
    @RequestMapping("saveUserCollectInfoByProject.ws")
    @ResponseBody
    public Object saveUserCollectInfoByProject(HttpServletRequest request) {
        AppiRequestPacket requestPacket = getRequestPacket(request);
        AppiSession session = null;
        try {
            session = ctx.checkSession(request);
        } catch (SessionCheckException ex) {
            return nologin();
        }

        AppiResponsePacket responsePacket = succ("操作成功！");

        // 前台传递过来的工程编号
        String gcbh = requestPacket.getParameter("gcbh");
        if (StringUtils.isBlank(gcbh))
            return fail("工程编号不能为空");

        SpecialInfo specialInfo=new SpecialInfo();
        specialInfo.setContract(contractService.findByProperty("engineeringCode", gcbh));//合同
        specialInfo.setDescribes(requestPacket.getParameter("wjms"));//文字描述
        specialInfoService.saveAppi(specialInfo, session);
        
        responsePacket.putSdata("cjid", specialInfo.getId());
        return responsePacket;
    }
    
    
        /**
      * 5.2.5.上传采集图片、录音信息
      * /appi/salesman/saveUserCollectInfoByProjectPicVideo.ws
      * 
      * 
      * @param request
      * @return
      */
     @RequestMapping("saveUserCollectInfoByProjectPicVideo.ws")
     @ResponseBody
     public Object saveUserCollectInfoByProjectPicVideo(HttpServletRequest request) {
         AppiSession appiSession = null;
         AppiRequestPacket requestPacket = getRequestPacket(request);
         try {
             appiSession = ctx.checkSession(request);
         } catch (SessionCheckException ex) {
             return nologin();
         }
    
         AppiResponsePacket responsePacket = succ("操作成功！");
    
         // 采集标识id
         String cjid = requestPacket.getParameter("cjid");
         if (StringUtils.isBlank(cjid))
             return fail("采集标识不能为空");
         
         String sclx = requestPacket.getParameter("sclx");
         if (StringUtils.isBlank(sclx))
             return fail("上传类型不能为空");
         
         //上传文件
         FileInfoDTO info=null;
         try {
             info=saveFile(request);
        } catch (IOException e) {
            return fail(e.getMessage());
        }

         SpecialInfo specialInfo=this.specialInfoService.findOne(Integer.parseInt(cjid));
         
         if(sclx.equals("1")){//上传图片
             SpecialImage specialImage=new SpecialImage();
             specialImage.setSpecialInfo(specialInfo);
             specialImage.setUrl(info.getUrl());
             specialImageService.saveAppi(specialImage, appiSession);
             responsePacket.putSdata("fileid", requestPacket.getParameter("fileid"));
         }else if(sclx.equals("2")){//上传录音
             specialInfo.setVideo(info.getUrl());
             specialInfoService.saveAppi(specialInfo, appiSession);
         }

         return responsePacket;
     }
    	

	/**
	 * 5.2.6. 下载材料定单记录 获取某一个工程编号下的所有定单记录。
	 * 
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getOrderInfoByProject.ws")
	@ResponseBody
	public Object getOrderInfoByProject(HttpServletRequest request) {
		AppiRequestPacket requestPacket = getRequestPacket(request);
		try {
			ctx.checkSession(request);
		} catch (SessionCheckException ex) {
		    return nologin();
		}

		AppiResponsePacket responsePacket = succ("操作成功！");

		// 前台传递过来的工程编号//gcbh 工程编号 varchar(10)
		String gcbh = requestPacket.getParameter("gcbh");

		if (StringUtils.isBlank(gcbh))
			return fail("工程编号不能为空");

		ArrayList<Ddxxb> ddxxblist = new ArrayList<Ddxxb>();
		
	    Page<Order> page=orderService.findPageByEngineeringCode(gcbh, PropertyFilter.buildAppPageableFromHttpRequest(request));
	    List<Order> list=page.getContent();
		for (Order order : list){
			Ddxxb ddxxb = new Ddxxb();
			ddxxb.transformObject(order, ddxxb);
			ddxxb.setDdzt(order.getStatus().ordinal());// ddzt 定单状态 Integer
			ddxxb.setDdzl(order.getType().ordinal());// ddzl 定单种类 Integer
			ddxxb.setWlzt(null!=order.getExpress() ? order.getExpress().getStatus().ordinal() : 0);// wlzt 物流状态 Integer
			ddxxb.setYszt(null!=order.getAcceptance() ? order.getAcceptance().getStatus().ordinal() : 0);// yszt 验收状态 Integer
			ddxxb.setAzzt(null!=order.getInstallationInfo() ? order.getInstallationInfo().getStatus().ordinal() : 0);// azzt 安装状态 Integer
			ddxxblist.add(ddxxb);
		}
		
		responsePacket.setTotal(page.getTotalElements());
		responsePacket.putSdata("ddxxblist", ddxxblist);

		return responsePacket;

	}

	/**
	 * 5.2.7.	下载材料定单详情 获取某一笔定单的所有详细信息。
	 * 
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getOrderInfoByProjectMx.ws")
	@ResponseBody
	public Object getOrderInfoByProjectMx(HttpServletRequest request) {
		AppiRequestPacket requestPacket = getRequestPacket(request);
		try {
			ctx.checkSession(request);
		} catch (SessionCheckException ex) {
		    return nologin();
		}

		AppiResponsePacket responsePacket = succ("操作成功！");
		
		String ddbh = requestPacket.getParameter("ddbh");
		
		if (StringUtils.isBlank(ddbh))
			return fail("订单编号不能为空");

		// todo:根据工程编号查询所有的定单记录

		Order order = orderService.findByProperty("code", ddbh);
		Ddxxb ddxxb = new Ddxxb();
		ddxxb.transformObject(order, ddxxb);
		
		//物流信息
		Express express=order.getExpress();
		if(null!=express){
			ddxxb.setWlsj(DateUtils.format(express.getTime(), DateUtils.FT_LONG_DATE_TIME) );// wlsj 物流时间 Datetime
			ddxxb.setWlzt(express.getStatus().ordinal());// wlzt 物流状态 Integer
			ddxxb.setWlmc(express.getName());// wlmc 物流名称 varchar(80)
			ddxxb.setWldh(express.getNo());// wldh 物流单号 varchar(50)
			ddxxb.setWllxr(express.getSender());// wllxr 物流联系人 varchar(30)
			ddxxb.setWllxdh(express.getPhone());// wllxdh 物流联系电话 varchar(30)
			ddxxb.setWlgzjllist(new ArrayList<Ddxxbwl>());
			if (CollectionUtils.isNotEmpty(express.getExpressWays())){
				for (ExpressWay way : express.getExpressWays()){
					Ddxxbwl ddxxbwl = new Ddxxbwl();
					ddxxbwl.transformObject(way, ddxxbwl);
					ddxxb.getWlgzjllist().add(ddxxbwl);
				}
			}
		}
		
		//验收信息
		Acceptance acceptance=order.getAcceptance();
		if(null!=acceptance){
			ddxxb.setYszt(acceptance.getStatus().ordinal());// yszt 验收状态 Integer
			ddxxb.setYssj(DateUtils.format(acceptance.getCreateTime(), DateUtils.FT_LONG_DATE_TIME));// yssj 验收时间 Datetime
			ddxxb.setYsrzh(acceptance.getCreateOperator().getLoginName());// ysrzh 验收人账号
			ddxxb.setYsrxm(acceptance.getCreateOperator().getEmployee().getName());// ysrxm 验收人姓名
			ddxxb.setYsrdh(acceptance.getCreateOperator().getEmployee().getPhone());// ysrdh 验收人电话 varchar(30)
			ddxxb.setYsqksm(acceptance.getExplains());// ysqksm 验收情况说明 varchar(500)
		}
		
		//安装信息
		InstallationInfo installationInfo=order.getInstallationInfo();
		if(null!=installationInfo){
			ddxxb.setAzzt(installationInfo.getStatus().ordinal());// azzt 安装状态 Integer
			ddxxb.setAzsj(DateUtils.format(installationInfo.getTime(), DateUtils.FT_LONG_DATE_TIME));// azsj 安装时间 Datetime
			ddxxb.setAzrxm(installationInfo.getInstaller());// azrxm 安装人 varchar(30)
			ddxxb.setAzrdh(installationInfo.getPhone());// azrdh 安装人电话 varchar(30)
			ddxxb.setAzqksm(installationInfo.getExplains());// azqksm 安装情况说明 varchar(500)
			ddxxb.setAztplist(new ArrayList<Ddxxbazpic>());// aztplist 安装图片列表 JSON
			if (CollectionUtils.isNotEmpty(installationInfo.getImages())){
				for (InstallationImage image : order.getInstallationInfo().getImages()){
					Ddxxbazpic ddxxbazpic = new Ddxxbazpic();
					ddxxbazpic.transformObject(image, ddxxbazpic);
					ddxxbazpic.setTpdz(resourceContextHolder.replaceContent(image.getUrl()));
					ddxxb.getAztplist().add(ddxxbazpic);
				}
			}
		}
		
		// clmxjllist 订单明细列表 JSON 数组、多条记录
		ddxxb.setClmxjllist(new ArrayList<DdxxbClmxjl>());
		if (CollectionUtils.isNotEmpty(order.getOrderDetails())){
			for (OrderDetails orderDetails : order.getOrderDetails()){
				DdxxbClmxjl clmxjl = new DdxxbClmxjl();
				clmxjl.transformObject(orderDetails, clmxjl);
				clmxjl.setShyszt(orderDetails.getAcceptanceStatus().ordinal());
				// shcszplist 收货验收照片列表 JSON 数组、多条记录
				clmxjl.setShcszplist(new ArrayList<DdxxbClmxjlPic>());
				if (CollectionUtils.isNotEmpty(orderDetails.getImages())){
					for (AcceptanceImage image : orderDetails.getImages()){
						DdxxbClmxjlPic clmxjlPic = new DdxxbClmxjlPic();
						clmxjlPic.transformObject(image, clmxjlPic);
						clmxjlPic.setTpdz(resourceContextHolder.replaceContent(image.getUrl()));
						clmxjl.getShcszplist().add(clmxjlPic);
					}
				}
				ddxxb.getClmxjllist().add(clmxjl);
			}
		}

//		ddxxb.setGcbh(gcbh);// gcbh 工程编号 varchar(10)
//		ddxxb.setGcmc("工程名称1");// gcmc 工程名称 varchar(40)
//		ddxxb.setDdbh("201401020001");// ddbh 定单编号 varchar(30)
//		ddxxb.setDdmc("木工材料采购定单");// ddmc 定单名称 varchar(30)
//		ddxxb.setDdsj("20150801120121");// ddsj 定单时间 Datetime
//		ddxxb.setDdsm("定单说明文字 ");// ddsm 定单说明 varchar(100)
//		ddxxb.setXdrzh("1001");// xdrzh 下单人账号 varchar(30)
//		ddxxb.setXdrxm("张三");// xdrxm 下单人姓名 varchar(30)
//		ddxxb.setDdzt(1);// ddzt 定单状态 Integer
//		ddxxb.setDdzl(1);// ddzl 定单种类 Integer
//		// clmxjllist 材料明细记录 JSON 定单的材料明细。// 数组、多条记录
//		ddxxb.setGhsbh("901102");// ghsbh 供货商编号 varchar(20)
//		ddxxb.setGhsmc("福湘材料厂直销");// ghsmc 供货商名称 varchar(80)
//		ddxxb.setGhslxr("陈老板");// ghslxr 供货商联系人 varchar(30)
//		ddxxb.setGhslxdh("07318911001");// ghslxdh 供货商联系电话 varchar(20)
//		ddxxb.setWlzt(0);// wlzt 物流状态 Integer
//		ddxxb.setWlsj("20150102120100");// wlsj 物流时间 Datetime
//		ddxxb.setWlmc("天天快递");// wlmc 物流名称 varchar(80)
//		ddxxb.setWldh("9872201029");// wldh 物流单号 varchar(50)
//		ddxxb.setWllxr("物流联系人");// wllxr 物流联系人 varchar(30)
//		ddxxb.setWllxdh("13988010101");// wllxdh 物流联系电话 varchar(30)
		// wlgzjllist 物流跟踪记录列表 JSON 数组、多条记录
//		ddxxb.setWlgzjllist(new ArrayList<Ddxxbwl>());
		// wlgzjllist (json)
		// ddbh 定单编号 varchar(30)
		// wlgxid 物流更新标识 integer
		// wlgxsj 物流更新时间 datetime
		// wlgxzt 物流更新状态 varchar(100)
//		Ddxxbwl ddxxbwl = new Ddxxbwl();
//		ddxxbwl.setDdbh(ddxxb.getDdbh());
//		ddxxbwl.setWlgxid(1);
//		ddxxbwl.setWlgxsj("20150101120122");
//		ddxxbwl.setWlgxzt("已打包");
//		ddxxb.getWlgzjllist().add(ddxxbwl);
//		ddxxbwl = new Ddxxbwl();
//		ddxxbwl.setDdbh(ddxxb.getDdbh());
//		ddxxbwl.setWlgxid(2);
//		ddxxbwl.setWlgxsj("20150101120122");
//		ddxxbwl.setWlgxzt("已经发货到快递公司");
//		ddxxb.getWlgzjllist().add(ddxxbwl);
//
//		ddxxb.setYszt(0);// yszt 验收状态 Integer
//		ddxxb.setYssj("20150102120100");// yssj 验收时间 Datetime
//		ddxxb.setYsrzh(session.getUserInfo().getLoginName());// ysrzh 验收人账号
//																// varchar(30)
//		ddxxb.setYsrxm(session.getUserInfo().getName());// ysrxm 验收人姓名
//														// varchar(30)
//		ddxxb.setYsrdh("验收人电话");// ysrdh 验收人电话 varchar(30)
//		ddxxb.setYsqksm(" 验收情况说明 ");// ysqksm 验收情况说明 varchar(500)
//
//		ddxxb.setAzzt(0);// azzt 安装状态 Integer
//		ddxxb.setAzsj("20150901120100");// azsj 安装时间 Datetime
//		ddxxb.setAzrxm("刘师付");// azrxm 安装人 varchar(30)
//		ddxxb.setAzrdh("18901010101");// azrdh 安装人电话 varchar(30)
//		ddxxb.setAzqksm("安装正确");// azqksm 安装情况说明 varchar(500)
//		ddxxb.setAztplist(new ArrayList<Ddxxbazpic>());// aztplist 安装图片列表 JSON
//
//		// 图片列表，数组多条记录。
//		// aztplist (json)
//		// ddbh 定单编号 varchar(30)
//		// tpid 图片标识 Integer
//		// tpdz 图片地址 varchar(200)
//		Ddxxbazpic ddxxbazpic = new Ddxxbazpic();
//		ddxxbazpic.setDdbh(ddxxb.getDdbh());
//		ddxxbazpic.setTpid(1);
//		ddxxbazpic.setTpdz("http://www.baidu.com/img/bd_logo1.png");
//		ddxxb.getAztplist().add(ddxxbazpic);
//		ddxxbazpic = new Ddxxbazpic();
//		ddxxbazpic.setDdbh(ddxxb.getDdbh());
//		ddxxbazpic.setTpid(2);
//		ddxxbazpic.setTpdz("http://www.baidu.com/img/bd_logo1.png");
//		ddxxb.getAztplist().add(ddxxbazpic);
//
//		ddxxb.setClmxjllist(new ArrayList<DdxxbClmxjl>());
//		// clmxjllist (json)
//		DdxxbClmxjl clmxjl = new DdxxbClmxjl();
//		clmxjl.setDdbh(ddxxb.getDdbh());// ddbh 定单编号 varchar(30)
//		clmxjl.setDdclid(1);// ddclid 定单材料标识 integer
//		clmxjl.setClbh("90101");// clbh 材料编号 varchar(10)
//		clmxjl.setClmc("大芯板");// clmc 材料名称 varchar(80)
//		clmxjl.setClms("E0级金福湘大");// clms 材料描述 varchar(500)
//		clmxjl.setSlyq("10块");// slyq 数量要求 varchar(80)
//		clmxjl.setSccj("生产厂家");// sccj 生产厂家 varchar(120)
//		clmxjl.setScpj("生产批次");// scpj 生产批次 varchar(120)
//		clmxjl.setSjclcs("E0级金福湘大");// sjclcs 实际材料参数 varchar(500)
//		clmxjl.setShyszt(0);// shyszt 收货验收状态 integer
//		clmxjl.setShyssm("收货验收说明");// shyssm 收货验收说明 varchar(500)
//		// shcszplist 收货验收照片列表 JSON 数组、多条记录
//		clmxjl.setShcszplist(new ArrayList<DdxxbClmxjlPic>());
//		// shcszplist (json)
//		DdxxbClmxjlPic clmxjlPic = new DdxxbClmxjlPic();
//		clmxjlPic.setDdclid(clmxjl.getDdclid());// ddclid 定单材料标识 integer
//		clmxjlPic.setTpid(1);// tpid 图片标识 Integer
//		clmxjlPic.setTpdz("http://www.baidu.com/img/bd_logo1.png");// tpdz 图片地址
//																	// varchar(200)
//		clmxjl.getShcszplist().add(clmxjlPic);
//		clmxjlPic = new DdxxbClmxjlPic();
//		clmxjlPic.setDdclid(clmxjl.getDdclid());// ddclid 定单材料标识 integer
//		clmxjlPic.setTpid(2);// tpid 图片标识 Integer
//		clmxjlPic.setTpdz("http://www.baidu.com/img/bd_logo1.png");// tpdz 图片地址
//																	// varchar(200)
//		clmxjl.getShcszplist().add(clmxjlPic);
//		ddxxb.getClmxjllist().add(clmxjl);
//
//		// ================第二条材料明细记录
//		clmxjl = new DdxxbClmxjl();
//		clmxjl.setDdbh(ddxxb.getDdbh());// ddbh 定单编号 varchar(30)
//		clmxjl.setDdclid(2);// ddclid 定单材料标识 integer
//		clmxjl.setClbh("90101");// clbh 材料编号 varchar(10)
//		clmxjl.setClmc("面板");// clmc 材料名称 varchar(80)
//		clmxjl.setClms("E0级金福湘大");// clms 材料描述 varchar(500)
//		clmxjl.setSlyq("10块");// slyq 数量要求 varchar(80)
//		clmxjl.setSccj("生产厂家");// sccj 生产厂家 varchar(120)
//		clmxjl.setScpj("生产批次");// scpj 生产批次 varchar(120)
//		clmxjl.setSjclcs("E0级金福湘大");// sjclcs 实际材料参数 varchar(500)
//		clmxjl.setShyszt(0);// shyszt 收货验收状态 integer
//		clmxjl.setShyssm("收货验收说明");// shyssm 收货验收说明 varchar(500)
//		// shcszplist 收货验收照片列表 JSON 数组、多条记录
//		clmxjl.setShcszplist(new ArrayList<DdxxbClmxjlPic>());
//		// shcszplist (json)
//		clmxjlPic = new DdxxbClmxjlPic();
//		clmxjlPic.setDdclid(clmxjl.getDdclid());// ddclid 定单材料标识 integer
//		clmxjlPic.setTpid(1);// tpid 图片标识 Integer
//		clmxjlPic.setTpdz("http://www.baidu.com/img/bd_logo1.png");// tpdz 图片地址
//																	// varchar(200)
//		clmxjl.getShcszplist().add(clmxjlPic);
//		clmxjlPic = new DdxxbClmxjlPic();
//		clmxjlPic.setDdclid(clmxjl.getDdclid());// ddclid 定单材料标识 integer
//		clmxjlPic.setTpid(2);// tpid 图片标识 Integer
//		clmxjlPic.setTpdz("http://www.baidu.com/img/bd_logo1.png");// tpdz 图片地址
//																	// varchar(200)
//		clmxjl.getShcszplist().add(clmxjlPic);
//		ddxxb.getClmxjllist().add(clmxjl);

		responsePacket.putSdata("ddxxb", ddxxb);

		return responsePacket;

	}

	/**
	 * 获取某一条材料的详细信息。
	 * 
	 * 5.2.8.1. 请求地址 /appi/salesman/getOrderInfoByProjectMxClxx .ws
	 * 
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getOrderInfoByProjectMxClxx.ws")
	@ResponseBody
	public Object getOrderInfoByProjectMxClxx(HttpServletRequest request) {
		AppiRequestPacket requestPacket = getRequestPacket(request);
		try {
			ctx.checkSession(request);
		} catch (SessionCheckException ex) {
		    return nologin();
		}

		AppiResponsePacket responsePacket = succ("操作成功！");

		String ddclmxid = requestPacket.getParameter("ddclmxid");
		if (StringUtils.isBlank(ddclmxid))
			return fail("订单材料明细标识不能为空");

		OrderDetails orderDetails = orderDetailsService.findOne(Integer.parseInt(ddclmxid));

		DdxxbClmxjl clmxjl  = new DdxxbClmxjl();
		clmxjl.transformObject(orderDetails, clmxjl);
		clmxjl.setShyszt(orderDetails.getAcceptanceStatus().ordinal());// shyszt 收货验收状态 integer
		// shcszplist 收货验收照片列表 JSON 数组、多条记录
		clmxjl.setShcszplist(new ArrayList<DdxxbClmxjlPic>());
		// shcszplist (json)
		for (AcceptanceImage image : orderDetails.getImages()){
			DdxxbClmxjlPic clmxjlPic = new DdxxbClmxjlPic();
			clmxjlPic.setTpid(image.getId());// tpid 图片标识 Integer
			clmxjlPic.setTpdz(this.resourceContextHolder.replaceContent(image.getUrl()));// tpdz 图片地址
			clmxjl.getShcszplist().add(clmxjlPic);
		}
		responsePacket.putSdata("clmxjl", clmxjl);
		return responsePacket;
	}

	
    /**
    * 5.2.9.上传材料明细验收信息   请求地址
    * /appi/salesman/saveCheckByOrderList.ws
    * 
    * 
    * @param request
    * @return
    */
    @RequestMapping("saveCheckByOrderList.ws")
    @ResponseBody
    public Object saveCheckByOrderList(HttpServletRequest request) {
        AppiSession appiSession = null;
        AppiRequestPacket requestPacket = getRequestPacket(request);
        try {
            appiSession = ctx.checkSession(request);
        } catch (SessionCheckException ex) {
            return nologin();
        }

        AppiResponsePacket responsePacket = succ("操作成功！");

        String ddclmxid = requestPacket.getParameter("ddclmxid");
        if (StringUtils.isBlank(ddclmxid))
            return fail("订单材料明细标识不能为空");

        if (StringUtils.isBlank(requestPacket.getParameter("shyszt")))
            return fail("收货验收状态不能为空");

        Integer shyszt = Integer.parseInt(requestPacket.getParameter("shyszt"));
        Acceptance.AcceptanceEnum status = null;
        if (shyszt.equals(0))
            status = Acceptance.AcceptanceEnum.WITHOUT;
        if (shyszt.equals(1))
            status = Acceptance.AcceptanceEnum.NORMAL;
        if (shyszt.equals(2))
            status = Acceptance.AcceptanceEnum.ABNORMAL;
        if (shyszt.equals(3))
            status = Acceptance.AcceptanceEnum.REFUSE;

        OrderDetails orderDetails = this.orderDetailsService.findOne(Integer.parseInt(ddclmxid));
        orderDetails.setAcceptanceStatus(status);
        orderDetails.setAcceptanceExplain(request.getParameter("shyssm"));
        orderDetailsService.saveAppi(orderDetails, appiSession);

        responsePacket.putSdata("ddclmxid", orderDetails.getId());

        return responsePacket;
    }
    
    
    /**
    * 5.2.10.上传材料明细验收图片信息
    * /appi/salesman/saveCheckByOrderListPic.ws
    * 
    * 
    * @param request
    * @return
    */
    @RequestMapping("saveCheckByOrderListPic.ws")
    @ResponseBody
    public Object saveCheckByOrderListPic(HttpServletRequest request) {
        AppiSession appiSession = null;
        AppiRequestPacket requestPacket = getRequestPacket(request);
        try {
            appiSession = ctx.checkSession(request);
        } catch (SessionCheckException ex) {
            return nologin();
        }

        AppiResponsePacket responsePacket = succ("操作成功！");

        String ddclmxid = requestPacket.getParameter("ddclmxid");
        if (StringUtils.isBlank(ddclmxid))
            return fail("定单材料明细标识不能为空");

        //上传文件
        FileInfoDTO info=null;
        try {
            info=saveFile(request);
       } catch (IOException e) {
           return fail(e.getMessage());
       }

        OrderDetails orderDetails = orderDetailsService.findOne(Integer.parseInt(ddclmxid));
        AcceptanceImage acceptanceImage=new AcceptanceImage();
        acceptanceImage.setOrderDetails(orderDetails);
        acceptanceImage.setUrl(info.getUrl());
        acceptanceImageService.saveAppi(acceptanceImage, appiSession);
        
        responsePacket.putSdata("fileid", requestPacket.getParameter("fileid"));
        
        return responsePacket;
    }
    
    
    /**
    * 5.2.11.上传材料定单验收信息
    * /appi/salesman/saveCheckByOrder.ws
    * 
    * 
    * @param request
    * @return
    */
    @RequestMapping("saveCheckByOrder.ws")
    @ResponseBody
    public Object saveCheckByOrder(HttpServletRequest request) {
        AppiSession appiSession = null;
        AppiRequestPacket requestPacket = getRequestPacket(request);
        try {
            appiSession = ctx.checkSession(request);
        } catch (SessionCheckException ex) {
            return nologin();
        }

        AppiResponsePacket responsePacket = succ("操作成功！");

        String ddbh = requestPacket.getParameter("ddbh");
        if (StringUtils.isBlank(ddbh))
            return fail("定单编号不能为空");
        
        if (StringUtils.isBlank(requestPacket.getParameter("yszt")))
            return fail("验收状态不能为空");

        Integer yszt = Integer.parseInt(requestPacket.getParameter("yszt"));
        Acceptance.AcceptanceEnum status = null;
        if (yszt.equals(0))
            status = Acceptance.AcceptanceEnum.WITHOUT;
        if (yszt.equals(1))
            status = Acceptance.AcceptanceEnum.NORMAL;
        if (yszt.equals(2))
            status = Acceptance.AcceptanceEnum.ABNORMAL;
        if (yszt.equals(3))
            status = Acceptance.AcceptanceEnum.REFUSE;

        Order order = orderService.findByProperty("code", ddbh);
        Acceptance acceptance=order.getAcceptance();
        if(null==acceptance){
            acceptance=new Acceptance();
            acceptance.setOrder(order);
        }
        acceptance.setStatus(status);
        acceptance.setExplains(requestPacket.getParameter("yssm"));
        order.setAcceptance(acceptance);
        acceptanceService.saveAppi(acceptance, appiSession);

        return responsePacket;
    }

    
    /**
    * 5.2.12.上传定单安装信息
    * /appi/salesman/saveCheckByOrderAz.ws
    * 
    * 
    * @param request
    * @return
    */
    @RequestMapping("saveCheckByOrderAz.ws")
    @ResponseBody
    public Object saveCheckByOrderAz(HttpServletRequest request) {
        AppiSession appiSession = null;
        AppiRequestPacket requestPacket = getRequestPacket(request);
        try {
            appiSession = ctx.checkSession(request);
        } catch (SessionCheckException ex) {
            return nologin();
        }

        AppiResponsePacket responsePacket = succ("操作成功！");

        String ddbh = requestPacket.getParameter("ddbh");
        if (StringUtils.isBlank(ddbh))
            return fail("定单编号不能为空");
        
        if (StringUtils.isBlank(requestPacket.getParameter("azzt")))
            return fail("安装状态不能为空");

        Integer azzt = Integer.parseInt(requestPacket.getParameter("azzt"));
        InstallationInfo.InstallationStatusEnum status = null;
        if (azzt.equals(0))
            status = InstallationInfo.InstallationStatusEnum.NOTINSTALL;
        if (azzt.equals(1))
            status = InstallationInfo.InstallationStatusEnum.INSTALLED;
        if (azzt.equals(2))
            status = InstallationInfo.InstallationStatusEnum.COMPLETE;
        if (azzt.equals(3))
            status = InstallationInfo.InstallationStatusEnum.NONEED;
        
        String azsj=requestPacket.getParameter("azsj");
        if (StringUtils.isBlank(azsj))
            return fail("安装时间不能为空");
        
        String azrxm=requestPacket.getParameter("azrxm");
        if (StringUtils.isBlank(azrxm))
            return fail("安装人不能为空");
        
        Order order = orderService.findByProperty("code", ddbh);
        InstallationInfo installationInfo=order.getInstallationInfo();
        if(null==installationInfo){
            installationInfo=new InstallationInfo();
            installationInfo.setOrder(order);
        }
        installationInfo.setStatus(status);
        installationInfo.setTime(DateUtils.parseToTimestamp(azsj, DateUtils.FT_LONG_DATE_TIME));
        installationInfo.setInstaller(azrxm);
        installationInfo.setPhone(requestPacket.getParameter("azrdh"));
        installationInfo.setExplains(requestPacket.getParameter("azqksm"));
        installationInfoService.saveAppi(installationInfo, appiSession);

        return responsePacket;
    }
    
    
    /**
    * 5.2.13.上传定单安装图片信息
    * /appi/salesman/saveCheckByOrderAzPic.ws
    * 
    * 
    * @param request
    * @return
    */
    @RequestMapping("saveCheckByOrderAzPic.ws")
    @ResponseBody
    public Object saveCheckByOrderAzPic(HttpServletRequest request) {
        AppiSession appiSession = null;
        AppiRequestPacket requestPacket = getRequestPacket(request);
        try {
            appiSession = ctx.checkSession(request);
        } catch (SessionCheckException ex) {
            return nologin();
        }

        AppiResponsePacket responsePacket = succ("操作成功！");

        String ddbh = requestPacket.getParameter("ddbh");
        if (StringUtils.isBlank(ddbh))
            return fail("订单编号不能为空");

        //上传文件
        FileInfoDTO info=null;
        try {
            info=saveFile(request);
       } catch (IOException e) {
           return fail(e.getMessage());
       }

        Order order = orderService.findByProperty("code", ddbh);
        InstallationInfo installationInfo=order.getInstallationInfo();
        InstallationImage installationImage=new InstallationImage();
        installationImage.setInstallationInfo(installationInfo);
        installationImage.setUrl(info.getUrl());
        order.setInstallationInfo(installationInfo);
        orderService.saveAppi(order, appiSession);

        return responsePacket;
    }
}
