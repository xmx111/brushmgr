package com.ufo.engineering.order.web;

import com.ufo.core.annotation.Description;
import com.ufo.core.entity.UndeleteEntity;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.utils.StringUtils;
import com.ufo.core.web.PersistableController;
import com.ufo.engineering.order.entity.Acceptance;
import com.ufo.engineering.order.entity.AcceptanceImage;
import com.ufo.engineering.order.entity.Order;
import com.ufo.engineering.order.entity.OrderDetails;
import com.ufo.engineering.order.service.interfaces.IAcceptanceImageService;
import com.ufo.engineering.order.service.interfaces.IOrderService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller("engineeringOrderOrderController")
@RequestMapping("/engineering/order/")
@Description(code = "engineering.order", value = "工程材料订单设置")
@Secured("engineering.order")
public class OrderController extends PersistableController<Order, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/engineering/order/";

    @Autowired
    private IOrderService orderService;

    @Override
    public IBaseSpringDataService<Order, Integer> getEntityService() {
        return orderService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("engineering.order.index")
    public String index(HttpServletRequest request) {
        return this.toView("order-index");
    }

    @RequestMapping("inputTabs.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "engineering.order.add", "engineering.order.edit" })
    public String inputTabs(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE)
            map.put("id", id);
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("order-inputTabs");
    }

    @RequestMapping("edit.htm")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "engineering.order.add", "engineering.order.edit" })
    public String edit(Integer id, Boolean clone, ModelMap map) {
        if (id != null && id != Integer.MIN_VALUE){
            Order dto = detail(id);
            for (OrderDetails orderDetails : dto.getOrderDetails()){
                String imagesUrl = "";
                for (AcceptanceImage image : orderDetails.getImages()){
                    imagesUrl += orderDetails.getImagesUrl() + ",";
                }
                orderDetails.setImagesUrl(imagesUrl);
            }
            map.put("operatorName", dto.getCreateOperator().getName());
            map.put("dto", dto);
            map.put("id", id);
        } else {
            map.put("orderCode", orderService.genCanUseCode());
            map.put("orderTime", new Date());
            map.put("operatorName", operation().getName());
        }
        if (clone == Boolean.TRUE)
            map.put("clone", clone);
        return this.toView("order-inputBasic");
    }

    @RequestMapping("view.htm")
    @Secured({ "engineering.order.add", "engineering.order.edit" })
    public String inputTabs(Integer id, ModelMap map) {
        map.put("dto", detail(id));
        return this.toView("order-inputView");
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
    public Object update(Order dto) {
        return super.save(dto);
    }
    
    @RequestMapping("save.json")
    @Description({ "新增数据", "编辑数据" })
    @Secured({ "engineering.order.add", "engineering.order.edit" })
    @ResponseBody
    public Object save(Order dto) {
        try {
            // 过滤无效的明细
            List<OrderDetails> list = new ArrayList<OrderDetails>();
            for (OrderDetails orderDetails : dto.getOrderDetails()) {
                if (orderDetails.getMaterial() != null && orderDetails.getMaterial().getId() != null)
                    list.add(orderDetails);
            }
            dto.setOrderDetails(list);
            return restSuccess(orderService.saveAsImages(dto));
        } catch (Exception e){
            return restFailed(e.getMessage());
        }
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
