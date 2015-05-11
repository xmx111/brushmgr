package com.ufo.appi.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.appi.context.AppiSession;
import com.ufo.appi.context.SessionCheckException;
import com.ufo.appi.core.AppiBaseController;
import com.ufo.appi.core.AppiRequestPacket;
import com.ufo.appi.core.AppiResponsePacket;
import com.ufo.appi.dto.ApiParam;
import com.ufo.appi.dto.Gysjbxx;
import com.ufo.appi.dto.Htxxb;
import com.ufo.appi.dto.Khjbxx;
import com.ufo.appi.dto.Ygjbxx;
import com.ufo.config.sys.entity.Manager;
import com.ufo.config.sys.service.interfaces.IManagerService;
import com.ufo.core.dto.FileInfoDTO;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.utils.RequestUtils;
import com.ufo.core.utils.StringUtils;
import com.ufo.project.contract.entity.Contract;
import com.ufo.project.contract.service.interfaces.IContractService;
import com.ufo.ucenter.custom.entity.Custom;
import com.ufo.ucenter.employee.entity.Employee;
import com.ufo.ucenter.supplier.entity.Supplier;

/**
 * 类名称: 类描述:
 * <p/>
 * 创建人: hekang 创建时间: 上午9:17
 *
 * @verion 1.0
 */
@Controller("appiController")
@RequestMapping("/appi/passport/")
public class AppiController extends AppiBaseController<Manager, Integer> {
	@Autowired
	private IContractService contractService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected IBaseSpringDataService<Manager, Integer> getEntityService() {
		return null;
	}

