package com.ufo.engineering.order.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ufo.basedata.material.entity.Material;
import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.engineering.order.entity.Acceptance.AcceptanceEnum;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 上午9:12
 *
 * @verion 1.0
 */
@MetaData(value = "工程材料订单明细")
@Entity
@Table(name = "tb_engi_order_details")
public class OrderDetails extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = 6107706909960177863L;

    @MetaData(value = "资料")
    private Material material;

    @MetaData(value = "订单")
    private Order order;

    @MetaData(value = "材料要求参数")
    @EntityAutoCode(order = 5, listShow = true)
    private String requireParameter;

    @MetaData(value = "数量要求")
    @EntityAutoCode(order = 10, listShow = true)
    private String requireNum;

    @MetaData(value = "生产厂家")
    @EntityAutoCode(order = 15, listShow = true)
    private String manufacturers;

    @MetaData(value = "生产批次")
    @EntityAutoCode(order = 20, listShow = true)
    private String batch;

    @MetaData(value = "实际材料参数")
    @EntityAutoCode(order = 25, listShow = true)
    private String actParameter;

    @MetaData(value = "收货验收状态")
    @EntityAutoCode(order = 30, listShow = true)
    private AcceptanceEnum acceptanceStatus = AcceptanceEnum.WITHOUT;

    @MetaData(value = "收货验收说明")
    @EntityAutoCode(order = 35, listShow = true)
    private String acceptanceExplain;

    private List<AcceptanceImage> images;

    @Transient
    private String imagesUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id")
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "require_parameter", length = 200)
    public String getRequireParameter() {
        return requireParameter;
    }

    public void setRequireParameter(String requireParameter) {
        this.requireParameter = requireParameter;
    }

    @Column(name = "require_num", length = 80)
    public String getRequireNum() {
        return requireNum;
    }

    public void setRequireNum(String requireNum) {
        this.requireNum = requireNum;
    }

    @Column(length = 100)
    public String getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(String manufacturers) {
        this.manufacturers = manufacturers;
    }

    @Column(length = 20)
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Column(name = "act_parameter", length = 200)
    public String getActParameter() {
        return actParameter;
    }

    public void setActParameter(String actParameter) {
        this.actParameter = actParameter;
    }

    @Column(name = "acceptance_status")
    @Enumerated(value = EnumType.ORDINAL)
    public AcceptanceEnum getAcceptanceStatus() {
        return acceptanceStatus;
    }

    public void setAcceptanceStatus(AcceptanceEnum acceptanceStatus) {
        this.acceptanceStatus = acceptanceStatus;
    }

    @Column(name = "acceptance_explain", length = 200)
    public String getAcceptanceExplain() {
        return acceptanceExplain;
    }

    public void setAcceptanceExplain(String acceptanceExplain) {
        this.acceptanceExplain = acceptanceExplain;
    }

    @OneToMany(mappedBy = "orderDetails", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    public List<AcceptanceImage> getImages() {
        return images;
    }

    public void setImages(List<AcceptanceImage> images) {
        this.images = images;
    }

    @Transient
    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }
}
