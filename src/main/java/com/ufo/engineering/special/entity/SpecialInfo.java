package com.ufo.engineering.special.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ufo.config.sys.entity.AttachmentFile;
import com.ufo.config.sys.entity.Manager;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.core.web.json.LongTimestampJsonSerializer;
import com.ufo.engineering.order.entity.AcceptanceImage;
import com.ufo.project.contract.entity.Contract;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 下午4:30
 *
 * @verion 1.0
 */
@MetaData(value = "工程特殊信息")
@Entity
@Table(name = "tb_engi_special_info")
public class SpecialInfo extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = 9084931429892296848L;

    @MetaData(value = "合同")
    private Contract contract;

    private Timestamp time;

    @MetaData(value = "采集人")
    private Manager collector;

    @MetaData(value = "文字描述")
    private String describes;

    private List<SpecialImage> images;

    @MetaData(value = "录音资料")
    private String video;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @JsonSerialize(using = LongTimestampJsonSerializer.class)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collector_id")
    @JsonIgnore
    public Manager getCollector() {
        return collector;
    }

    public void setCollector(Manager collector) {
        this.collector = collector;
    }

    @Column(length = 500)
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @OneToMany(mappedBy = "specialInfo", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonIgnore
    public List<SpecialImage> getImages() {
        return images;
    }

    public void setImages(List<SpecialImage> images) {
        this.images = images;
    }

    @Column(length = 100)
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Transient
    private String collectorLoginName;

    @Transient
    private String collectorName;

    public String getCollectorLoginName() {
        return collectorLoginName;
    }

    public void setCollectorLoginName(String collectorLoginName) {
        this.collectorLoginName = collectorLoginName;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }
}