	@RequestMapping("login.ws")
	@ResponseBody
	public Object login(HttpServletRequest request) {
		AppiRequestPacket requestPacket = getRequestPacket(request);

		String username = requestPacket.getParameter("username");
		String password = requestPacket.getParameter("password");
		try {
			if (StringUtils.isBlank(username))
				return fail("账号名不能为空");
			if (StringUtils.isBlank(password))
				return fail("密码不能为空");

			Manager manager = managerService.findByLoginName(username);
			if (manager == null) {
				return fail("账号不存在！");
			}
			if (!passwordEncoder.encodePassword(password, null).equals(
					manager.getPassword())) {
				return fail("密码错误！");
			}
			if (!manager.getStatus().equals(Manager.ManagerStatusEnum.NORMAL)) {
				return fail("您的账号处理非正常状态，无法登录本系统！");
			}

            AppiResponsePacket responsePacket = succ("登录成功！");

            String sappver=requestPacket.getSappver();
            String sappid=requestPacket.getSappid();
            if(StringUtils.isBlank(sappver))
                return fail("应用版本号不能为空!");
            
            if(StringUtils.isBlank(sappid))
                return fail("应用标识不能为空!");

            //判断权限
			if (manager.getType().equals(Manager.ManagerEnum.CUSTOM)
					&& !"2".equalsIgnoreCase(sappid)) {// 客户
				return fail("您无权使用本系统，请与管理员联系！");
			}
			
			if (manager.getType().equals(Manager.ManagerEnum.EMPLOYEE)
					&& !"1".equalsIgnoreCase(sappid)) {// 员工
				return fail("您无权使用本系统，请与管理员联系！");
			}

			if (manager.getType().equals(Manager.ManagerEnum.SUPPLIER)
					&& !"3".equalsIgnoreCase(sappid)) {// 供应商
				return fail("您无权使用本系统，请与管理员联系！");
			}
			
			
			//版本检测
	        if ("2".equalsIgnoreCase(sappid)) {// 客户
	            if(Double.parseDouble(sappver)<Double.parseDouble(resourceContextHolder.getCode_kh_android())){
                    responsePacket.setSstatuscode("202");
                    responsePacket.setSmessage("您的版本过旧,请升级!");
	            }
	        }

	        if ("1".equalsIgnoreCase(sappid)) {// 员工
	            if(Double.parseDouble(sappver)<Double.parseDouble(resourceContextHolder.getCode_cj_android())){
                    responsePacket.setSstatuscode("202");
                    responsePacket.setSmessage("您的版本过旧,请升级!");
	            }
	        }

	        if ("3".equalsIgnoreCase(sappid)) {// 供应商
	            if(Double.parseDouble(sappver)<Double.parseDouble(resourceContextHolder.getCode_wl_android())){
                    responsePacket.setSstatuscode("202");
                    responsePacket.setSmessage("您的版本过旧,请升级!");
	            }
	        }


			// 创建会话
			// 将用户的基本信息存储到会话中
			AppiSession session = ctx.loginNewSession(request);
			session.getUserInfo().setId(manager.getId());
			session.getUserInfo().setLoginName(manager.getLoginName());
			session.getUserInfo().setType(manager.getType().ordinal());
			session.getUserInfo().setCode(manager.getCode());

			responsePacket.putSdata("ssessionid", session.getSessionid());
			responsePacket.putSdata("code", manager.getCode());
			responsePacket.putSdata("id", manager.getId());

            RequestUtils.setAttribute("session_operation", manager);

			if (manager.getType().equals(Manager.ManagerEnum.CUSTOM)) {
				// 查看客户基本信息
			    Custom custom=manager.getCustom();
				Khjbxx khjbxx = new Khjbxx();
				khjbxx.transformObject(custom, khjbxx);
				khjbxx.setTxurl(resourceContextHolder.replaceContent(custom.getHeadUrl()));
				khjbxx.setKhxb(custom.getSex().ordinal());

				responsePacket.putSdata("khjbxx", khjbxx);
				// 保存员工基本信息到session
				session.setAttribute("khjbxx", khjbxx);
			} else if (manager.getType().equals(Manager.ManagerEnum.EMPLOYEE)) {
				// 查询员工基本信息
				Employee employee = manager.getEmployee();
				Ygjbxx ygjbxx = new Ygjbxx();
				ygjbxx.transformObject(employee, ygjbxx);
				ygjbxx.setYglx(employee.getType().ordinal());
				ygjbxx.setTxurl(resourceContextHolder.replaceContent(employee.getHeadUrl()));
				
				responsePacket.putSdata("ygjbxx", ygjbxx);
				// 保存员工基本信息到session
				session.setAttribute("ygjbxx", ygjbxx);
			} else if (manager.getType().equals(Manager.ManagerEnum.SUPPLIER)) {
				// 查看供应商基本信息
			    Supplier supplier=manager.getSupplier();
				Gysjbxx gysjbxx = new Gysjbxx();
				gysjbxx.transformObject(supplier, gysjbxx);

				responsePacket.putSdata("gysjbxx", gysjbxx);
				// 保存供应商基本信息到session
				session.setAttribute("gysjbxx", gysjbxx);
			}

			//员工登录、客户登录时，需要下载合同信息
			if (manager.getType().equals(Manager.ManagerEnum.EMPLOYEE)
					|| manager.getType().equals(Manager.ManagerEnum.CUSTOM)) {
				// 读取该员工编号与关联管理的合同信息列表/
				// 根据工程关联信息表中存储的员工编号查询
				ArrayList<Htxxb> htxxbList = new ArrayList<Htxxb>();
				
				//员工和客户查询合同信息的条件不一致//
				List<Contract> list = contractService.findByUser(manager.getType(),manager.getCode());
				//todo:如果是客户登录，根据客户编号从合同信息表中查询合同列表。
				//todo:如果是员工登录，根据员工编号从合同信息表中的“业务员员工工号”进行关联查询。
				
				for (Iterator<Contract> iterator = list.iterator(); iterator
						.hasNext();) {
					Contract contract = (Contract) iterator.next();
					Htxxb htxxb = new Htxxb();
		            htxxb.transformObject(contract, htxxb);
		            htxxb.setHtlx(contract.getType().ordinal());
					htxxbList.add(htxxb);
				}
				responsePacket.putSdata("htxxb", htxxbList);
			}
			return responsePacket;

		} catch (java.lang.Throwable tx) {
			return excep("系统故障！", tx);
		}
	}

	@RequestMapping("register.ws")
	@ResponseBody
	public Object register(HttpServletRequest request) {
		return null;
	}
	
