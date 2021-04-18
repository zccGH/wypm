package com.ruoyi.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目对象 sys_project
 *
 * @author ruoyi
 * @date 2021-04-13
 */
public class SysProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目参与人数 */
    @Excel(name = "项目参与人数")
    private Long projectPerson;

    /** 项目时间周期 xx天 */
    @Excel(name = "项目时间周期 xx天")
    private Long timePeriod;

    /** 项目确立时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目确立时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date projectTime;

    /** 项目完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTime;

    /** 项目进度，1-100整数表示 */
    @Excel(name = "项目进度，1-100整数表示")
    private Long projectSchedule;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getProjectName()
    {
        return projectName;
    }
    public void setProjectPerson(Long projectPerson)
    {
        this.projectPerson = projectPerson;
    }

    public Long getProjectPerson()
    {
        return projectPerson;
    }
    public void setTimePeriod(Long timePeriod)
    {
        this.timePeriod = timePeriod;
    }

    public Long getTimePeriod()
    {
        return timePeriod;
    }
    public void setProjectTime(Date projectTime)
    {
        this.projectTime = projectTime;
    }

    public Date getProjectTime()
    {
        return projectTime;
    }
    public void setFinishTime(Date finishTime)
    {
        this.finishTime = finishTime;
    }

    public Date getFinishTime()
    {
        return finishTime;
    }
    public void setProjectSchedule(Long projectSchedule)
    {
        this.projectSchedule = projectSchedule;
    }

    public Long getProjectSchedule()
    {
        return projectSchedule;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectName", getProjectName())
            .append("projectPerson", getProjectPerson())
            .append("timePeriod", getTimePeriod())
            .append("projectTime", getProjectTime())
            .append("finishTime", getFinishTime())
            .append("projectSchedule", getProjectSchedule())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
