package com.ufo.engineering.order.dao.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufo.core.dao.BaseDao;
import com.ufo.engineering.order.entity.Order;

@Repository
public interface OrderDao extends BaseDao<Order, Integer> {

    @Query("select max(o) from Order o where o.code like ?1")
    public Order getTodayMaxCode(String code);

    @Query("select o from Order o where o.deleted=0 and o.contract.engineeringCode = ?1 order by o.id desc")
    public List<Order> findByEngineeringCode(String engineeringCode);
    
    @Query("select o from Order o where o.deleted=0 and o.contract.engineeringCode = ?1 order by o.id desc")
    public Page<Order> findPageByEngineeringCode(String engineeringCode,Pageable page);
}
