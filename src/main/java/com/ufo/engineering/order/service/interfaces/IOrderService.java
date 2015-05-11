package com.ufo.engineering.order.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufo.core.service.IBaseSpringDataService;
import com.ufo.engineering.order.entity.Order;

public interface IOrderService extends IBaseSpringDataService<Order, Integer> {

    /**
     * 取当前可用新订单编号
     * @return
     */
    public String genCanUseCode();

    public Order saveAsImages(Order dto);

    public List<Order> findByEngineeringCode(String engineeringCode);

    public Page<Order> findPageByEngineeringCode(String engineeringCode,Pageable page);
}