package com.ufo.appi.dto;

import java.io.Serializable;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: khe
 * 创建时间: 2015/1/25 12:12
 *
 * @verion 1.0
 */
public class Gysjbxx extends AbstractApiDto implements Serializable {

    private static String[] SOURCEPROP = {"code", "name", "contacts", "tel", "address", "nickName", "headUrl"};

    private static String[] TARGETPROP = {"ghsbh", "ghsmc", "ghslxr", "ghslxdh", "ghsdz", "nc", "txurl"};

    @Override
    protected String[] sourceStrs() {
        return SOURCEPROP;
    }

    @Override
    protected String[] targetStrs() {
        return TARGETPROP;
    }

    private static final long serialVersionUID = -8549810562291896404L;

    private String ghsbh;

    private String ghsmc;

    private String ghslxr;

    private String ghslxdh;

    private String ghsdz;

    private String nc;

    private String txurl;

    public String getGhsbh() {
        return ghsbh;
    }

    public void setGhsbh(String ghsbh) {
        this.ghsbh = ghsbh;
    }

    public String getGhsmc() {
        return ghsmc;
    }

    public void setGhsmc(String ghsmc) {
        this.ghsmc = ghsmc;
    }

    public String getGhslxr() {
        return ghslxr;
    }

    public void setGhslxr(String ghslxr) {
        this.ghslxr = ghslxr;
    }

    public String getGhslxdh() {
        return ghslxdh;
    }

    public void setGhslxdh(String ghslxdh) {
        this.ghslxdh = ghslxdh;
    }

    public String getGhsdz() {
        return ghsdz;
    }

    public void setGhsdz(String ghsdz) {
        this.ghsdz = ghsdz;
    }

    public String getNc() {
        return nc;
    }

    public void setNc(String nc) {
        this.nc = nc;
    }

    public String getTxurl() {
        return txurl;
    }

    public void setTxurl(String txurl) {
        this.txurl = txurl;
    }
}
