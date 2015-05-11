package com.ufo.ucenter.supplier.web;

import javax.servlet.http.HttpServletRequest;

import com.ufo.config.sys.entity.Manager;
import com.ufo.config.sys.service.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.ucenter.supplier.entity.Supplier;
import com.ufo.ucenter.supplier.service.interfaces.ISupplierService;
import com.ufo.core.annotation.Description;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;

@Controller("ucenterSupplierSupplierController")
@RequestMapping("/ucenter/supplier/")
@Description(code = "ucenter.supplier", value = "供货商基本信息设置")
@Secured("ucenter.supplier")
public class SupplierController extends PersistableController<Supplier, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/ucenter/supplier/";

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IManagerService managerService;

    @Override
    public IBaseSpringDataService<Supplier, Integer> getEntityService() {
        return supplierService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("ucenter.supplier.index")
    public String index(HttpServletRequest request) {
        return this.toView("supplier-index");
    }

    @RequestMapping("inputTabs.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "ucenter.supplier.add", "ucenter.supplier.edit" })
    public String inputTabs(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE)
            map.put("id", id);
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("supplier-inputTabs");
    }

    @RequestMapping("edit.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "ucenter.supplier.add", "ucenter.supplier.edit" })
    public String edit(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE){
            map.put("dto", detail(id));
            map.put("id", id);
        }
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("supplier-inputBasic");
    }
    
    @RequestMapping("findByPage.json")
    @Description("列表查询")
    @Secured("ucenter.supplier.index")
    @ResponseBody
    public Object findByPage(HttpServletRequest request) {
        return super.findByPage(request);
    }
    
    @RequestMapping("update.json")
    @Description("编辑数据")
    @Secured("ucenter.supplier.edit")
    @ResponseBody
    public Object update(Supplier dto) {
        return super.save(dto);
    }
    
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "ucenter.supplier.add", "ucenter.supplier.edit" })
    @ResponseBody
    public Object save(Supplier dto) {
        if (dto.getId() == null || dto.getId() == Integer.MIN_VALUE){

            super.save(dto);
            Manager manager = new Manager();
            manager.setSupplier(dto);
            manager.setLoginName(dto.getCode());
            manager.setType(Manager.ManagerEnum.SUPPLIER);
            manager.setCode(dto.getCode());
            manager.setName(dto.getName());
            manager.setPassword("981980");
            managerService.save(manager);
            return restSuccess(dto);
        }
        return super.save(dto);
    }

    @RequestMapping("delete.json")
    @Description("删除数据")
    @Secured("ucenter.supplier.delete")
    @ResponseBody
    public Object delete(HttpServletRequest request) {
        return super.delete(request);
    }

    @RequestMapping("select.htm")
    public Object select() {
        return this.toView("supplier-select");
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
