package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysTeacher;

import java.util.List;

/**
 * 教师信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-03-29
 */
public interface SysTeacherMapper
{
    /**
     * 查询教师信息
     *
     * @param teacherId 教师信息ID
     * @return 教师信息
     */
    public SysTeacher selectSysTeacherById(Long teacherId);

    /**
     * 查询教师信息列表
     *
     * @param sysTeacher 教师信息
     * @return 教师信息集合
     */
    public List<SysTeacher> selectSysTeacherList(SysTeacher sysTeacher);

    /**
     * 新增教师信息
     *
     * @param sysTeacher 教师信息
     * @return 结果
     */
    public int insertSysTeacher(SysTeacher sysTeacher);

    /**
     * 修改教师信息
     *
     * @param sysTeacher 教师信息
     * @return 结果
     */
    public int updateSysTeacher(SysTeacher sysTeacher);

    /**
     * 删除教师信息
     *
     * @param teacherId 教师信息ID
     * @return 结果
     */
    public int deleteSysTeacherById(Long teacherId);

    /**
     * 批量删除教师信息
     *
     * @param teacherIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysTeacherByIds(Long[] teacherIds);
}
