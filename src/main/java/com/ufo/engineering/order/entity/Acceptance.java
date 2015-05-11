package com.ufo.engineering.order.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.core.web.json.LongTimestampJsonSerializer;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 下午2:32
 *
 * @verion 1.0
 */
@MetaData(value = "工程验收信息")
@Entity
@Table(name = "tb_engi_order_acceptance")
public class Acceptance extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = 4565917083424817263L;

    public static enum AcceptanceEnum {
        @MetaData(value = "未验收")
        WITHOUT,

        @MetaData(value = "正常验收")
        NORMAL,

        @MetaData(value = "异常验收")
        ABNORMAL,

        @MetaData(value = "拒绝验收")
        REFUSE,
    }

    private Order order;

    private AcceptanceEnum status;

    private Timestamp time;

    private String checker;

    private String phone;

    @MetaData(value = "验收情况")
    private String explains;

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
    public AcceptanceEnum getStatus() {
        return status;
    }

    public void setStatus(AcceptanceEnum status) {
        this.status = status;
    }

    @JsonSerialize(using = LongTimestampJsonSerializer.class)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Column(length = 30)
    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    @Column(length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(length = 200)
    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }
}
