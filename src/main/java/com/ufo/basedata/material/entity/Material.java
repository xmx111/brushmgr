package com.ufo.basedata.material.entity;

import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 上午9:09
 *
 * @verion 1.0
 */
@MetaData(value = "材料基础信息")
@Entity
@Table(name = "tb_bas_material")
public class Material extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = -5546096527066196769L;

    @MetaData(value = "材料编号")
    @EntityAutoCode(order = 5, listShow = true)
    private String code;

    @MetaData(value = "材料名称")
    @EntityAutoCode(order = 10, listShow = true)
    private String name;

    @MetaData(value = "材料描述")
    @EntityAutoCode(order = 15, listShow = true)
    private String describes;

    @Column(length = 10, nullable = false, unique = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(length = 80, nullable = false)
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
}
