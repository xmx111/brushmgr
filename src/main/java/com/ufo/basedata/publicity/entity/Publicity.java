package com.ufo.basedata.publicity.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 下午12:46
 *
 * @verion 1.0
 */
@MetaData(value = "宣传资料")
@Entity
@Table(name = "tb_bas_publicity")
public class Publicity extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = -700040171101078303L;

    public static enum PublicityEnum {
        @MetaData(value = "企业介绍")
        INTRODUCE,

        @MetaData(value = "企业文化")
        CORPORATE,
        
        @MetaData(value = "企业资源库")
        RESOURCES
    }

    @MetaData(value = "宣传类型")
    @EntityAutoCode(order = 5, listShow = true)
    private PublicityEnum publicityType;

    @MetaData(value = "公司简介/总经理致辞/签约设计师")
    @EntityAutoCode(order = 10, listShow = true)
    private String content;

    @MetaData(value = "创始人简介/文化篇/签约装修公司")
    @EntityAutoCode(order = 10, listShow = true)
    private String content1;

    @MetaData(value = "运营模式/''/签约品牌商")
    @EntityAutoCode(order = 10, listShow = true)
    private String content2;

    @Column(name = "publicity_type", length = 20, unique = true)
    public PublicityEnum getPublicityType() {
        return publicityType;
    }

    @Enumerated(EnumType.ORDINAL)
    public void setPublicityType(PublicityEnum publicityType) {
        this.publicityType = publicityType;
    }

    @Lob
    @Basic
    @Column()
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Lob
    @Basic
    @Column()
    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    @Lob
    @Basic
    @Column()
    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }
}
