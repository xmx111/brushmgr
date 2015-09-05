package com.ufo.ucenter.user.entity;

import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.UndeleteEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 类名称：User
 * 类描述：用户
 *
 * 创建人：hekang
 * 创建时间：2015-8-27 上午9:20:55
 * @version
 *
 */
@MetaData(value = "用户数据")
@Entity
@Table(name = "tb_ucenter_user")
public class User extends UndeleteEntity implements java.io.Serializable {

        public static enum UserTypeEnum {

            @MetaData(value = "买手")
            BUYER,

            @MetaData(value = "商家")
            SELLER;

        }

    private static final long serialVersionUID = -1745787432951463882L;

    @MetaData(value = "用户名")
    @EntityAutoCode(order = 5, listShow = true)
    private String loginName;

    @MetaData(value = "密码")
    @EntityAutoCode(order = 10, listShow = true)
    private String password;

    @MetaData(value = "邮箱")
    @EntityAutoCode(order = 15, listShow = true)
    private String email;

    @MetaData(value = "qq")
    @EntityAutoCode(order = 20, listShow = true)
    private String qq;

    @MetaData(value = "手机")
    @EntityAutoCode(order = 25, listShow = true)
    private String mobile;

    @MetaData(value = "昵称")
    @EntityAutoCode(order = 30, listShow = true)
    private String nickName;

    @MetaData(value = "真实名称")
    @EntityAutoCode(order = 35, listShow = true)
    private String factName;

    @MetaData(value = "电话")
    @EntityAutoCode(order = 40, listShow = true)
    private String tel;

    @MetaData(value = "地址")
    @EntityAutoCode(order = 45, listShow = true)
    private String address;

    @MetaData(value = "类型")
    @EntityAutoCode(order = 50, listShow = true)
    private UserTypeEnum type;

    @Column(name = "login_name", length = 20, unique = true)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(length = 20)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 20)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Column(length = 20)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "nick_name", length = 20)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(name = "fact_name", length = 20)
    public String getFactName() {
        return factName;
    }

    public void setFactName(String factName) {
        this.factName = factName;
    }

    @Column(name = "tel", length = 20)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name = "address", length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column
    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }
}
