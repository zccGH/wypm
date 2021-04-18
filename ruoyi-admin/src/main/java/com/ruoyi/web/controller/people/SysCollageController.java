package com.ruoyi.web.controller.people;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysCollage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.ISysCollageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学院Controller
 *
 * @author ruoyi
 * @date 2021-03-17
 */
@RestController
@RequestMapping("/people/collage")
public class SysCollageController extends BaseController
{
    @Autowired
    private ISysCollageService sysCollageService;

    /**
     * 查询学院列表
     */
    @PreAuthorize("@ss.hasPermi('people:collage:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCollage sysCollage)
    {
        startPage();
        List<SysCollage> list = sysCollageService.selectSysCollageList(sysCollage);
        return getDataTable(list);
    }

    /**
     * 导出学院列表
     */
    @PreAuthorize("@ss.hasPermi('people:collage:export')")
    @Log(title = "学院", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysCollage sysCollage)
    {
        List<SysCollage> list = sysCollageService.selectSysCollageList(sysCollage);
        ExcelUtil<SysCollage> util = new ExcelUtil<SysCollage>(SysCollage.class);
        return util.exportExcel(list, "collage");
    }

    /**
     * 获取学院详细信息
     */
    @PreAuthorize("@ss.hasPermi('people:collage:query')")
    @GetMapping(value = "/{collageId}")
    public AjaxResult getInfo(@PathVariable("collageId") Long collageId)
    {
        return AjaxResult.success(sysCollageService.selectSysCollageById(collageId));
    }

    /**
     * 新增学院
     */
    @PreAuthorize("@ss.hasPermi('people:collage:add')")
    @Log(title = "学院", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCollage sysCollage)
    {
        return toAjax(sysCollageService.insertSysCollage(sysCollage));
    }

    /**
     * 修改学院
     */
    @PreAuthorize("@ss.hasPermi('people:collage:edit')")
    @Log(title = "学院", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCollage sysCollage)
    {
        return toAjax(sysCollageService.updateSysCollage(sysCollage));
    }

    /**
     * 删除学院
     */
    @PreAuthorize("@ss.hasPermi('people:collage:remove')")
    @Log(title = "学院", businessType = BusinessType.DELETE)
	@DeleteMapping("/{collageIds}")
    public AjaxResult remove(@PathVariable Long[] collageIds)
    {
        return toAjax(sysCollageService.deleteSysCollageByIds(collageIds));
    }

    /**
     * 获取学院树
     */
    @PreAuthorize("@ss.hasAnyPermi('people:collage:list')")
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysCollage sysCollage){
        List<SysCollage> sysCollages = this.sysCollageService.selectSysCollageList(sysCollage);
        return AjaxResult.success(this.sysCollageService.buildCollageTreeSelect(sysCollages));
    }
}
