package com.ufo.project.drawing.web;

import javax.servlet.http.HttpServletRequest;

import com.ufo.project.contract.entity.Contract;
import com.ufo.project.contract.service.interfaces.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.project.drawing.entity.Drawing;
import com.ufo.project.drawing.service.interfaces.IDrawingService;
import com.ufo.core.annotation.Description;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;

@Controller("projectDrawingDrawingController")
@RequestMapping("/project/drawing/")
@Description(code = "project.drawing", value = "工程设计图纸设置")
@Secured("project.drawing")
public class DrawingController extends PersistableController<Drawing, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/project/drawing/";

    @Autowired
    private IDrawingService drawingService;

    @Autowired
    private IContractService contractService;

    @Override
    public IBaseSpringDataService<Drawing, Integer> getEntityService() {
        return drawingService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("project.drawing.index")
    public String index(HttpServletRequest request, ModelMap map) {
        map.put("contract", contractService.findByMin());
        return this.toView("drawing-index");
    }

    @RequestMapping("inputTabs.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.drawing.add", "project.drawing.edit" })
    public String inputTabs(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE) {
            map.put("id", id);
        }
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("drawing-inputTabs");
    }

    @RequestMapping("edit.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.drawing.add", "project.drawing.edit" })
    public String edit(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE){
            map.put("dto", detail(id));
            map.put("id", id);
        }
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("drawing-inputBasic");
    }
    
    @RequestMapping("findByPage.json")
    @Description("列表查询")
    @Secured("project.drawing.index")
    @ResponseBody
    public Object findByPage(HttpServletRequest request) {
        return super.findByPage(request);
    }
    
    @RequestMapping("update.json")
    @Description("编辑数据")
    @Secured("project.drawing.edit")
    @ResponseBody
    public Object update(Drawing dto) {
        return super.save(dto);
    }
    
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.drawing.add", "project.drawing.edit" })
    @ResponseBody
    public Object save(Drawing dto) {
        Drawing entity = drawingService.findByContractAndVersionAndCode(dto.getContract(), dto.getVersion(), dto.getCode());
        if (entity != null && (entity.getId() != dto.getId() || dto.getId() == null || dto.getId() == Integer.MIN_VALUE)){
            return restFailed("图纸编号重复");
        }

        return super.save(dto);
    }

    @RequestMapping("delete.json")
    @Description("删除数据")
    @Secured("project.drawing.delete")
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

    @RequestMapping("getMaxVersion.json")
    @ResponseBody
    public Integer getMaxVersion(Integer id, Drawing.DrawingEnum type){
        if (id == null || id == Integer.MIN_VALUE)
            return 0;
        Contract contract = new Contract();
        contract.setId(id);
        return this.drawingService.getMaxVersion(contract, type);
    }

    @RequestMapping("getMaxCode.json")
    @ResponseBody
    public Integer getMaxCode(Integer contractId, Drawing.DrawingEnum type, Integer version){
        if (contractId == null || type == null || version == null)
            return 0;
        Contract contract = new Contract();
        contract.setId(contractId);
        return this.drawingService.getMaxCode(contract, type, version);
    }

    @RequestMapping("selectVersion.json")
    @ResponseBody
    public Object selectVersion(Integer contractId, Drawing.DrawingEnum type, Integer page) {
        Pageable pageObj = new PageRequest(page - 1, 10, null);
        return this.drawingService.findVersion(contractId, type, pageObj);
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
