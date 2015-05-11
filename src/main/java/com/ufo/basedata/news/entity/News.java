package com.ufo.basedata.news.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;

/**
 * 
* 类名称：News 
* 类描述： 
*
*
* 创建人：Zengzhiyong
* 创建时间：2015年1月17日 下午12:16:34 
* @version 
*
 */
@MetaData(value = "新闻资讯")
@Entity
@Table(name = "tb_bas_news")
public class News extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1259532810642044061L;

    public static enum TypeEnum {
        @MetaData(value = "大事件纪实")
        COMPANY,

        @MetaData(value = "最新资讯")
        INDUSTRY
    }

    @MetaData(value = "类型")
    @EntityAutoCode(order = 5, listShow = true)
    private TypeEnum type;
    
    @MetaData(value = "标题")
    @EntityAutoCode(order = 10, listShow = true)
    private String title;

    @MetaData(value = "详细内容")
    @EntityAutoCode(order = 15, listShow = false)
    private String content;

    @Column(nullable = false)
    public TypeEnum getType() {
        return type;
    }

    @Enumerated(EnumType.ORDINAL)
    public void setType(TypeEnum type) {
        this.type = type;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    @Basic
    @Column(nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
