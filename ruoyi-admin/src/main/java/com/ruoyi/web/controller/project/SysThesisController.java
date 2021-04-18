package com.ruoyi.web.controller.project;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.entity.Student;
import com.ruoyi.common.core.domain.entity.SysThesis;
import com.ruoyi.common.utils.file.FileUploadUtils;
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
import com.ruoyi.system.service.ISysThesisService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 论文相关信息Controller
 *
 * @author ruoyi
 * @date 2021-04-11
 */
@RestController
@RequestMapping("/project/thesis")
public class SysThesisController extends BaseController
{
    @Autowired
    private ISysThesisService sysThesisService;

    /**
     * 查询论文相关信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:thesis:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysThesis sysThesis)
    {
        startPage();
        List<SysThesis> list = sysThesisService.selectSysThesisList(sysThesis);
        return getDataTable(list);
    }

    /**
     * 导出论文相关信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:thesis:export')")
    @Log(title = "论文相关信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysThesis sysThesis)
    {
        List<SysThesis> list = sysThesisService.selectSysThesisList(sysThesis);
        ExcelUtil<SysThesis> util = new ExcelUtil<SysThesis>(SysThesis.class);
        return util.exportExcel(list, "thesis");
    }

    /**
     * 获取论文相关信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:thesis:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysThesisService.selectSysThesisById(id));
    }

    /**
     * 新增论文相关信息
     */
    @PreAuthorize("@ss.hasPermi('project:thesis:add')")
    @Log(title = "论文相关信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysThesis sysThesis)
    {
        return toAjax(sysThesisService.insertSysThesis(sysThesis));
    }

    /**
     * 修改论文相关信息
     */
    @PreAuthorize("@ss.hasPermi('project:thesis:edit')")
    @Log(title = "论文相关信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysThesis sysThesis)
    {
        return toAjax(sysThesisService.updateSysThesis(sysThesis));
    }

    /**
     * 删除论文相关信息
     */
    @PreAuthorize("@ss.hasPermi('project:thesis:remove')")
    @Log(title = "论文相关信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysThesisService.deleteSysThesisByIds(ids));
    }

    /**
     * 论文上传
     */
    @PreAuthorize("@ss.hasPermi('project:thesis:edit')")
    @Log(title = "论文上传", businessType = BusinessType.OTHER)
    @PostMapping("/uploadThesis")
    public AjaxResult uploadThesis(MultipartFile file,Long id)
    {
        if (file.isEmpty()) {
            return AjaxResult.error("上传文件为空！");
        }
        if (!file.getOriginalFilename().endsWith(".docx")&&!file.getOriginalFilename().endsWith(".doc")&&!file.getOriginalFilename().endsWith(".pdf")) {
            return AjaxResult.error("上传文件格式错误！");
        }
        try {
            String filePath = FileUploadUtils.upload(RuoYiConfig.getUploadPath(), file);
            return AjaxResult.success("上传成功",filePath);
        } catch (IOException e) {
            return AjaxResult.error("论文上传异常");
        }
    }

}
