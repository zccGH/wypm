package com.ruoyi.system.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.core.domain.entity.SysCollage;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCollageMapper;
import com.ruoyi.common.core.domain.entity.SysCollage;
import com.ruoyi.system.service.ISysCollageService;

/**
 * 学院Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-17
 */
@Service
public class SysCollageServiceImpl implements ISysCollageService {

    @Autowired
    private SysCollageMapper sysCollageMapper;

    /**
     * 查询学院
     *
     * @param collageId 学院ID
     * @return 学院
     */
    @Override
    public SysCollage selectSysCollageById(Long collageId)
    {
        return sysCollageMapper.selectSysCollageById(collageId);
    }

    /**
     * 查询学院列表
     *
     * @param sysCollage 学院
     * @return 学院
     */
    @Override
    public List<SysCollage> selectSysCollageList(SysCollage sysCollage)
    {
        return sysCollageMapper.selectSysCollageList(sysCollage);
    }

    /**
     * 新增学院
     *
     * @param sysCollage 学院
     * @return 结果
     */
    @Override
    public int insertSysCollage(SysCollage sysCollage)
    {
        sysCollage.setCreateTime(DateUtils.getNowDate());
        return sysCollageMapper.insertSysCollage(sysCollage);
    }

    /**
     * 修改学院
     *
     * @param sysCollage 学院
     * @return 结果
     */
    @Override
    public int updateSysCollage(SysCollage sysCollage)
    {
        sysCollage.setUpdateTime(DateUtils.getNowDate());
        return sysCollageMapper.updateSysCollage(sysCollage);
    }

    /**
     * 批量删除学院
     *
     * @param collageIds 需要删除的学院ID
     * @return 结果
     */
    @Override
    public int deleteSysCollageByIds(Long[] collageIds)
    {
        return sysCollageMapper.deleteSysCollageByIds(collageIds);
    }

    /**
     * 删除学院信息
     *
     * @param collageId 学院ID
     * @return 结果
     */
    @Override
    public int deleteSysCollageById(Long collageId)
    {
        return sysCollageMapper.deleteSysCollageById(collageId);
    }

    /**
     * 构建学院树结构
     * @param list
     * @return
     */
    @Override
    public List<TreeSelect> buildCollageTreeSelect(List<SysCollage> list) {
        List<SysCollage> sysCollages = this.buildCollageTree(list);
        return sysCollages.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要的树结构
     * @param list
     * @return
     */
    @Override
    public List<SysCollage> buildCollageTree(List<SysCollage> list) {
        List<SysCollage> returnList= Lists.newArrayList();
        List<Long> tempList=Lists.newArrayList();
        for (SysCollage sysCollage : list) {
            tempList.add(sysCollage.getCollageId());
        }
        for (Iterator<SysCollage> iterator=list.iterator();iterator.hasNext();){
            SysCollage sysCollage = (SysCollage) iterator.next();
            //如果时顶级节点，遍历该节点的所有父节点
            if (!tempList.contains(sysCollage.getParentId())) {
                recursionFn(list, sysCollage);
                returnList.add(sysCollage);
            }
        }
        if (returnList.isEmpty()){
            returnList=list;
        }
        return returnList;
    }

    /**
     * 递归列表
     * @param list
     * @param sysCollage
     */
    private void recursionFn(List<SysCollage> list,SysCollage sysCollage){
        //得到子节点列表
        List<SysCollage> childList=this.getChildList(list, sysCollage);
        sysCollage.setChildren(childList);
        for (SysCollage collage : childList) {
            //判断字节点是否还有子节点
            if (this.hasChild(list, collage)) {
                //如果有字节点，递归遍历
                this.recursionFn(list, collage);
            }
        }
    }

    private List<SysCollage> getChildList(List<SysCollage> sysCollages,SysCollage sysCollage){
        List<SysCollage> list=Lists.newArrayList();
        Iterator<SysCollage> iterator=sysCollages.iterator();
        while (iterator.hasNext()) {
            SysCollage collage = iterator.next();
            if (StringUtils.isNotNull(collage.getCollageId())&&collage.getParentId().longValue()==sysCollage.getCollageId().longValue()) {
                list.add(collage);
            }
        }
        return list;
    }

    private boolean hasChild(List<SysCollage> list,SysCollage sysCollage){
        return this.getChildList(list, sysCollage).size()>0?true:false;
    }
}
