package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.Student;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysStudentService;
import com.ruoyi.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysStudentServiceImpl implements ISysStudentService
{
    private static final Logger log = LoggerFactory.getLogger(SysStudentServiceImpl.class);

    @Autowired
    private SysStudentMapper sysStudentMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Autowired
    private ISysConfigService configService;

    /**
     * 根据条件分页查询学生列表
     *
     * @param student 学生信息
     * @return 学生信息集合信息
     */
    @Override
    public List<Student> selectStudentList(Student student)
    {
        return sysStudentMapper.selectStudentList(student);
    }

    /**
     * 通过学生名查询学生
     *
     * @param studentName 学生名
     * @return 学生对象信息
     */
    @Override
    public Student selectStudentByStudentName(String studentName)
    {
        return sysStudentMapper.selectStudentByStudentName(studentName);
    }

    /**
     * 通过学生ID查询学生
     *
     * @param id 学生ID
     * @return 学生对象信息
     */
    @Override
    public Student selectStudentById(Long id)
    {
        return this.sysStudentMapper.selectStudentById(id);
    }

    /**
     * 查询学生所属角色组
     *
     * @param userName 学生名
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(String userName)
    {
        List<SysRole> list = roleMapper.selectRolesByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (SysRole role : list)
        {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询学生所属岗位组
     *
     * @param userName 学生名
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(String userName)
    {
        List<SysPost> list = postMapper.selectPostsByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (SysPost post : list)
        {
            idsStr.append(post.getPostName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 校验学生名称是否唯一
     *
     * @param userName 学生名称
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(String userName)
    {
        int count = sysStudentMapper.checkUserNameUnique(userName);
        if (count > 0)
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验学生名称是否唯一
     *
     * @param student 学生信息
     * @return
     */
    @Override
    public String checkPhoneUnique(Student student)
    {
        Long id = StringUtils.isNull(student.getId()) ? -1L : student.getId();
        Student info = sysStudentMapper.checkPhoneUnique(student.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param student 学生信息
     * @return
     */
    @Override
    public String checkEmailUnique(Student student)
    {
        Long id = StringUtils.isNull(student.getId()) ? -1L : student.getId();
        Student info = sysStudentMapper.checkEmailUnique(student.getEmail());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验学生是否允许操作
     *
     * @param user 学生信息
     */
    @Override
    public void checkUserAllowed(SysUser user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin())
        {
            throw new CustomException("不允许操作超级管理员学生");
        }
    }

    /**
     * 新增保存学生信息
     *
     * @param student 学生信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertStudent(Student student)
    {
        // 新增学生信息
        int rows = this.sysStudentMapper.insertStudent(student);
        return rows;
    }

    /**
     * 修改保存学生信息
     *
     * @param student 学生信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateStudent(Student student)
    {
        return sysStudentMapper.updateStudent(student);
    }

    /**
     * 修改学生状态
     *
     * @param student 学生信息
     * @return 结果
     */
    @Override
    public int updateUserStatus(Student student)
    {
        return sysStudentMapper.updateStudent(student);
    }

    /**
     * 修改学生基本信息
     *
     * @param student 学生信息
     * @return 结果
     */
    @Override
    public int updateStudentProfile(Student student)
    {
        return sysStudentMapper.updateStudent(student);
    }

    /**
     * 修改学生头像
     *
     * @param userName 学生名
     * @param avatar 头像地址
     * @return 结果
     */
    @Override
    public boolean updateUserAvatar(String userName, String avatar)
    {
        return sysStudentMapper.updateUserAvatar(userName, avatar) > 0;
    }

    /**
     * 重置学生密码
     *
     * @param userName 学生名
     * @param password 密码
     * @return 结果
     */
    @Override
    public int resetUserPwd(String userName, String password)
    {
        return sysStudentMapper.resetUserPwd(userName, password);
    }

    /**
     * 新增学生角色信息
     *
     * @param user 学生对象
     */
    public void insertUserRole(SysUser user)
    {
        Long[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles))
        {
            // 新增学生与角色管理
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            for (Long roleId : roles)
            {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0)
            {
                userRoleMapper.batchUserRole(list);
            }
        }
    }

    /**
     * 新增学生岗位信息
     *
     * @param user 学生对象
     */
    public void insertUserPost(SysUser user)
    {
        Long[] posts = user.getPostIds();
        if (StringUtils.isNotNull(posts))
        {
            // 新增学生与岗位管理
            List<SysUserPost> list = new ArrayList<SysUserPost>();
            for (Long postId : posts)
            {
                SysUserPost up = new SysUserPost();
                up.setUserId(user.getUserId());
                up.setPostId(postId);
                list.add(up);
            }
            if (list.size() > 0)
            {
                userPostMapper.batchUserPost(list);
            }
        }
    }

    /**
     * 通过学生ID删除学生
     *
     * @param userId 学生ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteStudentById(Long userId)
    {
        // 删除学生与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 删除学生与岗位表
        userPostMapper.deleteUserPostByUserId(userId);
        return sysStudentMapper.deleteUserById(userId);
    }

    /**
     * 批量删除学生信息
     *
     * @param ids 需要删除的学生ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteStudentByIds(Long[] ids)
    {
        int result=0;
        if (null!=ids&&0<ids.length) {
            result=sysStudentMapper.deleteStudentByIds(ids);
        }
        return result;
    }

    /**
     * 导入学生数据
     * @param studentList 学生数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作学生
     * @return 结果
     */
    @Override
    public String importStudent(List<Student> studentList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(studentList) || studentList.size() == 0)
        {
            throw new CustomException("导入学生数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Student student : studentList)
        {
            try
            {
                // 验证是否存在这个学生
                Student lstudent = this.selectStudentByStudentName(student.getStudentName());
                if (StringUtils.isNull(lstudent))
                {
                    student.setCreateBy(operName);
                    this.insertStudent(student);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + student.getStudentName() + " 导入成功");
                }else if (isUpdateSupport)
                {
                    student.setId(lstudent.getId());
                    student.setUpdateBy(operName);
                    this.updateStudent(student);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + student.getStudentName() + " 更新成功");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + student.getStudentName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
