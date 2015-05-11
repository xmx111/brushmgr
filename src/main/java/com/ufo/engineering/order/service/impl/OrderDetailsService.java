package com.ufo.engineering.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufo.core.dao.BaseDao;
import com.ufo.core.service.BaseSpringDataService;
import com.ufo.engineering.order.dao.interfaces.OrderDetailsDao;
import com.ufo.engineering.order.entity.OrderDetails;
import com.ufo.engineering.order.service.interfaces.IOrderDetailsService;

@Service
@Transactional
public class OrderDetailsService extends BaseSpringDataService<OrderDetails, Integer> implements IOrderDetailsService {
    
    @Autowired
    private OrderDetailsDao orderDetailsDao;

    @Override
    public BaseDao<OrderDetails, Integer> getEntityDao() {
        return orderDetailsDao;
    }
}
