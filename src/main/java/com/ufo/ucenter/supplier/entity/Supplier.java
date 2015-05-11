package com.ufo.ucenter.supplier.entity;

import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;

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
@MetaData(value = "供货商基本信息")
@Entity
@Table(name = "tk_tb_bas_supplier")
public class Supplier extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = -7411825468681802503L;

    @MetaData(value = "供货商编号")
    @EntityAutoCode(order = 5, listShow = true)
    private String code;

    @MetaData(value = "供货商名称")
    @EntityAutoCode(order = 10, listShow = true)
    private String name;

    @MetaData(value = "供货商联系人")
    @EntityAutoCode(order = 15, listShow = true)
    private String contacts;

    @MetaData(value = "供货商联系电话")
    @EntityAutoCode(order = 20, listShow = true)
    private String tel;

    @MetaData(value = "供货商地址")
    @EntityAutoCode(order = 25, listShow = true)
    private String address;

    @MetaData(value = "所供产品名称")
    @EntityAutoCode(order = 22, listShow = false)
    private String productName;

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

    @Column(length = 30, nullable = false)
    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Column(length = 20, nullable = false)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(length = 2000)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
