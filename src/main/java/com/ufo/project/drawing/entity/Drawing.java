package com.ufo.project.drawing.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.core.web.json.LongTimestampJsonSerializer;
import com.ufo.project.contract.entity.Contract;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 下午15:22
 *
 * @verion 1.0
 */
@MetaData(value = "工程设计图纸")
@Entity
@Table(name = "tb_proj_drawing")
public class Drawing extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = -1126097903092020147L;

    public static enum DrawingEnum {

        @MetaData(value = "建筑改造")
        PLAN,

        @MetaData(value = "园林规划")
        RENDERING,

        @MetaData(value = "室内空间")
        WORK,

        @MetaData(value = "产品深化")
        DEEPEN

    }

    @MetaData(value = "合同")
    private Contract contract;

    @MetaData(value = "图纸类型")
    @EntityAutoCode(order = 15, listShow = true)
    private DrawingEnum type;

    @MetaData(value = "图纸版本")
    @EntityAutoCode(order = 20, listShow = true)
    private Integer version;

    @MetaData(value = "图纸编号")
    @EntityAutoCode(order = 5, listShow = true)
    private Integer code;

    @MetaData(value = "图纸名称")
    @EntityAutoCode(order = 10, listShow = true)
    private String name;

    @MetaData(value = "图纸描述")
    @EntityAutoCode(order = 30, listShow = true)
    private String describes;

    @MetaData(value = "图纸url")
    @EntityAutoCode(order = 10, listShow = true)
    private String url;

    @MetaData(value = "设计师姓名")
    @EntityAutoCode(order = 25, listShow = true)
    private String designer;

    private Timestamp designerTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public DrawingEnum getType() {
        return type;
    }

    public void setType(DrawingEnum type) {
        this.type = type;
    }

    @Column(nullable = false)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(nullable = false)
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Column(length = 30, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 500)
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @Column(length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(length = 30)
    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    @Column(name="designer_time")
    @JsonSerialize(using = LongTimestampJsonSerializer.class)
    public Timestamp getDesignerTime() {
        return designerTime;
    }

    public void setDesignerTime(Timestamp designerTime) {
        this.designerTime = designerTime;
    }
}
