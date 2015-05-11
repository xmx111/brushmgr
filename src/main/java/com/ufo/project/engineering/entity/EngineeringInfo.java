package com.ufo.project.engineering.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufo.core.annotation.EntityAutoCode;
import com.ufo.core.annotation.MetaData;
import com.ufo.core.entity.OperatorEntity;
import com.ufo.project.contract.entity.Contract;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * 类名称:
 * 类描述:
 * <p/>
 * 创建人: hekang
 * 创建时间: 下午1:23
 *
 * @verion 1.0
 */
@MetaData(value = "材料基础信息")
@Entity
@Table(name = "tb_proj_engineering_info")
public class EngineeringInfo extends OperatorEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1262578316995273770L;

    public static enum EditTypeEnum {

        @MetaData(value = "未编辑")
        NOTEDIT,

        @MetaData(value = "已编辑")
        EDITED

    }

    @MetaData(value = "工程合同")
    @EntityAutoCode(order = 5, listShow = true)
    private Contract contract;

    @MetaData(value = "是否编辑")
    @EntityAutoCode(order = 10, listShow = true)
    private EditTypeEnum type = EditTypeEnum.NOTEDIT;

    @MetaData(value = "项目概况")
    @EntityAutoCode(order = 15, listShow = true)
    private String about;

    @MetaData(value = "效果图")
    @EntityAutoCode(order = 20, listShow = true)
    private String drawing;

    @MetaData(value = "资金规划")
    @EntityAutoCode(order = 25, listShow = true)
    private String planning;

    @MetaData(value = "特殊要求")
    @EntityAutoCode(order = 30, listShow = true)
    private String special;

    @MetaData(value = "业主寄语")
    @EntityAutoCode(order = 35, listShow = true)
    private String word;

    @MetaData(value = "业主寄语录像")
    @EntityAutoCode(order = 40, listShow = true)
    private String video;

    private String videoUrl;

    private List<RelatedPersonal> relatedPersonals;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public EditTypeEnum getType() {
        return type;
    }

    public void setType(EditTypeEnum type) {
        this.type = type;
    }

    @Lob
    @Basic
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Lob
    @Basic
    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }

    @Lob
    @Basic
    public String getPlanning() {
        return planning;
    }

    public void setPlanning(String planning) {
        this.planning = planning;
    }

    @Lob
    @Basic
    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    @Lob
    @Basic
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Column(length = 100)
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Column(name = "video_url", length = 100)
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @OneToMany(mappedBy = "engineeringInfo", cascade = {CascadeType.ALL})
    @Fetch(FetchMode.SUBSELECT)
    @Where(clause = "deleted=0")
    @OrderBy("id")
    @JsonIgnore
    public List<RelatedPersonal> getRelatedPersonals() {
        return relatedPersonals;
    }

    public void setRelatedPersonals(List<RelatedPersonal> relatedPersonals) {
        this.relatedPersonals = relatedPersonals;
    }

    @Transient
    private RelatedPersonal estate;

    @Transient
    private RelatedPersonal designer;

    @Transient
    private RelatedPersonal projectmanager;

    @Transient
    private RelatedPersonal projectbutler;

    @Transient
    private RelatedPersonal deependesigner;

    @Transient
    public RelatedPersonal getEstate() {
        return estate;
    }

    public void setEstate(RelatedPersonal estate) {
        this.estate = estate;
    }

    @Transient
    public RelatedPersonal getDesigner() {
        return designer;
    }

    public void setDesigner(RelatedPersonal designer) {
        this.designer = designer;
    }

    @Transient
    public RelatedPersonal getProjectmanager() {
        return projectmanager;
    }

    public void setProjectmanager(RelatedPersonal projectmanager) {
        this.projectmanager = projectmanager;
    }

    @Transient
    public RelatedPersonal getProjectbutler() {
        return projectbutler;
    }

    public void setProjectbutler(RelatedPersonal projectbutler) {
        this.projectbutler = projectbutler;
    }

    @Transient
    public RelatedPersonal getDeependesigner() {
        return deependesigner;
    }

    public void setDeependesigner(RelatedPersonal deependesigner) {
        this.deependesigner = deependesigner;
    }

    private String editorName;

    @Transient
    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }
}
