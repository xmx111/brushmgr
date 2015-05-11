package com.ufo.ucenter.employee.web;

import javax.servlet.http.HttpServletRequest;

import com.ufo.config.sys.entity.Manager;
import com.ufo.config.sys.service.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.ucenter.employee.entity.Employee;
import com.ufo.ucenter.employee.service.interfaces.IEmployeeService;
import com.ufo.core.annotation.Description;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;

@Controller("ucenterEmployeeEmployeeController")
@RequestMapping("/ucenter/employee/")
@Description(code = "ucenter.employee", value = "员工基本信息设置")
@Secured("ucenter.employee")
public class EmployeeController extends PersistableController<Employee, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/ucenter/employee/";

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IManagerService managerService;

    @Override
    public IBaseSpringDataService<Employee, Integer> getEntityService() {
        return employeeService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("ucenter.employee.index")
    public String index(HttpServletRequest request) {
        return this.toView("employee-index");
    }

    @RequestMapping("inputTabs.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "ucenter.employee.add", "ucenter.employee.edit" })
    public String inputTabs(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE)
            map.put("id", id);
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("employee-inputTabs");
    }

    @RequestMapping("edit.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "ucenter.employee.add", "ucenter.employee.edit" })
    public String edit(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE){
            map.put("dto", detail(id));
            map.put("id", id);
        }
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("employee-inputBasic");
    }
    
    @RequestMapping("findByPage.json")
    @Description("列表查询")
    @Secured("ucenter.employee.index")
    @ResponseBody
    public Object findByPage(HttpServletRequest request) {
        return super.findByPage(request);
    }
    
    @RequestMapping("update.json")
    @Description("编辑数据")
    @Secured("ucenter.employee.edit")
    @ResponseBody
    public Object update(Employee dto) {
        return super.save(dto);
    }
    
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "ucenter.employee.add", "ucenter.employee.edit" })
    @ResponseBody
    public Object save(Employee dto) {
        if (dto.getId() != null && dto.getId() != Integer.MIN_VALUE){
            Employee employee = detail(dto.getId());
            dto.setName(employee.getName());
        } else {
            super.save(dto);
            Manager manager = new Manager();
            manager.setEmployee(dto);
            manager.setLoginName(dto.getCode());
            manager.setType(Manager.ManagerEnum.EMPLOYEE);
            manager.setCode(dto.getCode());
            manager.setName(dto.getName());
            manager.setPassword(dto.getPhone());
            managerService.save(manager);
            return restSuccess(dto);
        }
        return super.save(dto);
    }

    @RequestMapping("delete.json")
    @Description("删除数据")
    @Secured("ucenter.employee.delete")
    @ResponseBody
    public Object delete(HttpServletRequest request) {
        return super.delete(request);
    }

    @RequestMapping("select.htm")
    public Object select() {
        return this.toView("employee-select");
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
