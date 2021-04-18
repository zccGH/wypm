<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--学院数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="deptName"
            placeholder="请输入学院名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="deptOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            :highlight-current="true"
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <el-col :span="20" :xs="24">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="教师姓名" prop="teacherName">
            <el-input
              v-model="queryParams.teacherName"
              placeholder="请输入教师姓名"
              clearable
              size="small"
              style="width: 150px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="教师职称" prop="teacherTitle">
            <el-input
              v-model="queryParams.teacherTitle"
              placeholder="请输入教师职称"
              clearable
              size="small"
              style="width: 150px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="所授专业" prop="course">
            <el-input
              v-model="queryParams.course"
              placeholder="请输入所授专业"
              clearable
              size="small"
              style="width: 150px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select style="width: 150px" v-model="queryParams.sex" placeholder="请选择性别" clearable size="small">
              <el-option
                v-for="sex in sexOptions"
                :label="sex.dictLabel"
                :value="sex.dictValue"
                :key="sex.dictValue"/>
            </el-select>
          </el-form-item>
          <el-form-item>
            <!--<el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
          </el-form-item>
        </el-form>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-search"
              size="mini"
              @click="handleQuery"
              v-hasPermi="['people:student:query']"
            >搜索</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-refresh"
              size="mini"
              @click="resetQuery"
            >重置</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['people:teacher:add']"
            >新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['people:teacher:edit']"
            >修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['people:teacher:remove']"
            >删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              plain
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['people:teacher:export']"
            >导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
        <el-table v-loading="loading" :data="teacherList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <!--<el-table-column label="教师id" align="center" prop="teacherId" />
          <el-table-column label="学院id" align="center" prop="collageId" />-->
          <el-table-column label="教师姓名" align="center" prop="teacherName" />
          <el-table-column label="头像" align="center" prop="avatar" >
            <template style="width: 80px;" slot-scope="scope">
              <img style="border-radius:40px;width:70px;height:70px;border:none;" :src="scope.row.avatar">
            </template>
          </el-table-column>
          <el-table-column label="教师职称" align="center" prop="teacherTitle" />
          <el-table-column label="教师年龄" align="center" prop="age" />
          <el-table-column label="教龄" align="center" prop="worktime" />
          <el-table-column label="所授专业" align="center" prop="course" />
          <el-table-column label="性别" align="center" prop="sex" :formatter="getTeacherSex" />
          <el-table-column label="联系方式" align="center" prop="phone" />
          <el-table-column label="联系地址" align="center" prop="address" />
          <el-table-column label="备注" align="center" prop="remark" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['people:teacher:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['people:teacher:remove']"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />

      </el-col>
    </el-row>
    <!-- 添加或修改教师信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="教师姓名" prop="teacherName">
          <el-input v-model="form.teacherName" placeholder="请输入教师姓名" />
        </el-form-item>
        <el-form-item label="教师职称" prop="teacherTitle">
          <el-input v-model="form.teacherTitle" placeholder="请输入教师职称" />
        </el-form-item>
        <el-form-item label="教师年龄" prop="age">
          <el-input v-model="form.age" placeholder="请输入教师年龄" />
        </el-form-item>
        <el-form-item label="教龄" prop="worktime">
          <el-input v-model="form.worktime" placeholder="请输入教龄" />
        </el-form-item>
        <el-form-item label="所授专业" prop="course">
          <el-input v-model="form.course" placeholder="请输入所授专业" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别">
            <el-option
              v-for="sex in sexOptions"
              :key="sex.dictValue"
              :value="sex.dictValue"
              :label="sex.dictLabel"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="联系方式" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="联系地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入联系地址" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="头像上传" prop="avatar">
          <el-upload
            class="avatar-uploader"
            :headers="upload.headers"
            :action="imgPath"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imgUrl" :src="imgUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTeacher, getTeacher, delTeacher, addTeacher, updateTeacher, exportTeacher } from "@/api/people/teacher";
import { treeselect } from "@/api/people/collage";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { getToken } from "@/utils/auth";
export default {
  name: "Teacher",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 教师信息表格数据
      teacherList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        collageId: null,
        teacherName: null,
        avatar: null,
        teacherTitle: null,
        age: null,
        worktime: null,
        course: null,
        sex: null,
        phone: null,
        address: null,
      },
      // 表单参数
      form: {

      },
      // 表单校验
      rules: {
      },
      sexOptions:[],
      deptOptions:[],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      deptName:'',
      // 教师导入参数
      upload: {
        // 是否显示弹出层（教师导入）
        open: false,
        // 弹出层标题（教师导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的教师数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/people/teacher/importData"
      },
      imgPath: process.env.VUE_APP_BASE_API+'/people/teacher/uploadAvatar',
      imgUrl:''
    };
  },
  watch:{
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {
    this.getList();
    this.getTreeselect();
    this.getDicts("sys_user_sex").then(response=>{
      this.sexOptions=response.data;
    })
  },
  methods: {
    handleAvatarSuccess(response, file) {
      if (response.data) {
        this.imgUrl =process.env.VUE_APP_BASE_API+ response.data;
      }else {
        //上传失败，使用默认头像
        this.imgUrl='http://localhost/dev-api/profile/upload/2021/04/07/629bafb4-44c4-48b1-9650-c4a701e4d689.jpg';
      }

    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    /** 查询教师信息列表 */
    getList() {
      this.loading = true;
      listTeacher(this.queryParams).then(response => {
        this.teacherList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        teacherId: null,
        collageId: null,
        teacherName: null,
        avatar: null,
        teacherTitle: null,
        age: null,
        worktime: null,
        course: null,
        sex: null,
        phone: null,
        address: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.imgUrl=undefined;
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.teacherId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加教师信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const teacherId = row.teacherId || this.ids
      getTeacher(teacherId).then(response => {
        this.form = response.data;
        this.imgUrl=response.data.avatar;
        this.open = true;
        this.title = "修改教师信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.teacherId != null) {
            if (this.imgUrl.startsWith('http:')) {
              this.form.avatar=this.imgUrl;
            }else {
              this.form.avatar='http://localhost'+this.imgUrl;
            }
            updateTeacher(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.avatar='http://localhost'+this.imgUrl;
            addTeacher(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const teacherIds = row.teacherId || this.ids;
      this.$confirm('是否确认删除教师信息编号为"' + teacherIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTeacher(teacherIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有教师信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTeacher(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    getTeacherSex(row, column){
      switch (row.sex) {
        case "0":
          return "男";
        case "1":
          return "女";
        case "2":
          return "未知";
      }
    },
    /** 查询学院下拉树结构 */
    getTreeselect() {
      treeselect().then(response => {
        this.deptOptions = response.data;
      });
    },
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.collageId = data.id;
      this.getList();
    },
  }
};
</script>
