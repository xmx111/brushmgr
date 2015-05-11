package com.ufo.engineering.order.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.engineering.order.entity.ExpressWay;
import com.ufo.engineering.order.service.interfaces.IExpressWayService;
import com.ufo.core.annotation.Description;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;

@Controller("engineeringOrderExpressWayController")
@RequestMapping("/engineering/order/expressway/")
@Description(code = "engineering.order", value = "工程材料物流跟踪设置")
@Secured("engineering.order")
public class ExpressWayController extends PersistableController<ExpressWay, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/engineering/order/";

    @Autowired
    private IExpressWayService expressWayService;

    @Override
    public IBaseSpringDataService<ExpressWay, Integer> getEntityService() {
        return expressWayService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("engineering.order.index")
    public String index(HttpServletRequest request) {
        return this.toView("express-way-index");
    }

    @RequestMapping("inputTabs.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "engineering.order.add", "engineering.order.edit" })
    public String inputTabs(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE)
            map.put("id", id);
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("express-way-inputTabs");
    }

    @RequestMapping("edit.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "engineering.order.add", "engineering.order.edit" })
    public String edit(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE){
            map.put("dto", detail(id));
            map.put("id", id);
        }
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("express-way-inputBasic");
    }
    
    @RequestMapping("findByPage.json")
    @Description("列表查询")
    @Secured("engineering.order.index")
    @ResponseBody
    public Object findByPage(HttpServletRequest request) {
        return super.findByPage(request);
    }
    
    @RequestMapping("update.json")
    @Description("编辑数据")
    @Secured("engineering.order.edit")
    @ResponseBody
    public Object update(ExpressWay dto) {
        return super.save(dto);
    }
    
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "engineering.order.add", "engineering.order.edit" })
    @ResponseBody
    public Object save(ExpressWay dto) {
        return super.save(dto);
    }

    @RequestMapping("delete.json")
    @Description("删除数据")
    @Secured("engineering.order.delete")
    @ResponseBody
    public Object delete(HttpServletRequest request) {
        return super.delete(request);
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
