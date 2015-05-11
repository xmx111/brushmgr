package com.ufo.basedata.region.entity;

import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.ucenter.employee.entity.Employee;

import javax.persistence.*;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 下午12:46
 *
 * @verion 1.0
 */
@MetaData(value = "业务区域信息")
@Entity
@Table(name = "tb_bas_region")
public class Region extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = -7394722882068795972L;

    @MetaData(value = "区域编号")
    @EntityAutoCode(order = 5, listShow = true)
    private String code;

    @MetaData(value = "区域名称")
    @EntityAutoCode(order = 10, listShow = true)
    private String name;

    @MetaData(value = "区域负责人")
    @EntityAutoCode(order = 15, listShow = true)
    private Employee employee;

    @MetaData(value = "所在省")
    @EntityAutoCode(order = 20, listShow = true)
    private String province = "湖南省";

    @MetaData(value = "所在市")
    @EntityAutoCode(order = 25, listShow = true)
    private String city = "长沙市";

    @Column(length = 10, nullable = false, unique = true)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Column(length = 20, nullable = false)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(length = 20, nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
