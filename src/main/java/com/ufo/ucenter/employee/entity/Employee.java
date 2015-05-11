package com.ufo.ucenter.employee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.core.web.json.DateJsonSerializer;
import com.ufo.project.contract.entity.Contract;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 下午12:46
 *
 * @verion 1.0
 */
@MetaData(value = "员工基本信息")
@Entity
@Table(name = "tk_tb_bas_employee")
public class Employee extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = -2281597712427338027L;

    public static enum SexEnum {

        @MetaData(value = "男")
        MAN,

        @MetaData(value = "女")
        WOMAN
    }

    public static enum EmployeeEnum {

        @MetaData(value = "正式员工")
        REGULAR,

        @MetaData(value = "外聘员工")
        EXTERNAL,

        @MetaData(value = "临时员工")
        TEMPORARY

    }

    public static enum StationEnum {

        @MetaData(value = "业务人员")
        SALESMAN,

        @MetaData(value = "设计师")
        DESIGNER,

        @MetaData(value = "管理员")
        MANAGER,

        @MetaData(value = "项目经理")
        PROJECTMNG

    }

    public static enum EmployeeStatusEnum {

        @MetaData(value = "在职")
        ON,

        @MetaData(value = "离职")
        LEAVE

    }

    @MetaData(value = "员工编号")
    @EntityAutoCode(order = 5, listShow = true)
    private String code;

    @MetaData(value = "员工姓名")
    @EntityAutoCode(order = 10, listShow = true)
    private String name;

    @MetaData(value = "员工性别")
    @EntityAutoCode(order = 15, listShow = true)
    private SexEnum sex;

    @MetaData(value = "员工联系电话")
    @EntityAutoCode(order = 20, listShow = true)
    private String phone;

    @MetaData(value = "员工邮箱")
    @EntityAutoCode(order = 25, listShow = true)
    private String email;

    @MetaData(value = "员工类型")
    @EntityAutoCode(order = 27, listShow = true)
    private EmployeeEnum type;

    @MetaData(value = "员工岗位")
    @EntityAutoCode(order = 30, listShow = true)
    private StationEnum station;

    @MetaData(value = "员工状态")
    @EntityAutoCode(order = 35, listShow = true)
    private EmployeeStatusEnum status;

    @MetaData(value = "入职时间")
    @EntityAutoCode(order = 40, listShow = true)
    private Date dutyDate;

    @MetaData(value = "离职时间")
    @EntityAutoCode(order = 45, listShow = true)
    private Date leaveDate;

    private List<Contract> contracts;

    @Column(length = 20, nullable = false, unique = true)
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

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    @Column(length = 20, nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public EmployeeEnum getType() {
        return type;
    }

    public void setType(EmployeeEnum type) {
        this.type = type;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public StationEnum getStation() {
        return station;
    }

    public void setStation(StationEnum station) {
        this.station = station;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public EmployeeStatusEnum getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatusEnum status) {
        this.status = status;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "duty_date")
    @JsonSerialize(using = DateJsonSerializer.class)
    public Date getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(Date dutyDate) {
        this.dutyDate = dutyDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "leave_date")
    @JsonSerialize(using = DateJsonSerializer.class)
    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    private String headUrl;

    private String nickName;

    @Column(name = "head_url", length = 100)
    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    @Column(name = "nick_name", length = 30)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonIgnore
    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
