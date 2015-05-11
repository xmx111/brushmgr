package com.ufo.engineering.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.core.web.json.DateJsonSerializer;
import com.ufo.core.web.json.LongTimestampJsonSerializer;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 下午12:29
 *
 * @verion 1.0
 */
@MetaData(value = "工程材料物流")
@Entity
@Table(name = "tb_engi_order_express")
public class Express extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = -710150753998408136L;

    public static enum ExpressStatusEnum {

        @MetaData(value = "未发货")
        NOTSEND,

        @MetaData(value = "已发货")
        SENDED,

        @MetaData(value = "已收货")
        RECEIVED,

        @MetaData(value = "已退货")
        RETURN

    }

    @MetaData(value = "订单")
    private Order order;

    @MetaData(value = "物流状态")
    @EntityAutoCode(order = 5, listShow = true)
    private ExpressStatusEnum status = ExpressStatusEnum.NOTSEND;

    @MetaData(value = "物流时间")
    @EntityAutoCode(order = 10, listShow = true)
    private Timestamp time;

    @MetaData(value = "物流名称")
    @EntityAutoCode(order = 15, listShow = true)
    private String name;

    @MetaData(value = "物流单号")
    @EntityAutoCode(order = 20, listShow = true)
    private String no;

    @MetaData(value = "物流联系人")
    @EntityAutoCode(order = 25, listShow = true)
    private String sender;

    @MetaData(value = "物流联系电话")
    @EntityAutoCode(order = 10, listShow = true)
    private String phone;

    private List<ExpressWay> expressWays;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public ExpressStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ExpressStatusEnum status) {
        this.status = status;
    }

    @JsonSerialize(using = LongTimestampJsonSerializer.class)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Column(length = 80)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 50)
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Column(length = 20)
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Column(length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @OneToMany(mappedBy = "express")
    @Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @LazyCollection(LazyCollectionOption.TRUE)
    @JsonIgnore
    public List<ExpressWay> getExpressWays() {
        return expressWays;
    }

    public void setExpressWays(List<ExpressWay> expressWays) {
        this.expressWays = expressWays;
    }
}
