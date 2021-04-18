package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysProject;

import java.util.List;

/**
 * 项目Service接口
 *
 * @author ruoyi
 * @date 2021-04-13
 */
public interface ISysProjectService
{
    /**
     * 查询项目
     *
     * @param id 项目ID
     * @return 项目
     */
    public SysProject selectSysProjectById(Long id);

    /**
     * 查询项目列表
     *
     * @param sysProject 项目
     * @return 项目集合
     */
    public List<SysProject> selectSysProjectList(SysProject sysProject);

    /**
     * 新增项目
     *
     * @param sysProject 项目
     * @return 结果
     */
    public int insertSysProject(SysProject sysProject);

    /**
     * 修改项目
     *
     * @param sysProject 项目
     * @return 结果
     */
    public int updateSysProject(SysProject sysProject);

    /**
     * 批量删除项目
     *
     * @param ids 需要删除的项目ID
     * @return 结果
     */
    public int deleteSysProjectByIds(Long[] ids);

    /**
     * 删除项目信息
     *
     * @param id 项目ID
     * @return 结果
     */
    public int deleteSysProjectById(Long id);
}
