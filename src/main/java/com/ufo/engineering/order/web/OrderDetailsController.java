package com.ufo.engineering.order.web;

import javax.servlet.http.HttpServletRequest;

import com.ufo.core.entity.UndeleteEntity;
import com.ufo.core.pagination.GroupPropertyFilter;
import com.ufo.core.pagination.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.engineering.order.entity.OrderDetails;
import com.ufo.engineering.order.service.interfaces.IOrderDetailsService;
import com.ufo.core.annotation.Description;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;

@Controller("engineeringOrderOrderDetailsController")
@RequestMapping("/engineering/order/details/")
@Description(code = "engineering.order", value = "工程材料订单明细设置")
@Secured("engineering.order")
public class OrderDetailsController extends PersistableController<OrderDetails, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/engineering/order/";

    @Autowired
    private IOrderDetailsService orderDetailsService;

    @Override
    public IBaseSpringDataService<OrderDetails, Integer> getEntityService() {
        return orderDetailsService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("engineering.order.index")
    public String index(HttpServletRequest request) {
        return this.toView("order-details-index");
    }

    @RequestMapping("inputTabs.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "engineering.order.add", "engineering.order.edit" })
    public String inputTabs(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE)
            map.put("id", id);
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("order-details-inputTabs");
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
        return this.toView("order-details-inputBasic");
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
    public Object update(OrderDetails dto) {
        return super.save(dto);
    }
    
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "engineering.order.add", "engineering.order.edit" })
    @ResponseBody
    public Object save(OrderDetails dto) {
        return super.save(dto);
    }

    @RequestMapping("getOrderDetails.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "engineering.order.add", "engineering.order.edit" })
    @ResponseBody
    public Object getOrderDetails(Integer id, HttpServletRequest request) {
        Pageable pageable = PropertyFilter.buildPageableFromHttpRequest(request);
        PropertyFilter filter = new PropertyFilter(PropertyFilter.MatchType.EQ, "deleted", UndeleteEntity.DeleteTypeEnum.UNDELETE);
        GroupPropertyFilter groupFilter = GroupPropertyFilter.buildDefaultAndGroupFilter(filter);
        groupFilter.forceAnd(new PropertyFilter(PropertyFilter.MatchType.EQ, "order.id", id));
        return this.getEntityService().findByPage(groupFilter, pageable);
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
