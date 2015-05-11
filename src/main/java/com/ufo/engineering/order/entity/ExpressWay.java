package com.ufo.engineering.order.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.core.web.json.DateJsonSerializer;
import com.ufo.core.web.json.LongTimestampJsonSerializer;
import com.ufo.core.web.json.TimestampJsonSerializer;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 下午1:38
 *
 * @verion 1.0
 */
@MetaData(value = "工程材料物流跟踪")
@Entity
@Table(name = "tb_engi_order_express_way")
public class ExpressWay extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = -5544001830571699595L;

    private Express express;

    private String way;

    private Timestamp time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "express_id")
    public Express getExpress() {
        return express;
    }

    public void setExpress(Express express) {
        this.express = express;
    }

    @Column(length = 200)
    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    @Column
    @JsonSerialize(using = LongTimestampJsonSerializer.class)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
