package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.Student;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生表 数据层
 *
 * @author ruoyi
 */
public interface SysStudentMapper
{
    /**
     * 根据条件分页查询学生列表
     *
     * @param student 学生信息
     * @return 学生信息集合信息
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 通过学生名查询学生
     *
     * @param studentName 学生名
     * @return 学生对象信息
     */
    public Student selectStudentByStudentName(String studentName);

    /**
     * 通过学生ID查询学生
     *
     * @param id 学生ID
     * @return 学生对象信息
     */
    public Student selectStudentById(Long id);

    /**
     * 新增学生信息
     *
     * @param student 学生信息
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学生信息
     *
     * @param student 学生信息
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 修改学生头像
     *
     * @param userName 学生名
     * @param avatar 头像地址
     * @return 结果
     */
    public int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

    /**
     * 重置学生密码
     *
     * @param userName 学生名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

    /**
     * 通过学生ID删除学生
     *
     * @param userId 学生ID
     * @return 结果
     */
    public int deleteUserById(Long userId);

    /**
     * 批量删除学生信息
     *
     * @param ids 需要删除的学生ID
     * @return 结果
     */
    public int deleteStudentByIds(Long[] ids);

    /**
     * 校验学生名称是否唯一
     *
     * @param userName 学生名称
     * @return 结果
     */
    public int checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public Student checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 学生邮箱
     * @return 结果
     */
    public Student checkEmailUnique(String email);
}
