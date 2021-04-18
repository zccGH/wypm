package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.Student;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;

/**
 * 学生 业务层
 *
 * @author ruoyi
 */
public interface ISysStudentService
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
     * 根据学生ID查询学生所属角色组
     *
     * @param userName 学生名
     * @return 结果
     */
    public String selectUserRoleGroup(String userName);

    /**
     * 根据学生ID查询学生所属岗位组
     *
     * @param userName 学生名
     * @return 结果
     */
    public String selectUserPostGroup(String userName);

    /**
     * 校验学生名称是否唯一
     *
     * @param userName 学生名称
     * @return 结果
     */
    public String checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param student 学生信息
     * @return 结果
     */
    public String checkPhoneUnique(Student student);

    /**
     * 校验email是否唯一
     *
     * @param student 学生信息
     * @return 结果
     */
    public String checkEmailUnique(Student student);

    /**
     * 校验学生是否允许操作
     *
     * @param user 学生信息
     */
    public void checkUserAllowed(SysUser user);

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
     * 修改学生状态
     *
     * @param student 学生信息
     * @return 结果
     */
    public int updateUserStatus(Student student);

    /**
     * 修改学生基本信息
     *
     * @param student 学生信息
     * @return 结果
     */
    public int updateStudentProfile(Student student);

    /**
     * 修改学生头像
     *
     * @param userName 学生名
     * @param avatar 头像地址
     * @return 结果
     */
    public boolean updateUserAvatar(String userName, String avatar);


    /**
     * 重置学生密码
     *
     * @param userName 学生名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(String userName, String password);

    /**
     * 通过学生ID删除学生
     *
     * @param userId 学生ID
     * @return 结果
     */
    public int deleteStudentById(Long userId);

    /**
     * 批量删除学生信息
     *
     * @param ids 需要删除的学生ID
     * @return 结果
     */
    public int deleteStudentByIds(Long[] ids);

    /**
     * 导入学生数据
     *
     * @param studentList 学生数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作学生
     * @return 结果
     */
    public String importStudent(List<Student> studentList, Boolean isUpdateSupport, String operName);
}
