package com.ufo.appi.dto;

/**
 * 企业新闻
 * @author appdev
 *
 */
public class QyNews extends AbstractApiDto{
    private static String[] SOURCEPROP = {"id", "title","createTime"};

    private static String[] TARGETPROP = {"id", "title", "time"};

    @Override
    protected String[] sourceStrs() {
        return SOURCEPROP;
    }

    @Override
    protected String[] targetStrs() {
        return TARGETPROP;
    }
    
    private Integer id;//	id	integer
    private String title;//	标题	varchar(200)
    private String time;//	时间	varchar(20)
    private String content;//	内容	富HTMl文本

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public QyNews() {
        // TODO Auto-generated constructor stub
    }

}
