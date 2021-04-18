package com.ruoyi.web.controller.people;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.Student;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysStudentService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 学生信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/people/student")
public class StudentController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysStudentService sysStudentService;

    /**
     * 获取学生列表
     */
    @PreAuthorize("@ss.hasPermi('people:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(Student student)
    {
        startPage();
        List<Student> list = this.sysStudentService.selectStudentList(student);
        return getDataTable(list);
    }

    /**
     * 导出学生信息
     * @param student
     * @return
     */
    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('people:student:export')")
    @GetMapping("/export")
    public AjaxResult export(Student student)
    {
        List<Student> list = this.sysStudentService.selectStudentList(student);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        return util.exportExcel(list, "学生数据");
    }

    /**
     * 导入学生信息
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @Log(title = "学生管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('people:student:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Student> excelUtil=new ExcelUtil<Student>(Student.class);
        List<Student> students = excelUtil.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = this.sysStudentService.importStudent(students, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 获取导入模板
     * @return
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        return util.importTemplateExcel("学生数据");
    }

    /**
     * 根据学生编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('people:student:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        Student student = this.sysStudentService.selectStudentById(id);
        ajax.put(AjaxResult.DATA_TAG,student);
        return ajax;
    }

    /**
     * 新增学生
     */
    @PreAuthorize("@ss.hasPermi('people:student:add')")
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Student student)
    {
        if (StringUtils.isNotEmpty(student.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(this.sysStudentService.checkPhoneUnique(student)))
        {
            return AjaxResult.error("新增学生'" + student.getStudentName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(student.getEmail())
                && UserConstants.NOT_UNIQUE.equals(this.sysStudentService.checkEmailUnique(student)))
        {
            return AjaxResult.error("新增学生'" + student.getStudentName() + "'失败，邮箱账号已存在");
        }
        student.setCreateBy(SecurityUtils.getUsername());
        return toAjax(this.sysStudentService.insertStudent(student));
    }

    /**
     * 修改学生
     */
    @PreAuthorize("@ss.hasPermi('people:student:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Student student)
    {
        if (StringUtils.isNotEmpty(student.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(this.sysStudentService.checkPhoneUnique(student)))
        {
            return AjaxResult.error("修改学生'" + student.getStudentName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(student.getEmail())
                && UserConstants.NOT_UNIQUE.equals(sysStudentService.checkEmailUnique(student)))
        {
            return AjaxResult.error("修改学生'" + student.getStudentName() + "'失败，邮箱账号已存在");
        }
        student.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(this.sysStudentService.updateStudent(student));
    }

    /**
     * 删除学生
     */
    @PreAuthorize("@ss.hasPermi('people:student:remove')")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(this.sysStudentService.deleteStudentByIds(ids));
    }


    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('people:student:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }

    @PreAuthorize("@ss.hasAnyPermi('people:student:edit')")
    @Log(title = "学生头像上传",businessType = BusinessType.OTHER)
    @PostMapping("/uploadAvatar")
    public AjaxResult uploadAvatar(@RequestParam("file") MultipartFile file){
        String result="";
        if (Objects.isNull(file)) {
            return AjaxResult.error(HttpStatus.IMG_IS_NULL,"图片文件为空");
        }
        String filename = file.getOriginalFilename();
        if (!filename.endsWith(".jpg")&&!filename.endsWith(".JPG")) {
            return AjaxResult.error(HttpStatus.IMG_FORMAT_ERROR,"图片只能是jpg格式!");
        }
        try {
             result = FileUploadUtils.upload(RuoYiConfig.getUploadPath(), file);
        } catch (IOException e) {
            return AjaxResult.error(HttpStatus.IMG_UPLOAD_ERROR, "图片上传异常");
        }
        return AjaxResult.success("上传成功",result);
    }
}
