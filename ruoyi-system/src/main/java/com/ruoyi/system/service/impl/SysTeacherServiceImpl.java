package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysTeacher;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysTeacherMapper;
import com.ruoyi.system.service.ISysTeacherService;

/**
 * 教师信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-29
 */
@Service
public class SysTeacherServiceImpl implements ISysTeacherService
{
    @Autowired
    private SysTeacherMapper sysTeacherMapper;

    /**
     * 查询教师信息
     *
     * @param teacherId 教师信息ID
     * @return 教师信息
     */
    @Override
    public SysTeacher selectSysTeacherById(Long teacherId)
    {
        return sysTeacherMapper.selectSysTeacherById(teacherId);
    }

    /**
     * 查询教师信息列表
     *
     * @param sysTeacher 教师信息
     * @return 教师信息
     */
    @Override
    public List<SysTeacher> selectSysTeacherList(SysTeacher sysTeacher)
    {
        return sysTeacherMapper.selectSysTeacherList(sysTeacher);
    }

    /**
     * 新增教师信息
     *
     * @param sysTeacher 教师信息
     * @return 结果
     */
    @Override
    public int insertSysTeacher(SysTeacher sysTeacher)
    {
        sysTeacher.setCreateTime(DateUtils.getNowDate());
        return sysTeacherMapper.insertSysTeacher(sysTeacher);
    }

    /**
     * 修改教师信息
     *
     * @param sysTeacher 教师信息
     * @return 结果
     */
    @Override
    public int updateSysTeacher(SysTeacher sysTeacher)
    {
        sysTeacher.setUpdateTime(DateUtils.getNowDate());
        return sysTeacherMapper.updateSysTeacher(sysTeacher);
    }

    /**
     * 批量删除教师信息
     *
     * @param teacherIds 需要删除的教师信息ID
     * @return 结果
     */
    @Override
    public int deleteSysTeacherByIds(Long[] teacherIds)
    {
        return sysTeacherMapper.deleteSysTeacherByIds(teacherIds);
    }

    /**
     * 删除教师信息信息
     *
     * @param teacherId 教师信息ID
     * @return 结果
     */
    @Override
    public int deleteSysTeacherById(Long teacherId)
    {
        return sysTeacherMapper.deleteSysTeacherById(teacherId);
    }
}
