package com.ruoyi.web.controller.project;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysProject;
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
import com.ruoyi.system.service.ISysProjectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目Controller
 *
 * @author ruoyi
 * @date 2021-04-13
 */
@RestController
@RequestMapping("/project/project")
public class SysProjectController extends BaseController
{
    @Autowired
    private ISysProjectService sysProjectService;

    /**
     * 查询项目列表
     */
    @PreAuthorize("@ss.hasPermi('project:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysProject sysProject)
    {
        startPage();
        List<SysProject> list = sysProjectService.selectSysProjectList(sysProject);
        return getDataTable(list);
    }

    /**
     * 导出项目列表
     */
    @PreAuthorize("@ss.hasPermi('project:item:export')")
    @Log(title = "项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysProject sysProject)
    {
        List<SysProject> list = sysProjectService.selectSysProjectList(sysProject);
        ExcelUtil<SysProject> util = new ExcelUtil<SysProject>(SysProject.class);
        return util.exportExcel(list, "project");
    }

    /**
     * 获取项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysProjectService.selectSysProjectById(id));
    }

    /**
     * 新增项目
     */
    @PreAuthorize("@ss.hasPermi('project:item:add')")
    @Log(title = "项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysProject sysProject)
    {
        return toAjax(sysProjectService.insertSysProject(sysProject));
    }

    /**
     * 修改项目
     */
    @PreAuthorize("@ss.hasPermi('project:item:edit')")
    @Log(title = "项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysProject sysProject)
    {
        return toAjax(sysProjectService.updateSysProject(sysProject));
    }

    /**
     * 删除项目
     */
    @PreAuthorize("@ss.hasPermi('project:item:remove')")
    @Log(title = "项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysProjectService.deleteSysProjectByIds(ids));
    }
}
