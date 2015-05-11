package com.ufo.project.quote.entity;

import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.project.contract.entity.Contract;

import javax.persistence.*;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: khe
 * 创建时间: 2015/1/18 21:05
 *
 * @verion 1.0
 */
@MetaData(value = "工程报价信息")
@Entity
@Table(name = "tb_proj_quote")
public class Quote extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = -3375084363873066570L;

    @MetaData(value = "合同")
    private Contract contract;

    @MetaData(value = "报价编号")
    @EntityAutoCode(order = 5, listShow = true)
    private Integer code;

    @MetaData(value = "报价名称")
    @EntityAutoCode(order = 10, listShow = true)
    private String name;

    @MetaData(value = "报价描述")
    @EntityAutoCode(order = 15, listShow = true)
    private String describes;

    @MetaData(value = "报价图片")
    @EntityAutoCode(order = 20, listShow = true)
    private String url;

    @MetaData(value = "报价人姓名")
    @EntityAutoCode(order = 25, listShow = true)
    private String quoter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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
    public String getQuoter() {
        return quoter;
    }

    public void setQuoter(String quoter) {
        this.quoter = quoter;
    }
}
