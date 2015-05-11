package com.ufo.basedata.publicity.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.basedata.publicity.entity.Publicity;
import com.ufo.basedata.publicity.entity.Publicity.PublicityEnum;
import com.ufo.basedata.publicity.service.interfaces.IPublicityService;
import com.ufo.config.sys.service.interfaces.IAttachmentFileService;
import com.ufo.core.annotation.Description;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;

@Controller("basedataPublicityPublicityController")
@RequestMapping("/basedata/publicity/")
@Description(code = "basedata.publicity", value = "宣传资料设置")
@Secured("basedata.publicity")
public class PublicityController extends PersistableController<Publicity, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/basedata/publicity/";

    @Autowired
    private IPublicityService publicityService;

    @Autowired
    private IAttachmentFileService attachmentFileService;

    @Override
    public IBaseSpringDataService<Publicity, Integer> getEntityService() {
        return publicityService;
    }

    @RequestMapping("edit.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "basedata_publicity.intro.add", "basedata_publicity.intro.edit" })
    public String edit(Integer id, Boolean clone, ModelMap map,HttpServletRequest request) {
        if("0".equals(request.getParameter("type"))){
            map.put("dto", publicityService.findByPublicityType(PublicityEnum.INTRODUCE));
            map.put("publicityType", PublicityEnum.INTRODUCE);
            map.put("title", "企业介绍");
        }else  if("1".equals(request.getParameter("type"))){
            map.put("dto", publicityService.findByPublicityType(PublicityEnum.CORPORATE));
            map.put("publicityType", PublicityEnum.CORPORATE);
            map.put("title", "企业文化");
        }else  if("2".equals(request.getParameter("type"))){
            map.put("dto", publicityService.findByPublicityType(PublicityEnum.RESOURCES));
            map.put("publicityType", PublicityEnum.RESOURCES);
            map.put("title", "企业资源库");
        }
        return this.toView("publicity-inputBasic");
    }
   
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "basedata_publicity.intro.add", "basedata_publicity.intro.edit" })
    @ResponseBody
    public Object save(HttpServletRequest request, Publicity dto) {
        return super.save(dto);
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
