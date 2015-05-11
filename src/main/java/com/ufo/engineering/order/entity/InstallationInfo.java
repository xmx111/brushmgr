package com.ufo.engineering.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ufo.config.sys.entity.AttachmentFile;
import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.core.web.json.DateJsonSerializer;
import com.ufo.core.web.json.LongTimestampJsonSerializer;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 下午1:50
 *
 * @verion 1.0
 */
@MetaData(value = "工程材料安装")
@Entity
@Table(name = "tb_engi_order_installation_info")
public class InstallationInfo extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = -3062335937223424502L;

    public static enum InstallationStatusEnum {

        @MetaData(value = "未安装")
        NOTINSTALL,

        @MetaData(value = "已安装")
        INSTALLED,

        @MetaData(value = "安装完成")
        COMPLETE,

        @MetaData(value = "不需安装")
        NONEED

    }

    @MetaData(value = "合同")
    private Order order;

    @MetaData(value = "安装状态")
    @EntityAutoCode(order = 5, listShow = true)
    private InstallationStatusEnum status;

    @MetaData(value = "安装时间")
    @EntityAutoCode(order = 10, listShow = true)
    private Timestamp time;

    @MetaData(value = "安装人")
    @EntityAutoCode(order = 15, listShow = true)
    private String installer;

    @MetaData(value = "安装人电话")
    @EntityAutoCode(order = 20, listShow = true)
    private String phone;

    @MetaData(value = "安装情况说明")
    @EntityAutoCode(order = 25, listShow = true)
    private String explains;

    private List<InstallationImage> images;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public InstallationStatusEnum getStatus() {
        return status;
    }

    public void setStatus(InstallationStatusEnum status) {
        this.status = status;
    }

    @JsonSerialize(using = LongTimestampJsonSerializer.class)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Column(length = 20)
    public String getInstaller() {
        return installer;
    }

    public void setInstaller(String installer) {
        this.installer = installer;
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

    @OneToMany(mappedBy = "installationInfo", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonIgnore
    public List<InstallationImage> getImages() {
        return images;
    }

    public void setImages(List<InstallationImage> images) {
        this.images = images;
    }
}
