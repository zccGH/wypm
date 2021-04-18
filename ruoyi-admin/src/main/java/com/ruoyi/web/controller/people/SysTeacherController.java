package com.ruoyi.web.controller.people;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.entity.SysTeacher;
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
import com.ruoyi.system.service.ISysTeacherService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 教师信息Controller
 *
 * @author ruoyi
 * @date 2021-03-29
 */
@RestController
@RequestMapping("/people/teacher")
public class SysTeacherController extends BaseController
{
    @Autowired
    private ISysTeacherService sysTeacherService;

    /**
     * 查询教师信息列表
     */
    @PreAuthorize("@ss.hasPermi('people:teacher:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysTeacher sysTeacher)
    {
        startPage();
        List<SysTeacher> list = sysTeacherService.selectSysTeacherList(sysTeacher);
        return getDataTable(list);
    }

    /**
     * 导出教师信息列表
     */
    @PreAuthorize("@ss.hasPermi('people:teacher:export')")
    @Log(title = "教师信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysTeacher sysTeacher)
    {
        List<SysTeacher> list = sysTeacherService.selectSysTeacherList(sysTeacher);
        ExcelUtil<SysTeacher> util = new ExcelUtil<SysTeacher>(SysTeacher.class);
        return util.exportExcel(list, "teacher");
    }

    /**
     * 获取教师信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('people:teacher:query')")
    @GetMapping(value = "/{teacherId}")
    public AjaxResult getInfo(@PathVariable("teacherId") Long teacherId)
    {
        return AjaxResult.success(sysTeacherService.selectSysTeacherById(teacherId));
    }

    /**
     * 新增教师信息
     */
    @PreAuthorize("@ss.hasPermi('people:teacher:add')")
    @Log(title = "教师信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTeacher sysTeacher)
    {
        return toAjax(sysTeacherService.insertSysTeacher(sysTeacher));
    }

    /**
     * 修改教师信息
     */
    @PreAuthorize("@ss.hasPermi('people:teacher:edit')")
    @Log(title = "教师信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTeacher sysTeacher)
    {
        return toAjax(sysTeacherService.updateSysTeacher(sysTeacher));
    }

    /**
     * 删除教师信息
     */
    @PreAuthorize("@ss.hasPermi('people:teacher:remove')")
    @Log(title = "教师信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teacherIds}")
    public AjaxResult remove(@PathVariable Long[] teacherIds)
    {
        return toAjax(sysTeacherService.deleteSysTeacherByIds(teacherIds));
    }

    @PreAuthorize("@ss.hasPermi('people:teacher:edit')")
    @Log(title = "教师头像上传",businessType = BusinessType.OTHER)
    @PostMapping("/uploadAvatar")
    public AjaxResult uploadAvatar(MultipartFile file)
    {
        if (file.isEmpty()) {
            return AjaxResult.error(HttpStatus.IMG_IS_NULL, "上传图片不能为空！");
        }
        if (!file.getOriginalFilename().endsWith(".jpg")&&!file.getOriginalFilename().endsWith(".JPG")){
            return AjaxResult.error(HttpStatus.IMG_FORMAT_ERROR, "图片只能是jpg格式！");
        }
        String upload ="";
        try {
             upload = FileUploadUtils.upload(RuoYiConfig.getUploadPath(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.success("上传成功",upload);
    }
}
