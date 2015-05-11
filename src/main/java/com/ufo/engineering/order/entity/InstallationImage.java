package com.ufo.engineering.order.entity;

import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 上午10:11
 *
 * @verion 1.0
 */
@MetaData(value = "工程安装照片")
@Entity
@Table(name = "tb_engi_order_installation_image")
public class InstallationImage extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 4075614369562873430L;

    private InstallationInfo installationInfo;

    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "installation_info_id")
    public InstallationInfo getInstallationInfo() {
        return installationInfo;
    }

    public void setInstallationInfo(InstallationInfo installationInfo) {
        this.installationInfo = installationInfo;
    }

    @Column(length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
