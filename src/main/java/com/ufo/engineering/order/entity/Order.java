package com.ufo.engineering.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.core.web.json.DateJsonSerializer;
import com.ufo.core.web.json.LongTimestampJsonSerializer;
import com.ufo.project.contract.entity.Contract;
import com.ufo.ucenter.supplier.entity.Supplier;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 上午8:57
 *
 * @verion 1.0
 */
@MetaData(value = "工程材料订单")
@Entity
@Table(name = "tb_engi_order")
public class Order extends OperatorEntity implements java.io.Serializable {


    private static final long serialVersionUID = 4188618431522401349L;

    public static enum OrderStatusEnum {

        @MetaData(value = "未下单")
        NOTPLACE,

        @MetaData(value = "招标中")
        BIDDING,

        @MetaData(value = "已定")
        CONFIRM,

        @MetaData(value = "已下单")
        HAVEPLACE,

        @MetaData(value = "运送中")
        TRANSPORT,

        @MetaData(value = "已到货")
        ARRIVAL,

        @MetaData(value = "安装中")
        INSTALLING,

        @MetaData(value = "已完工")
        COMPLETE,

        @MetaData(value = "已取消")
        CANCEL

    }

    public static enum OrderTypeEnum {

        @MetaData(value = "材料订单")
        MATERIAL,

        @MetaData(value = "产品订单")
        PRODUCT

    }

    @MetaData(value = "合同")
    private Contract contract;

    @MetaData(value = "订单编号")
    @EntityAutoCode(order = 5, listShow = true)
    private String code;

    @MetaData(value = "订单名称")
    @EntityAutoCode(order = 10, listShow = true)
    private String name;

    @MetaData(value = "订单说明")
    @EntityAutoCode(order = 15, listShow = false)
    private String explains;

    @MetaData(value = "订单时间")
    @EntityAutoCode(order = 20, listShow = true)
    private Timestamp time = new Timestamp(new Date().getTime());

    @MetaData(value = "订单状态")
    @EntityAutoCode(order = 25, listShow = true)
    private OrderStatusEnum status = OrderStatusEnum.NOTPLACE;

    @MetaData(value = "订单供应商")
    @EntityAutoCode(order = 30, listShow = false)
    private Supplier supplier;

    @MetaData(value = "供应商联系人")
    private String supplierContacts;

    @MetaData(value = "供应商电话")
    private String supplierTel;

    @MetaData(value = "订单种类")
    @EntityAutoCode(order = 35, listShow = true)
    private OrderTypeEnum type = OrderTypeEnum.MATERIAL;

    @MetaData(value = "订单明细")
    private List<OrderDetails> orderDetails;

    @MetaData(value = "物流")
    private Express express;

    @MetaData(value = "验收")
    private Acceptance acceptance;

    @MetaData(value = "安装信息")
    private InstallationInfo installationInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Column(length = 12, nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(length = 30, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 100)
    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }


    @JsonSerialize(using = LongTimestampJsonSerializer.class)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Column(name = "supplier_contacts", length = 30)
    public String getSupplierContacts() {
        return supplierContacts;
    }

    public void setSupplierContacts(String supplierContacts) {
        this.supplierContacts = supplierContacts;
    }

    @Column(name = "supplier_tel", length = 20)
    public String getSupplierTel() {
        return supplierTel;
    }

    public void setSupplierTel(String supplierTel) {
        this.supplierTel = supplierTel;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public OrderTypeEnum getType() {
        return type;
    }

    public void setType(OrderTypeEnum type) {
        this.type = type;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @JsonIgnore
    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "order")
    @JsonIgnore
    public Express getExpress() {
        return express;
    }

    public void setExpress(Express express) {
        this.express = express;
    }

    @OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "order")
    @JsonIgnore
    public Acceptance getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(Acceptance acceptance) {
        this.acceptance = acceptance;
    }

    @OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "order")
    @JsonIgnore
    public InstallationInfo getInstallationInfo() {
        return installationInfo;
    }

    public void setInstallationInfo(InstallationInfo installationInfo) {
        this.installationInfo = installationInfo;
    }

    @Transient
    private String operatorName;

    @Transient
    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Transient
    public String getStatusName(){
        if (this.status.equals(OrderStatusEnum.NOTPLACE))
            return "未下单";
        else if (this.status.equals(OrderStatusEnum.BIDDING))
            return "招标中";
        else if (this.status.equals(OrderStatusEnum.CONFIRM))
            return "已定";
        else if (this.status.equals(OrderStatusEnum.HAVEPLACE))
            return "已下单";
        else if (this.status.equals(OrderStatusEnum.TRANSPORT))
            return "运送中";
        else if (this.status.equals(OrderStatusEnum.ARRIVAL))
            return "已到货";
        else if (this.status.equals(OrderStatusEnum.INSTALLING))
            return "安装中";
        else if (this.status.equals(OrderStatusEnum.COMPLETE))
            return "已完工";
        else if (this.status.equals(OrderStatusEnum.CANCEL))
            return "已取消";
        else
            return "";
    }

    @Transient
    public String getTypeName(){
        if (this.type.equals(OrderTypeEnum.MATERIAL))
            return "材料订单";
        else if (this.type.equals(OrderTypeEnum.PRODUCT))
            return "产品订单";
        else
            return "";
    }
}
