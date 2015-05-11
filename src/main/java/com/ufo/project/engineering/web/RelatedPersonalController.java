package com.ufo.project.engineering.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.project.engineering.entity.RelatedPersonal;
import com.ufo.project.engineering.service.interfaces.IRelatedPersonalService;
import com.ufo.core.annotation.Description;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;

@Controller("projectEngineeringRelatedPersonalController")
@RequestMapping("/project/engineering/relatedpersonal/")
@Description(code = "project.engineering.relatedpersonal", value = "工程相关人员设置")
@Secured("project.engineering.relatedpersonal")
public class RelatedPersonalController extends PersistableController<RelatedPersonal, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/project/engineering/";

    @Autowired
    private IRelatedPersonalService relatedPersonalService;

    @Override
    public IBaseSpringDataService<RelatedPersonal, Integer> getEntityService() {
        return relatedPersonalService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("project.engineering.relatedpersonal.index")
    public String index(HttpServletRequest request) {
        return this.toView("related-personal-index");
    }

    @RequestMapping("inputTabs.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.engineering.relatedpersonal.add", "project.engineering.relatedpersonal.edit" })
    public String inputTabs(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE)
            map.put("id", id);
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("related-personal-inputTabs");
    }

    @RequestMapping("edit.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.engineering.relatedpersonal.add", "project.engineering.relatedpersonal.edit" })
    public String edit(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE){
            map.put("dto", detail(id));
            map.put("id", id);
        }
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("related-personal-inputBasic");
    }
    
    @RequestMapping("findByPage.json")
    @Description("列表查询")
    @Secured("project.engineering.relatedpersonal.index")
    @ResponseBody
    public Object findByPage(HttpServletRequest request) {
        return super.findByPage(request);
    }
    
    @RequestMapping("update.json")
    @Description("编辑数据")
    @Secured("project.engineering.relatedpersonal.edit")
    @ResponseBody
    public Object update(RelatedPersonal dto) {
        return super.save(dto);
    }
    
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.engineering.relatedpersonal.add", "project.engineering.relatedpersonal.edit" })
    @ResponseBody
    public Object save(RelatedPersonal dto) {
        return super.save(dto);
    }

    @RequestMapping("delete.json")
    @Description("删除数据")
    @Secured("project.engineering.relatedpersonal.delete")
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
