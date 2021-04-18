package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 学生对象
 *
 * @author ruoyi
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生ID */
    private Long id;

    /** 学院ID */
    @Excel(name = "学院编号", type = Type.IMPORT)
    private Long deptId;

    /** 学生名称 */
    @Excel(name = "学生名称")
    private String studentName;

    /** 学生邮箱 */
    @Excel(name = "学生邮箱")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 学生性别 */
    @Excel(name = "学生性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 学生头像 */
    private String avatar;


    /** 学生类型（0毕业 1在校 2应届） */
    @Excel(name = "学生类型", readConverterExp = "0=毕业,1=在校,2=应届")
    private String type;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 学院对象 */
    @Excels({
        @Excel(name = "学院名称", targetAttr = "deptName", type = Type.EXPORT),
        @Excel(name = "学院负责人", targetAttr = "leader", type = Type.EXPORT)
    })
    private SysDept dept;


    public Student()
    {

    }

    public Student(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    @NotBlank(message = "学生姓名不能为空")
    @Size(min = 0, max = 30, message = "学生姓名长度不能超过30个字符")
    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public SysDept getDept()
    {
        return dept;
    }

    public void setDept(SysDept dept)
    {
        this.dept = dept;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getId())
            .append("deptId", getDeptId())
            .append("studentName", getStudentName())
            .append("email", getEmail())
            .append("phonenumber", getPhonenumber())
            .append("sex", getSex())
            .append("avatar", getAvatar())
            .append("type", getType())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("dept", getDept())
            .toString();
    }
}
