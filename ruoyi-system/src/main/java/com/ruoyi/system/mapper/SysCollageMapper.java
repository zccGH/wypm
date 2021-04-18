package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysCollage;

import java.util.List;

/**
 * 学院Mapper接口
 *
 * @author ruoyi
 * @date 2021-03-17
 */
public interface SysCollageMapper
{
    /**
     * 查询学院
     *
     * @param collageId 学院ID
     * @return 学院
     */
    public SysCollage selectSysCollageById(Long collageId);

    /**
     * 查询学院列表
     *
     * @param sysCollage 学院
     * @return 学院集合
     */
    public List<SysCollage>  selectSysCollageList(SysCollage sysCollage);

    /**
     * 新增学院
     *
     * @param sysCollage 学院
     * @return 结果
     */
    public int insertSysCollage(SysCollage sysCollage);

    /**
     * 修改学院
     *
     * @param sysCollage 学院
     * @return 结果
     */
    public int updateSysCollage(SysCollage sysCollage);

    /**
     * 删除学院
     *
     * @param collageId 学院ID
     * @return 结果
     */
    public int deleteSysCollageById(Long collageId);

    /**
     * 批量删除学院
     *
     * @param collageIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCollageByIds(Long[] collageIds);
}
