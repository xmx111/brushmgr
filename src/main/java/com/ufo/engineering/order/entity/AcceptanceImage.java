package com.ufo.engineering.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 上午10:10
 *
 * @verion 1.0
 */
@MetaData(value = "工程验收照片")
@Entity
@Table(name = "tb_engi_order_acceptance_image")
public class AcceptanceImage extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -3634415474410127119L;

    private OrderDetails orderDetails;

    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_details_id")
    @JsonIgnore
    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Column(length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
