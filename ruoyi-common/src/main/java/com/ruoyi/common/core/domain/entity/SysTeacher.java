package com.ruoyi.common.core.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 教师信息对象 sys_teacher
 *
 * @author ruoyi
 * @date 2021-03-29
 */
public class SysTeacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教师id */
    private Long teacherId;

    /** 学院id */
    private Long collageId;

    /** 教师姓名 */
    @Excel(name = "教师姓名")
    private String teacherName;

    /** 头像 */
    private String avatar;

    /** 教师职称 */
    @Excel(name = "教师职称")
    private String teacherTitle;

    /** 教师年龄 */
    @Excel(name = "教师年龄")
    private Long age;

    /** 教龄 */
    @Excel(name = "教龄")
    private Long worktime;

    /** 所授专业 */
    @Excel(name = "所授专业")
    private String course;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String address;

    public void setTeacherId(Long teacherId)
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId()
    {
        return teacherId;
    }
    public void setCollageId(Long collageId)
    {
        this.collageId = collageId;
    }

    public Long getCollageId()
    {
        return collageId;
    }
    public void setTeacherName(String teacherName)
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName()
    {
        return teacherName;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setTeacherTitle(String teacherTitle)
    {
        this.teacherTitle = teacherTitle;
    }

    public String getTeacherTitle()
    {
        return teacherTitle;
    }
    public void setAge(Long age)
    {
        this.age = age;
    }

    public Long getAge()
    {
        return age;
    }
    public void setWorktime(Long worktime)
    {
        this.worktime = worktime;
    }

    public Long getWorktime()
    {
        return worktime;
    }
    public void setCourse(String course)
    {
        this.course = course;
    }

    public String getCourse()
    {
        return course;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getSex()
    {
        return sex;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teacherId", getTeacherId())
            .append("collageId", getCollageId())
            .append("teacherName", getTeacherName())
            .append("avatar", getAvatar())
            .append("teacherTitle", getTeacherTitle())
            .append("age", getAge())
            .append("worktime", getWorktime())
            .append("course", getCourse())
            .append("sex", getSex())
            .append("phone", getPhone())
            .append("address", getAddress())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
