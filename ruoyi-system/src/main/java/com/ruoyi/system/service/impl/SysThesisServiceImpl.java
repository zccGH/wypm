package com.ruoyi.system.service.impl;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysThesis;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysThesisMapper;
import com.ruoyi.system.service.ISysThesisService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 论文相关信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-11
 */
@Service
public class SysThesisServiceImpl implements ISysThesisService
{
    @Autowired
    private SysThesisMapper sysThesisMapper;

    /**
     * 查询论文相关信息
     *
     * @param id 论文相关信息ID
     * @return 论文相关信息
     */
    @Override
    public SysThesis selectSysThesisById(Long id)
    {
        return sysThesisMapper.selectSysThesisById(id);
    }

    /**
     * 查询论文相关信息列表
     *
     * @param sysThesis 论文相关信息
     * @return 论文相关信息
     */
    @Override
    public List<SysThesis> selectSysThesisList(SysThesis sysThesis)
    {
        return sysThesisMapper.selectSysThesisList(sysThesis);
    }

    /**
     * 新增论文相关信息
     *
     * @param sysThesis 论文相关信息
     * @return 结果
     */
    @Override
    public int insertSysThesis(SysThesis sysThesis)
    {
        sysThesis.setCreateTime(DateUtils.getNowDate());
        return sysThesisMapper.insertSysThesis(sysThesis);
    }

    /**
     * 修改论文相关信息
     *
     * @param sysThesis 论文相关信息
     * @return 结果
     */
    @Override
    public int updateSysThesis(SysThesis sysThesis)
    {
        sysThesis.setUpdateTime(DateUtils.getNowDate());
        return sysThesisMapper.updateSysThesis(sysThesis);
    }

    /**
     * 批量删除论文相关信息
     *
     * @param ids 需要删除的论文相关信息ID
     * @return 结果
     */
    @Override
    public int deleteSysThesisByIds(Long[] ids)
    {
        return sysThesisMapper.deleteSysThesisByIds(ids);
    }

    /**
     * 删除论文相关信息信息
     *
     * @param id 论文相关信息ID
     * @return 结果
     */
    @Override
    public int deleteSysThesisById(Long id)
    {
        return sysThesisMapper.deleteSysThesisById(id);
    }

}
