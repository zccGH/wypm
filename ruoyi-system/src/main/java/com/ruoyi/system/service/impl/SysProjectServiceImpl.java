package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysProject;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysProjectMapper;
import com.ruoyi.system.service.ISysProjectService;

/**
 * 项目Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-13
 */
@Service
public class SysProjectServiceImpl implements ISysProjectService
{
    @Autowired
    private SysProjectMapper sysProjectMapper;

    /**
     * 查询项目
     *
     * @param id 项目ID
     * @return 项目
     */
    @Override
    public SysProject selectSysProjectById(Long id)
    {
        return sysProjectMapper.selectSysProjectById(id);
    }

    /**
     * 查询项目列表
     *
     * @param sysProject 项目
     * @return 项目
     */
    @Override
    public List<SysProject> selectSysProjectList(SysProject sysProject)
    {
        return sysProjectMapper.selectSysProjectList(sysProject);
    }

    /**
     * 新增项目
     *
     * @param sysProject 项目
     * @return 结果
     */
    @Override
    public int insertSysProject(SysProject sysProject)
    {
        sysProject.setCreateTime(DateUtils.getNowDate());
        return sysProjectMapper.insertSysProject(sysProject);
    }

    /**
     * 修改项目
     *
     * @param sysProject 项目
     * @return 结果
     */
    @Override
    public int updateSysProject(SysProject sysProject)
    {
        sysProject.setUpdateTime(DateUtils.getNowDate());
        return sysProjectMapper.updateSysProject(sysProject);
    }

    /**
     * 批量删除项目
     *
     * @param ids 需要删除的项目ID
     * @return 结果
     */
    @Override
    public int deleteSysProjectByIds(Long[] ids)
    {
        return sysProjectMapper.deleteSysProjectByIds(ids);
    }

    /**
     * 删除项目信息
     *
     * @param id 项目ID
     * @return 结果
     */
    @Override
    public int deleteSysProjectById(Long id)
    {
        return sysProjectMapper.deleteSysProjectById(id);
    }
}
