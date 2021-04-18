package com.ruoyi.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 论文相关信息对象 sys_thesis
 *
 * @author ruoyi
 * @date 2021-04-11
 */
public class SysThesis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 论文题目 */
    @Excel(name = "论文题目")
    private String paperTitle;

    /** 论文字数 */
    @Excel(name = "论文字数")
    private Long paperWord;
    /** 论文路径 */
    private String paperPath;

    /** 论文完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "论文完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paperFinished;

    /** 论文引用文献数量 */
    @Excel(name = "论文引用文献数量")
    private Long paperReference;
    @Excel(name = "论文下载次数")
    private Integer downloadCount;

    /** 论文状态 1.初稿，2.定稿，3.发布/公开，4.私密 */
    @Excel(name = "论文状态 1.初稿，2.定稿，3.发布/公开，4.私密")
    private String paperStatus;

    /** 论文分类 ，按照学科分类 1.工学，2.理学.... */
    @Excel(name = "论文分类 ，按照学科分类 1.工学，2.理学....")
    private String paperClassify;

    /** 论文类型 1.毕业论文，2，非毕业论文 */
    @Excel(name = "论文类型 1.毕业论文，2，非毕业论文")
    private String paperType;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPaperTitle(String paperTitle)
    {
        this.paperTitle = paperTitle;
    }

    public String getPaperTitle()
    {
        return paperTitle;
    }
    public void setPaperWord(Long paperWord)
    {
        this.paperWord = paperWord;
    }

    public Long getPaperWord()
    {
        return paperWord;
    }
    public void setPaperFinished(Date paperFinished)
    {
        this.paperFinished = paperFinished;
    }

    public Date getPaperFinished()
    {
        return paperFinished;
    }
    public void setPaperReference(Long paperReference)
    {
        this.paperReference = paperReference;
    }

    public Long getPaperReference()
    {
        return paperReference;
    }
    public void setPaperStatus(String paperStatus)
    {
        this.paperStatus = paperStatus;
    }

    public String getPaperStatus()
    {
        return paperStatus;
    }
    public void setPaperClassify(String paperClassify)
    {
        this.paperClassify = paperClassify;
    }

    public String getPaperClassify()
    {
        return paperClassify;
    }
    public void setPaperType(String paperType)
    {
        this.paperType = paperType;
    }

    public String getPaperType()
    {
        return paperType;
    }

    public String getPaperPath() {
        return paperPath;
    }

    public void setPaperPath(String paperPath) {
        this.paperPath = paperPath;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("paperTitle", getPaperTitle())
            .append("paperWord", getPaperWord())
            .append("paperFinished", getPaperFinished())
            .append("paperReference", getPaperReference())
            .append("paperStatus", getPaperStatus())
            .append("paperClassify", getPaperClassify())
            .append("paperType", getPaperType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
