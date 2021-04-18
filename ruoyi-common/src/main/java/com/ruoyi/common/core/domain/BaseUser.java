package com.ruoyi.common.core.domain;

import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;

/**
 * @author 赵成成
 * @version 1.0
 * @Description 用户基类，用于定义系统用户通用属性
 * @date
 */
public class BaseUser implements Serializable {
    private static final long serialVersionUID = -8854709499801867302L;

    /** 部门ID */
    @Excel(name = "部门编号", type = Excel.Type.IMPORT)
    private Long deptId;
}
