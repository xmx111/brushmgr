package com.ufo.appi.web.custom;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.ufo.appi.dto.Htxxb;
import com.ufo.appi.dto.Khjbxx;
import com.ufo.config.sys.entity.Manager.ManagerEnum;
import com.ufo.project.contract.entity.Contract;
import com.ufo.project.contract.service.interfaces.IContractService;

/**
 * 用户中心控制类 会员中心接口：上传个人资料
 * 
 * @author appdev
 *
 */
@Controller("appi.custom.user.Controller")
@RequestMapping("/appi/custom/user")
public class AppiCustomUserController extends AppiCustomController {

    @Autowired
    private IContractService contractService;

	/**
	 * 5.3.11. 获取用户资料 手机端在登录成功以后或在打开用户中心时，从后台读取用户的基本资料。资料内容包括用户基本属性和所管理的工程清单。
	 * 5.3.11.1. 请求地址 /appi/custom/user/getUserInfoByUserName.ws 5.3.11.2.
	 * SJSONP userinfocallback 5.3.11.3. 请求参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 无
	 * 示例：
	 * 
	 * 5.3.11.4. 响应参数 参数 参数名称 类型（长度） 参数说明 是否为空 样例 公共参数 sdata(json) khjbxx 客户基本信息
	 * JSON 用户对象 单条记录 否 khjbxx:{} htxxb 合同信息表 JSON 项目列表 数组、多条记录 否 htxxb:[{},{}]
	 * 
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getUserInfoByUserName.ws")
	@ResponseBody
	public Object getUserInfoByUserName(HttpServletRequest request) {
		AppiRequestPacket requestPacket = getRequestPacket(request);
		AppiSession session = null;
		try {
			session = ctx.checkSession(request);
		} catch (SessionCheckException ex) {
		    return nologin();
		}

		AppiResponsePacket responsePacket = succ("操作成功！");

		// 获取登录时保存在会话中的基本信息
		Khjbxx khjbxx = (Khjbxx) session.getAttribute("khjbxx");
		if (khjbxx != null) {
			responsePacket.putSdata("khjbxx", khjbxx);
		}

		// 读取该客户编号与关联管理的合同信息列表/
		// 根据工程关联信息表中存储的客户编号查询
        ArrayList<Htxxb> htxxbList = new ArrayList<Htxxb>();
        List<Contract> list=contractService.findByUser(ManagerEnum.CUSTOM, session.getUserInfo().getCode());
        for (Iterator<Contract> iterator = list.iterator(); iterator.hasNext();) {
            Contract contract = (Contract) iterator.next();
            Htxxb htxxb = new Htxxb();
            htxxb.transformObject(contract, htxxb);
            htxxb.setHtlx(contract.getType().ordinal());
            htxxbList.add(htxxb);
        }
        responsePacket.setTotal(htxxbList.size());
		responsePacket.putSdata("htxxb", htxxbList);
		return responsePacket;
	}
}
