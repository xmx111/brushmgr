package com.ufo.engineering.special.entity;

import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 上午10:21
 *
 * @verion 1.0
 */
@MetaData(value = "特殊信息照片")
@Entity
@Table(name = "tb_engi_special_image")
public class SpecialImage extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -2699469258588199106L;

    private SpecialInfo specialInfo;

    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "special_info_id")
    public SpecialInfo getSpecialInfo() {
        return specialInfo;
    }

    public void setSpecialInfo(SpecialInfo specialInfo) {
        this.specialInfo = specialInfo;
    }

    @Column(length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
