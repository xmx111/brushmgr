package com.ufo.ucenter.shop.entity;

import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.UndeleteEntity;

import javax.persistence.Column;

/**
 * 类名称：Shop
 * 类描述：店铺
 * <p/>
 * 创建人：hekang
 * 创建时间：2015/8/27 11:43
 */
public class Shop extends UndeleteEntity implements java.io.Serializable {

    private static final long serialVersionUID = 7076659309268664313L;

    public static enum ThirdPlatformEnum {

        @MetaData(value = "淘宝")
        TAOBAO,

        @MetaData(value = "天猫")
        TMALL,

        @MetaData(value = "京东")
        JD,

        @MetaData(value = "1号店")
        YHD,

        @MetaData(value = "亚马逊")
        AMAZON,

        @MetaData(value = "蘑菇街")
        MOGUJIE,

        @MetaData(value = "当当")
        DANGDANG;

    }

    @MetaData(value = "店铺主账户")
    @EntityAutoCode(order = 5, listShow = true)
    public String name;

    @MetaData(value = "揽件网店编码")
    @EntityAutoCode(order = 15, listShow = true)
    public String code;

    @MetaData(value = "店铺首页网址")
    @EntityAutoCode(order = 20, listShow = true)
    public String homeSite;

    @MetaData(value = "发件人手机号")
    @EntityAutoCode(order = 25, listShow = true)
    public String senderMobile;

    @MetaData(value = "发件人姓名")
    @EntityAutoCode(order = 30, listShow = true)
    public String senderName;

    @MetaData(value = "发件人身份证号")
    @EntityAutoCode(order = 35, listShow = true)
    public String senderNo;

    @MetaData(value = "发件人详细地址")
    @EntityAutoCode(order = 40, listShow = true)
    public String senderAddress;

    @MetaData(value = "店铺所在平台")
    @EntityAutoCode(order = 45, listShow = true)
    public ThirdPlatformEnum platform;

    @Column(length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 30)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "home_site", length = 200)
    public String getHomeSite() {
        return homeSite;
    }

    public void setHomeSite(String homeSite) {
        this.homeSite = homeSite;
    }

    @Column(name = "sender_mobile", length = 20)
    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
    }

    @Column(name = "sender_name", length = 20)
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Column(name = "sender_no", length = 20)
    public String getSenderNo() {
        return senderNo;
    }

    public void setSenderNo(String senderNo) {
        this.senderNo = senderNo;
    }

    @Column(name = "sender_address", length = 200)
    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    @Column
    public ThirdPlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(ThirdPlatformEnum platform) {
        this.platform = platform;
    }
}
