package com.ufo.engineering.special.web;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.core.entity.UndeleteEntity;
import com.ufo.core.utils.FileUtils;
import com.ufo.core.utils.ImageUtils;
import com.ufo.engineering.special.entity.SpecialImage;
import com.ufo.engineering.special.service.interfaces.ISpecialImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufo.engineering.special.entity.SpecialInfo;
import com.ufo.engineering.special.service.interfaces.ISpecialInfoService;
import com.ufo.core.annotation.Description;
import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.core.web.PersistableController;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Controller("engineeringSpecialSpecialInfoController")
@RequestMapping("/engineering/special/")
@Description(code = "engineering.special", value = "工程特殊信息设置")
@Secured("engineering.special")
public class SpecialInfoController extends PersistableController<SpecialInfo, Integer> {
    
    /***
     * jsp file path
     */
    private static final String VIEWPATH = "/engineering/special/";

    @Autowired
    private ISpecialInfoService specialInfoService;

    @Autowired
    private ISpecialImageService specialImageService;

    @Override
    public IBaseSpringDataService<SpecialInfo, Integer> getEntityService() {
        return specialInfoService;
    }
    
    @RequestMapping("index.htm")
    @Description("列表查询")
    @Secured("engineering.special.index")
    public String index(HttpServletRequest request) {
        return this.toView("special-info-index");
    }

    @RequestMapping("details.htm")
    @Description({ "查看详情" })
    @Secured("engineering.special.details")
    public String details(Integer id, ModelMap map) {
        map.put("dto", detail(id));
        return this.toView("special-info-viewBasic");
    }
    
    @RequestMapping("findByPage.json")
    @Description("列表查询")
    @Secured("engineering.special.index")
    @ResponseBody
    public Object findByPage(HttpServletRequest request) {
        Page<SpecialInfo> page = (Page<SpecialInfo>)super.findByPage(request);
        for (SpecialInfo specialInfo : page.getContent()){
            specialInfo.setCollectorLoginName(specialInfo.getCreateOperator().getLoginName());
            specialInfo.setCollectorName(specialInfo.getCreateOperator().getName());
        }
        return page;
    }

    @RequestMapping("delete.json")
    @Description("删除数据")
    @Secured("engineering.special.delete")
    @ResponseBody
    public Object delete(HttpServletRequest request) {

        //删除失败的id和对应消息以Map结构返回，可用于前端批量显示错误提示和计算表格组件更新删除行项
        Map<Integer, String> errorMessageMap = Maps.newLinkedHashMap();
        Collection<SpecialInfo> entities = this.getEntitiesByParameterIds(request);

        for (SpecialInfo entity : entities) {
            try {
                entity.setDeleted(UndeleteEntity.DeleteTypeEnum.DELETE);
                entity.setDeleteOperator(operation());
                entity.setDeleteTime(getCurrentTime());
                for (int i = entity.getImages().size()-1; i >= 0; i--) {
                    SpecialImage specialImage = entity.getImages().get(i);
                    File file = new File(getRoot(request) + specialImage.getUrl());
                    if (file.exists()) file.delete();
                    entity.getImages().remove(specialImage);
                    specialImageService.delete(specialImage);
                }
                File file = new File(getRoot(request) + entity.getVideo());
                if (file.exists()) file.delete();
                getEntityService().save(entity);
            } catch (Exception e) {
                errorMessageMap.put(entity.getId(), e.getMessage());
            }
        }
        int rejectSize = errorMessageMap.size();
        if (rejectSize == 0) {
            return this.restSuccess(true, "成功删除所选选取记录:" + entities.size() + "条");
        } else {
            if (rejectSize == entities.size()) {
                return this.restFailed("所有选取记录删除操作失败:" + errorMessageMap);
            } else {
                return this.restFailed("删除操作已处理. 成功:" + (entities.size() - rejectSize) + "条"
                        + ",失败:" + rejectSize + "条" + errorMessageMap);
            }
        }
    }

    @RequestMapping("buildValidateRules.json")
    @ResponseBody
    public Object buildValidateRules() {
        return super.buildValidateRules();
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
