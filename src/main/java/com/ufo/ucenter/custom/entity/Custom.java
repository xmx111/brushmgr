package com.ufo.ucenter.custom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ufo.basedata.region.entity.Region;
import com.ufo.config.sys.entity.Manager;
import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.core.web.json.LongTimestampJsonSerializer;
import com.ufo.ucenter.employee.entity.Employee.SexEnum;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 下午12:46
 *
 * @verion 1.0
 */
@MetaData(value = "客户基本信息")
@Entity
@Table(name = "tk_tb_bas_custom")
public class Custom extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = -6108237021737611210L;

    public static enum RegisterEnum {

        @MetaData(value = "自主注册")
        SELF,

        @MetaData(value = "后台注册")
        SERVER
    }

    public static enum CheckEnum {
        @MetaData(value = "未验证")
        NOT,

        @MetaData(value = "已验证")
        YES
    }

    @MetaData(value = "客户编号")
    @EntityAutoCode(order = 5, listShow = true)
    private String code;

    @MetaData(value = "客户姓名")
    @EntityAutoCode(order = 10, listShow = true)
    private String name;

    @MetaData(value = "客户性别")
    @EntityAutoCode(order = 15, listShow = true)
    private SexEnum sex;

    @MetaData(value = "客户联系电话")
    @EntityAutoCode(order = 20, listShow = true)
    private String phone;

    @MetaData(value = "客户邮箱")
    @EntityAutoCode(order = 25, listShow = true)
    private String email;

    @MetaData(value = "客户QQ号")
    @EntityAutoCode(order = 30, listShow = true)
    private String qq;

    @MetaData(value = "客户家庭住址")
    @EntityAutoCode(order = 35, listShow = true)
    private String address;

    @MetaData(value = "配偶姓名")
    @EntityAutoCode(order = 40, listShow = true)
    private String spouseName;

    @MetaData(value = "配偶联系电话")
    @EntityAutoCode(order = 45, listShow = true)
    private String spousePhone;

    @MetaData(value = "配偶邮箱")
    @EntityAutoCode(order = 50, listShow = true)
    private String spouseEmail;

    @MetaData(value = "配偶QQ号")
    @EntityAutoCode(order = 55, listShow = true)
    private String spouseQq;

    @MetaData(value = "注册方式")
    @EntityAutoCode(order = 60, listShow = true)
    private RegisterEnum registerType;

    @MetaData(value = "邮箱验证状态")
    @EntityAutoCode(order = 65, listShow = false)
    private CheckEnum checked = CheckEnum.NOT;

    @MetaData(value = "注册时间")
    @EntityAutoCode(order = 70, listShow = false)
    private Timestamp registerTime;

    @MetaData(value = "注册人帐号")
    @EntityAutoCode(order = 75, listShow = false)
    private Manager registerOperator;

    @MetaData(value = "注册人姓名")
    @EntityAutoCode(order = 80, listShow = false)
    private String registerName;

    private Region region;

    @Column(length = 8, unique = true, nullable = false)
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

    @Column(length = 20, nullable = false, unique = true)
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

    @Column(length = 30)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Column(length = 120)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "spouse_name", length = 30)
    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Column(name = "spouse_phone", length = 20)
    public String getSpousePhone() {
        return spousePhone;
    }

    public void setSpousePhone(String spousePhone) {
        this.spousePhone = spousePhone;
    }

    @Column(name = "spouse_email", length = 50)
    public String getSpouseEmail() {
        return spouseEmail;
    }

    public void setSpouseEmail(String spouseEmail) {
        this.spouseEmail = spouseEmail;
    }

    @Column(name = "spouse_qq", length = 30)
    public String getSpouseQq() {
        return spouseQq;
    }

    public void setSpouseQq(String spouseQq) {
        this.spouseQq = spouseQq;
    }

    @Column(name = "register_type")
    @Enumerated(EnumType.ORDINAL)
    public RegisterEnum getRegisterType() {
        return registerType;
    }

    public void setRegisterType(RegisterEnum registerType) {
        this.registerType = registerType;
    }

    @Column
    @Enumerated(EnumType.ORDINAL)
    public CheckEnum getChecked() {
        return checked;
    }

    public void setChecked(CheckEnum checked) {
        this.checked = checked;
    }

    @Column(name = "register_time")
    @JsonSerialize(using = LongTimestampJsonSerializer.class)
    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "register_operator_id")
    @JsonIgnore
    public Manager getRegisterOperator() {
        return registerOperator;
    }

    public void setRegisterOperator(Manager registerOperator) {
        this.registerOperator = registerOperator;
    }

    @Column(name = "register_name")
    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    @JsonIgnore
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
