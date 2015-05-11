package com.ufo.project.quote.web;

import javax.servlet.http.HttpServletRequest;

import com.ufo.project.contract.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.project.quote.entity.Quote;
import com.ufo.project.quote.service.interfaces.IQuoteService;
import com.ufo.core.annotation.Description;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;

@Controller("projectQuoteQuoteController")
@RequestMapping("/project/quote/")
@Description(code = "project.quote", value = "工程报价信息设置")
@Secured("project.quote")
public class QuoteController extends PersistableController<Quote, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/project/quote/";

    @Autowired
    private IQuoteService quoteService;

    @Override
    public IBaseSpringDataService<Quote, Integer> getEntityService() {
        return quoteService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("project.quote.index")
    public String index(HttpServletRequest request) {
        return this.toView("quote-index");
    }

    @RequestMapping("inputTabs.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.quote.add", "project.quote.edit" })
    public String inputTabs(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE)
            map.put("id", id);
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("quote-inputTabs");
    }

    @RequestMapping("edit.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.quote.add", "project.quote.edit" })
    public String edit(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE){
            map.put("dto", detail(id));
            map.put("id", id);
        }
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("quote-inputBasic");
    }
    
    @RequestMapping("findByPage.json")
    @Description("列表查询")
    @Secured("project.quote.index")
    @ResponseBody
    public Object findByPage(HttpServletRequest request) {
        return super.findByPage(request);
    }
    
    @RequestMapping("update.json")
    @Description("编辑数据")
    @Secured("project.quote.edit")
    @ResponseBody
    public Object update(Quote dto) {
        return super.save(dto);
    }
    
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "project.quote.add", "project.quote.edit" })
    @ResponseBody
    public Object save(Quote dto) {
        if (dto.getId() != null && dto.getId() != Integer.MIN_VALUE){

            Quote quote = quoteService.findByContractAndCode(dto.getContract(), dto.getCode());
            if (quote != null && quote.getId() != dto.getId())
                return restFailed("报价编号重复");
        } else {
            Quote quote = quoteService.findByContractAndCode(dto.getContract(), dto.getCode());
            if (quote != null)
                return restFailed("报价编号重复");
        }
        return super.save(dto);
    }

    @RequestMapping("delete.json")
    @Description("删除数据")
    @Secured("project.quote.delete")
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

    @RequestMapping("getMaxCode.json")
    @ResponseBody
    public Integer getMaxCode(Contract contract){
        if (contract.getId() == null)
            return 0;
        return this.quoteService.getMaxCode(contract);
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
