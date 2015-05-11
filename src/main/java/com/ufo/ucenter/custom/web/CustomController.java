package com.ufo.ucenter.custom.web;

import javax.servlet.http.HttpServletRequest;

import com.ufo.config.sys.entity.Manager;
import com.ufo.config.sys.service.interfaces.IManagerService;
import com.ufo.core.pagination.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.ucenter.custom.entity.Custom;
import com.ufo.ucenter.custom.service.interfaces.ICustomService;
import com.ufo.core.annotation.Description;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;

import java.util.Collection;
import java.util.List;

@Controller("ucenterCustomCustomController")
@RequestMapping("/ucenter/custom/")
@Description(code = "ucenter.custom", value = "客户基本信息设置")
@Secured("ucenter.custom")
public class CustomController extends PersistableController<Custom, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/ucenter/custom/";

    @Autowired
    private ICustomService customService;

    @Autowired
    private IManagerService managerService;

    @Override
    public IBaseSpringDataService<Custom, Integer> getEntityService() {
        return customService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("ucenter.custom.index")
    public String index(HttpServletRequest request) {
        return this.toView("custom-index");
    }

    @RequestMapping("inputTabs.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "ucenter.custom.add", "ucenter.custom.edit" })
    public String inputTabs(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE)
            map.put("id", id);
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("custom-inputTabs");
    }

    @RequestMapping("edit.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "ucenter.custom.add", "ucenter.custom.edit" })
    public String edit(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE){
            map.put("dto", detail(id));
            map.put("id", id);
        }
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("custom-inputBasic");
    }
    
    @RequestMapping("findByPage.json")
    @Description("列表查询")
    @Secured("ucenter.custom.index")
    @ResponseBody
    public Object findByPage(HttpServletRequest request) {
        return super.findByPage(request);
    }
    
    @RequestMapping("update.json")
    @Description("编辑数据")
    @Secured("ucenter.custom.edit")
    @ResponseBody
    public Object update(Custom dto) {
        return super.save(dto);
    }
    
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "ucenter.custom.add", "ucenter.custom.edit" })
    @ResponseBody
    public Object save(Custom dto) {
        if (dto.getId() == null){
            dto.setRegisterOperator(operation());
            dto.setRegisterName(operation().getName());
            dto.setRegisterTime(getCurrentTime());
            dto.setRegisterType(Custom.RegisterEnum.SERVER);

            customService.save(dto);

            Manager manager = new Manager();
            manager.setCustom(dto);
            manager.setLoginName(dto.getPhone());
            manager.setType(Manager.ManagerEnum.CUSTOM);
            manager.setCode(dto.getCode());
            manager.setName(dto.getName());
            manager.setPassword(dto.getPhone().length() > 7 ? dto.getPhone().substring(dto.getPhone().length() - 7) : dto.getPhone());
            managerService.save(manager);
            return restSuccess(dto);
        } else {
            Custom entity = detail(dto.getId());
            dto.setRegisterOperator(entity.getRegisterOperator());
            dto.setRegisterName(entity.getRegisterName());
            dto.setRegisterTime(entity.getRegisterTime());
            dto.setRegisterType(entity.getRegisterType());
        }
        return super.save(dto);
    }

    @RequestMapping("delete.json")
    @Description("删除数据")
    @Secured("ucenter.custom.delete")
    @ResponseBody
    public Object delete(HttpServletRequest request) {

        Collection<Custom> entities = this.getEntitiesByParameterIds(request);
        for (Custom entity : entities) {
            List<Manager> managers = managerService.findByFilter(new PropertyFilter(PropertyFilter.MatchType.EQ, "custom", entity));
            if (managers != null)
                managerService.delete(managers);
        }

        return super.delete(request, true);
    }

    @RequestMapping("select.htm")
    public Object select() {
        return this.toView("custom-select");
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
