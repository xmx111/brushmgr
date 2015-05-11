package com.ufo.project.contract.web;

import com.ufo.core.annotation.Description;
import com.ufo.core.entity.UndeleteEntity;
import com.ufo.core.pagination.GroupPropertyFilter;
import com.ufo.core.pagination.PropertyFilter;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;
import com.ufo.project.contract.entity.Contract;
import com.ufo.project.contract.service.interfaces.IContractService;
import com.ufo.project.drawing.entity.Drawing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller("projectContractContractController")
@RequestMapping("/project/contract/")
@Description(code = "project.contract", value = "材料基础信息设置")
@Secured("project.contract")
public class ContractController extends PersistableController<Contract, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/project/contract/";

    @Autowired
    private IContractService contractService;

    @Override
    public IBaseSpringDataService<Contract, Integer> getEntityService() {
        return contractService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("project.contract.index")
    public String index(HttpServletRequest request) {
        return this.toView("contract-index");
    }

    @RequestMapping("inputTabs.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.contract.add", "project.contract.edit" })
    public String inputTabs(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE)
            map.put("id", id);
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("contract-inputTabs");
    }

    @RequestMapping("edit.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.contract.add", "project.contract.edit" })
    public String edit(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE){
            map.put("dto", detail(id));
            map.put("id", id);
        } else {
            map.put("engineeringCode", contractService.genCanUseEngineeringCode());
        }
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("contract-inputBasic");
    }
    
    @RequestMapping("findByPage.json")
    @Description("列表查询")
    @Secured("project.contract.index")
    @ResponseBody
    public Object findByPage(HttpServletRequest request) {
        Page<Contract> page = (Page<Contract>)super.findByPage(request);
        for (Contract contract : page.getContent()){
            contract.setCreateOperatorName(contract.getCreateOperator().getName());
            if (contract.getUpdateOperator() != null)contract.setUpdateOperatorName(contract.getUpdateOperator().getName());
        }
        return page;
    }
    
    @RequestMapping("update.json")
    @Description("编辑数据")
    @Secured("project.contract.edit")
    @ResponseBody
    public Object update(Contract dto) {
        return super.save(dto);
    }
    
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.contract.add", "project.contract.edit" })
    @ResponseBody
    public Object save(Contract dto) {
        if (dto.getEmployee() != null && (dto.getEmployee().getId() == null || dto.getEmployee().getId() == Integer.MIN_VALUE)){
            dto.setEmployee(null);
        }
        return super.save(dto);
    }

    @RequestMapping("delete.json")
    @Description("删除数据")
    @Secured("project.contract.delete")
    @ResponseBody
    public Object delete(HttpServletRequest request) {
        return super.delete(request);
    }

    @RequestMapping("select.htm")
    public Object select() {
        return this.toView("contract-select");
    }

    @RequestMapping("select.json")
    @ResponseBody
    public Object selectJson(String engineeringName, Integer page) {
        Pageable pageObj = new PageRequest(page - 1, 10, null);
        GroupPropertyFilter groupPropertyFilter = GroupPropertyFilter.buildDefaultAndGroupFilter(new PropertyFilter(PropertyFilter.MatchType.CN, "engineeringName", engineeringName));
        groupPropertyFilter.append(new PropertyFilter(PropertyFilter.MatchType.EQ, "deleted", UndeleteEntity.DeleteTypeEnum.UNDELETE));
        return this.getEntityService().findByPage(groupPropertyFilter, pageObj);
    }

    @RequestMapping("selectByCode.json")
    @ResponseBody
    public Object selectByCode(String engineeringCode, Integer page) {
        Pageable pageObj = new PageRequest(page - 1, 10, null);
        GroupPropertyFilter groupPropertyFilter = GroupPropertyFilter.buildDefaultAndGroupFilter(new PropertyFilter(PropertyFilter.MatchType.CN, "engineeringCode", engineeringCode));
        groupPropertyFilter.append(new PropertyFilter(PropertyFilter.MatchType.EQ, "deleted", UndeleteEntity.DeleteTypeEnum.UNDELETE));
        return this.getEntityService().findByPage(groupPropertyFilter, pageObj);
    }
    
    @RequestMapping("buildValidateRules.json")
    @ResponseBody
    public Object buildValidateRules() {
        return super.buildValidateRules();
    }

    @RequestMapping("checkUnique.json")
    @ResponseBody
    public Boolean checkUnique(HttpServletRequest request) {
        return super.checkUnique(request);
    }
    
    /** 
     * 重写方法 
     * @see com.ufo.core.web.AbstractBaseController#getModelPath() 
     */
    @Override
    protected String getModelViewPath() {
        return VIEWPATH;
    }
    
}
