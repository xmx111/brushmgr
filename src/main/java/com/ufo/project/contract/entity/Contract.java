package com.ufo.project.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.project.engineering.entity.EngineeringInfo;
import com.ufo.ucenter.custom.entity.Custom;
import com.ufo.ucenter.employee.entity.Employee;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 上午11:02
 *
 * @verion 1.0
 */
@MetaData(value = "合同信息")
@Entity
@Table(name = "tb_proj_contract")
public class Contract extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = 468365657297748178L;

    public static enum ContractEnum {
        @MetaData(value = "临时合同")
        TEMP,

        @MetaData(value = "正式合同")
        RELE
    }

    @MetaData(value = "合同编号")
    @EntityAutoCode(order = 5, listShow = true)
    private String code;

    @MetaData(value = "合同名称")
    @EntityAutoCode(order = 10, listShow = true)
    private String name;

    @MetaData(value = "合同客户")
    @EntityAutoCode(order = 15, listShow = true)
    private Custom custom;

    private String customPhone;

    @MetaData(value = "合同类型")
    @EntityAutoCode(order = 20, listShow = true)
    private ContractEnum type;

    @MetaData(value = "工程编号")
    @EntityAutoCode(order = 25, listShow = true)
    private String engineeringCode;

    @MetaData(value = "工程名称")
    @EntityAutoCode(order = 30, listShow = true)
    private String engineeringName;

    @MetaData(value = "工程小区")
    @EntityAutoCode(order = 35, listShow = true)
    private String engineeringCommunity;

    @MetaData(value = "工程小区地址")
    @EntityAutoCode(order = 40, listShow = true)
    private String engineeringCommunityAddress;

    private Employee employee;

    private List<EngineeringInfo> engineeringInfos;

    @Column(length = 20, nullable = false, unique = true)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "custom_id")
    public Custom getCustom() {
        return custom;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }

    @Column(length = 15)
    public String getCustomPhone() {
        return customPhone;
    }

    public void setCustomPhone(String customPhone) {
        this.customPhone = customPhone;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public ContractEnum getType() {
        return type;
    }

    public void setType(ContractEnum type) {
        this.type = type;
    }

    @Column(name = "engineering_code", length = 10, nullable = false)
    public String getEngineeringCode() {
        return engineeringCode;
    }

    public void setEngineeringCode(String engineeringCode) {
        this.engineeringCode = engineeringCode;
    }

    @Column(name = "engineering_name", length = 40, nullable = false)
    public String getEngineeringName() {
        return engineeringName;
    }

    public void setEngineeringName(String engineeringName) {
        this.engineeringName = engineeringName;
    }

    @Column(name = "engineering_community", length = 40)
    public String getEngineeringCommunity() {
        return engineeringCommunity;
    }

    public void setEngineeringCommunity(String engineeringCommunity) {
        this.engineeringCommunity = engineeringCommunity;
    }

    @Column(name = "engineering_community_address", length = 120)
    public String getEngineeringCommunityAddress() {
        return engineeringCommunityAddress;
    }

    public void setEngineeringCommunityAddress(String engineeringCommunityAddress) {
        this.engineeringCommunityAddress = engineeringCommunityAddress;
    }

    @OneToMany(mappedBy = "contract")
    @Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @LazyCollection(LazyCollectionOption.TRUE)
    @JsonIgnore
    public List<EngineeringInfo> getEngineeringInfos() {
        return engineeringInfos;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setEngineeringInfos(List<EngineeringInfo> engineeringInfos) {
        this.engineeringInfos = engineeringInfos;
    }

    private String createOperatorName;

    private String updateOperatorName;

    @Transient
    public String getCreateOperatorName() {
        return createOperatorName;
    }

    public void setCreateOperatorName(String createOperatorName) {
        this.createOperatorName = createOperatorName;
    }

    @Transient
    public String getUpdateOperatorName() {
        return updateOperatorName;
    }

    public void setUpdateOperatorName(String updateOperatorName) {
        this.updateOperatorName = updateOperatorName;
    }
}
