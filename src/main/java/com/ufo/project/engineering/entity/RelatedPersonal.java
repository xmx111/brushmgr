package com.ufo.project.engineering.entity;

import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 下午1:23
 *
 * @verion 1.0
 */
@MetaData(value = "工程相关人员")
@Entity
@Table(name = "tb_proj_reated_personel")
public class RelatedPersonal extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = -7125871116811496910L;

    public static enum RelatedPersonelEnum {

        @MetaData(value = "物业")
        ESTATE,

        @MetaData(value = "设计师")
        DESIGNER,

        @MetaData(value = "项目经理")
        PROJECTMANAGER,

        @MetaData(value = "项目管家")
        PROJECTBUTLER,

        @MetaData(value = "深化设计师")
        DEEPENDESIGNER

    }

    private EngineeringInfo engineeringInfo;

    @MetaData(value = "姓名")
    @EntityAutoCode(order = 5, listShow = true)
    private String name;

    @MetaData(value = "类型")
    @EntityAutoCode(order = 10, listShow = true)
    private RelatedPersonelEnum type;

    @MetaData(value = "电话")
    @EntityAutoCode(order = 15, listShow = true)
    private String phone;

    @MetaData(value = "照片")
    @EntityAutoCode(order = 20, listShow = true)
    private String photo;

    @MetaData(value = "介绍")
    @EntityAutoCode(order = 25, listShow = true)
    private String introduce;

    private String video;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "engineering_info_id")
    @ForeignKey(name = "fk_engineering_info_related_personal")
    public EngineeringInfo getEngineeringInfo() {
        return engineeringInfo;
    }

    public void setEngineeringInfo(EngineeringInfo engineeringInfo) {
        this.engineeringInfo = engineeringInfo;
    }

    @Column(length = 80, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public RelatedPersonelEnum getType() {
        return type;
    }

    public void setType(RelatedPersonelEnum type) {
        this.type = type;
    }

    @Column(length = 20, nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(length = 60)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Lob
    @Basic
    @Column
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Column(length = 100)
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