    @RequestMapping("modifyUserPwd.ws")
    @ResponseBody
    public Object modifyUserPwd(HttpServletRequest request){
        AppiRequestPacket requestPacket = getRequestPacket(request);
        AppiSession session = null;
        try {
            session = ctx.checkSession(request);
        } catch (SessionCheckException ex) {
            return nologin();
        }
        
        AppiResponsePacket responsePacket=succ("操作成功！");
        
        String password = requestPacket.getParameter("password");
        String newpassword = requestPacket.getParameter("newpassword");
        
        Manager manager=managerService.findOne(session.getUserInfo().getId());
        if(StringUtils.isEmpty(password))
            return fail("旧密码不能为空");
        if(!passwordEncoder.encodePassword(password, null).equals(manager.getPassword()))
            return fail("旧密码错误");
        if(StringUtils.isEmpty(newpassword))
            return fail("新密码不能为空");
        
        manager.setPassword(newpassword);
        managerService.save(manager);

        return responsePacket;
    }
    
    
    @RequestMapping("uploadUserPhoto.ws")
    @ResponseBody
    public Object uploadUserPhoto(HttpServletRequest request){
        AppiSession session = null;
        try {
            session = ctx.checkSession(request);
        } catch (SessionCheckException ex) {
            return nologin();
        }
        
        AppiResponsePacket responsePacket=succ("操作成功！");

        Manager manager=managerService.findOne(session.getUserInfo().getId());
        
        //上传文件
        FileInfoDTO info=null;
        try {
            info=saveFile(request);
       } catch (IOException e) {
           return fail(e.getMessage());
       }
        
        if (manager.getType().equals(Manager.ManagerEnum.CUSTOM)) {
            // 查看客户基本信息
            Custom custom=manager.getCustom();
            custom.setHeadUrl(info.getUrl());
            
            Khjbxx khjbxx = (Khjbxx) session.getAttribute("khjbxx");
            khjbxx.setTxurl(resourceContextHolder.replaceContent(info.getUrl()));
            session.setAttribute("khjbxx", khjbxx);   
        } else if (manager.getType().equals(Manager.ManagerEnum.EMPLOYEE)) {
            // 查询员工基本信息
            Employee employee = manager.getEmployee();
            employee.setHeadUrl(info.getUrl());
            
            Ygjbxx ygjbxx = (Ygjbxx) session.getAttribute("ygjbxx");
            ygjbxx.setTxurl(resourceContextHolder.replaceContent(info.getUrl()));
            session.setAttribute("ygjbxx", ygjbxx);  
        } else if (manager.getType().equals(Manager.ManagerEnum.SUPPLIER)) {
            Supplier supplier=manager.getSupplier();
            supplier.setHeadUrl(info.getUrl());
            
            Gysjbxx gysjbxx = (Gysjbxx) session.getAttribute("gysjbxx");
            gysjbxx.setTxurl(resourceContextHolder.replaceContent(info.getUrl()));
            session.setAttribute("gysjbxx", gysjbxx);
        }
        
        managerService.save(manager);

        return responsePacket;
    }
    
    
    @RequestMapping("version.ws")
    @ResponseBody
    public Object version(HttpServletRequest request){
        AppiResponsePacket responsePacket = succ("操作成功！");   
        AppiRequestPacket requestPacket = getRequestPacket(request);
        
        String sappver=requestPacket.getSappver();
        String sappid=requestPacket.getSappid();
        if(StringUtils.isBlank(sappver))
            return fail("应用版本号不能为空!");
        
        if(StringUtils.isBlank(sappid))
            return fail("应用标识不能为空!");
        
        if ("2".equalsIgnoreCase(sappid))// 客户
            responsePacket.putSdata("url", resourceContextHolder.getUrl_kh_android());

        if ("1".equalsIgnoreCase(sappid))// 员工
            responsePacket.putSdata("url", resourceContextHolder.getUrl_cj_android());

        if ("3".equalsIgnoreCase(sappid))// 供应商
            responsePacket.putSdata("url", resourceContextHolder.getUrl_wl_android());

        return responsePacket;
    }


	private ApiParam getParams(HttpServletRequest request) {
		ApiParam apiParam = new ApiParam();
		apiParam.setSappid(request.getParameter("sappid"));
		apiParam.setSsessionid(request.getParameter("ssessionid"));
		apiParam.setSjsonp(request.getParameter("sjsonp"));
		apiParam.setSappid(request.getParameter("stime"));
		return apiParam;
	}
}
