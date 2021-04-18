package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysThesis;

import java.util.List;

/**
 * 论文相关信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-11
 */
public interface SysThesisMapper
{
    /**
     * 查询论文相关信息
     *
     * @param id 论文相关信息ID
     * @return 论文相关信息
     */
    public SysThesis selectSysThesisById(Long id);

    /**
     * 查询论文相关信息列表
     *
     * @param sysThesis 论文相关信息
     * @return 论文相关信息集合
     */
    public List<SysThesis> selectSysThesisList(SysThesis sysThesis);

    /**
     * 新增论文相关信息
     *
     * @param sysThesis 论文相关信息
     * @return 结果
     */
    public int insertSysThesis(SysThesis sysThesis);

    /**
     * 修改论文相关信息
     *
     * @param sysThesis 论文相关信息
     * @return 结果
     */
    public int updateSysThesis(SysThesis sysThesis);

    /**
     * 删除论文相关信息
     *
     * @param id 论文相关信息ID
     * @return 结果
     */
    public int deleteSysThesisById(Long id);

    /**
     * 批量删除论文相关信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysThesisByIds(Long[] ids);
}
