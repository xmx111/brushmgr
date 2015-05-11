package com.ufo.engineering.order.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.engineering.order.entity.InstallationImage;
import com.ufo.engineering.order.service.interfaces.IInstallationImageService;
import com.ufo.core.annotation.Description;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;

@Controller("engineeringOrderInstallationImageController")
@RequestMapping("/engineering/order/installationimage/")
@Description(code = "engineering.order", value = "工程安装照片设置")
@Secured("engineering.order")
public class InstallationImageController extends PersistableController<InstallationImage, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/engineering/order/installationimage/";

    @Autowired
    private IInstallationImageService installationImageService;

    @Override
    public IBaseSpringDataService<InstallationImage, Integer> getEntityService() {
        return installationImageService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("engineering.order.index")
    public String index(HttpServletRequest request) {
        return this.toView("installation-image-index");
    }

    @RequestMapping("inputTabs.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "engineering.order.add", "engineering.order.edit" })
    public String inputTabs(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE)
            map.put("id", id);
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("installation-image-inputTabs");
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
        return this.toView("installation-image-inputBasic");
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
    public Object update(InstallationImage dto) {
        return super.save(dto);
    }
    
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "engineering.order.add", "engineering.order.edit" })
    @ResponseBody
    public Object save(InstallationImage dto) {
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
