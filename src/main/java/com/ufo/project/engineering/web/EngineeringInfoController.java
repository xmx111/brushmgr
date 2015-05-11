package com.ufo.project.engineering.web;

import javax.servlet.http.HttpServletRequest;

import com.ufo.project.contract.entity.Contract;
import com.ufo.project.engineering.entity.RelatedPersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.project.engineering.entity.EngineeringInfo;
import com.ufo.project.engineering.service.interfaces.IEngineeringInfoService;
import com.ufo.core.annotation.Description;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;

@Controller("projectEngineeringEngineeringInfoController")
@RequestMapping("/project/engineering/engineeringinfo/")
@Description(code = "project.engineering.engineeringinfo", value = "材料基础信息设置")
@Secured("project.engineering.engineeringinfo")
public class EngineeringInfoController extends PersistableController<EngineeringInfo, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/project/engineering/";

    @Autowired
    private IEngineeringInfoService engineeringInfoService;

    @Override
    public IBaseSpringDataService<EngineeringInfo, Integer> getEntityService() {
        return engineeringInfoService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("project.engineering.engineeringinfo.index")
    public String index(HttpServletRequest request) {
        return this.toView("engineering-info-index");
    }

    @RequestMapping("inputTabs.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.engineering.engineeringinfo.add", "project.engineering.engineeringinfo.edit" })
    public String inputTabs(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE)
            map.put("id", id);
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("engineering-info-inputTabs");
    }

    @RequestMapping("viewBasic.htm")
     @Description({ "新增数据", "编辑数据" })
     @Secured({ "project.engineering.engineeringinfo.add", "project.engineering.engineeringinfo.edit" })
     public String viewBasic(Integer id, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE){
            EngineeringInfo dto = detail(id);
            for (RelatedPersonal relatedPersonal : dto.getRelatedPersonals()){
                if (relatedPersonal.getType().equals(RelatedPersonal.RelatedPersonelEnum.ESTATE)){
                    dto.setEstate(relatedPersonal);
                } else if (relatedPersonal.getType().equals(RelatedPersonal.RelatedPersonelEnum.DESIGNER)){
                    dto.setDesigner(relatedPersonal);
                } else if (relatedPersonal.getType().equals(RelatedPersonal.RelatedPersonelEnum.PROJECTMANAGER)){
                    dto.setProjectmanager(relatedPersonal);
                } else if (relatedPersonal.getType().equals(RelatedPersonal.RelatedPersonelEnum.PROJECTBUTLER)){
                    dto.setProjectbutler(relatedPersonal);
                } else if (relatedPersonal.getType().equals(RelatedPersonal.RelatedPersonelEnum.DEEPENDESIGNER)){
                    dto.setDeependesigner(relatedPersonal);
                }
            }
            map.put("dto", dto);
        }
        return this.toView("engineering-info-viewBasic");
    }

    @RequestMapping("edit.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.engineering.engineeringinfo.add", "project.engineering.engineeringinfo.edit" })
    public String edit(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE){
            EngineeringInfo dto = detail(id);

            for (RelatedPersonal relatedPersonal : dto.getRelatedPersonals()){
                if (relatedPersonal.getType().equals(RelatedPersonal.RelatedPersonelEnum.ESTATE)){
                    dto.setEstate(relatedPersonal);
                } else if (relatedPersonal.getType().equals(RelatedPersonal.RelatedPersonelEnum.DESIGNER)){
                    dto.setDesigner(relatedPersonal);
                } else if (relatedPersonal.getType().equals(RelatedPersonal.RelatedPersonelEnum.PROJECTMANAGER)){
                    dto.setProjectmanager(relatedPersonal);
                } else if (relatedPersonal.getType().equals(RelatedPersonal.RelatedPersonelEnum.PROJECTBUTLER)){
                    dto.setProjectbutler(relatedPersonal);
                } else if (relatedPersonal.getType().equals(RelatedPersonal.RelatedPersonelEnum.DEEPENDESIGNER)){
                    dto.setDeependesigner(relatedPersonal);
                }
            }

            map.put("dto", dto);
            map.put("id", id);
        }
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("engineering-info-inputBasic");
    }
    
    @RequestMapping("findByPage.json")
    @Description("列表查询")
    @Secured("project.engineering.engineeringinfo.index")
    @ResponseBody
    public Object findByPage(HttpServletRequest request) {
        Page<EngineeringInfo> page = (Page<EngineeringInfo>)super.findByPage(request);
        for (EngineeringInfo engineeringInfo : page.getContent()){
            engineeringInfo.setEditorName(engineeringInfo.getUpdateOperator() != null ? engineeringInfo.getUpdateOperator().getName() : "");
        }
        return page;
    }
    
    @RequestMapping("update.json")
    @Description("编辑数据")
    @Secured("project.engineering.engineeringinfo.edit")
    @ResponseBody
    public Object update(EngineeringInfo dto) {
        if (dto.getId() != null && dto.getId() != Integer.MIN_VALUE){
            for (RelatedPersonal relatedPersonal : dto.getRelatedPersonals()){
                relatedPersonal.setEngineeringInfo(dto);
            }
            dto.setType(EngineeringInfo.EditTypeEnum.EDITED);
        }
        return super.save(dto);
    }
    
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.engineering.engineeringinfo.add", "project.engineering.engineeringinfo.edit" })
    @ResponseBody
    public Object save(EngineeringInfo dto) {
        if (dto.getId() != null && dto.getId() != Integer.MIN_VALUE){
            dto.setType(EngineeringInfo.EditTypeEnum.EDITED);
        }
        for (RelatedPersonal relatedPersonal : dto.getRelatedPersonals()){
            relatedPersonal.setEngineeringInfo(dto);
        }
        return super.save(dto);
    }

    @RequestMapping("delete.json")
    @Description("删除数据")
    @Secured("project.engineering.engineeringinfo.delete")
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

    @RequestMapping("findByContract.json")
    @ResponseBody
    public Object findByContract(Integer id) {
        Contract contract = new Contract();
        contract.setId(id);
        return this.engineeringInfoService.findByContract(contract);
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
