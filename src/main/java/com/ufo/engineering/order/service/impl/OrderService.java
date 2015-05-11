package com.ufo.engineering.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ufo.core.entity.UndeleteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.core.utils.DateUtils;
import com.ufo.core.utils.StringUtils;
import com.ufo.engineering.order.dao.interfaces.OrderDao;
import com.ufo.engineering.order.entity.Acceptance;
import com.ufo.engineering.order.entity.AcceptanceImage;
import com.ufo.engineering.order.entity.Order;
import com.ufo.engineering.order.entity.OrderDetails;
import com.ufo.engineering.order.service.interfaces.IAcceptanceImageService;
import com.ufo.engineering.order.service.interfaces.IOrderService;

@Service
@Transactional
public class OrderService extends BaseSpringDataService<Order, Integer> implements IOrderService {
    
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private IAcceptanceImageService acceptanceImageService;

    @Override
    public BaseDao<Order, Integer> getEntityDao() {
        return orderDao;
    }

    public String genCanUseCode() {
        String code;
        String start = String.valueOf(DateUtils.getThisYear()) + (DateUtils.getThisMonth() > 9 ? String.valueOf(DateUtils.getThisMonth()) : "0" + DateUtils.getThisMonth()) + (DateUtils.getToDayOfMonth() > 9 ? String.valueOf(DateUtils.getToDayOfMonth()) : "0" + DateUtils.getToDayOfMonth());
        Order order = orderDao.getTodayMaxCode(start + "%");
        if (order == null){
            code = start + "0001";
        } else {
            code = start + StringUtils.addZero(Integer.valueOf(order.getCode().substring(8)) + 1, 4);
        }
        return code;
    }

    @Override
    public Order saveAsImages(Order dto) {
        Map<Integer, OrderDetails> map = new HashMap<Integer, OrderDetails>();
        List<OrderDetails> notEdit = new ArrayList<OrderDetails>();
        for (OrderDetails details : dto.getOrderDetails()){
            if (details.getId() != null && details.getId() != Integer.MIN_VALUE)
                map.put(details.getId(), details);
        }
        if (dto.getId() != null && dto.getId() != Integer.MIN_VALUE) {
            Order entity = findOne(dto.getId());
            for (OrderDetails details : entity.getOrderDetails()){
                // 由于没修改的明细前台不会传过来，所以加上没修改的明细
                if (map.get(details.getId()) == null && details.getMaterial() != null && details.getMaterial().getId() != null && details.getMaterial().getId() != Integer.MIN_VALUE)
                    notEdit.add(details);
                else {
                    // 清空图片
                    acceptanceImageService.delete(details.getImages());
                    details.setImages(null);
                    if (map.get(details.getId()).getDeleted() == UndeleteEntity.DeleteTypeEnum.DELETE){
                        dto.getOrderDetails().remove(map.get(details.getId()));
                    }
                }
            }
        }
        for (OrderDetails details : dto.getOrderDetails()){
            details.setOrder(dto);
            if (details.getAcceptanceStatus() == null)
                details.setAcceptanceStatus(Acceptance.AcceptanceEnum.WITHOUT);
            details.setImages(new ArrayList<AcceptanceImage>());
            if (StringUtils.isNotBlank(details.getImagesUrl())) {
                for (String url : details.getImagesUrl().split(",")){
                    if (StringUtils.isBlank(url))
                        continue;
                    AcceptanceImage image = new AcceptanceImage();
                    image.setOrderDetails(details);
                    image.setUrl(url);
                    details.getImages().add(image);
                }
            }
        }
        dto.getOrderDetails().addAll(notEdit);
        return save(dto);
    }


    @Override
    public List<Order> findByEngineeringCode(String engineeringCode) {
        return orderDao.findByEngineeringCode(engineeringCode);
    }
    
    @Override
    public Page<Order> findPageByEngineeringCode(String engineeringCode,Pageable page) {
        return orderDao.findPageByEngineeringCode(engineeringCode,page);
    }
}
