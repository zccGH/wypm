<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="论文题目" prop="paperTitle">
        <el-input
          v-model="queryParams.paperTitle"
          placeholder="请输入论文题目"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
          style="width: 145px"
        />
      </el-form-item>
      <el-form-item label="完成时间" prop="paperFinished">
        <el-date-picker clearable size="small"
          v-model="queryParams.paperFinished"
          type="year"
          placeholder="选择完成时间"
          value-format="yyyy-MM-dd"
          style="width: 145px">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="论文状态" prop="paperStatus">
        <el-select style="width: 145px" v-model="queryParams.paperStatus" placeholder="请选择论文状态" clearable size="small">
          <el-option v-for="status in paperStatus" :label="status.dictLabel" :value="status.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="论文分类" prop="paperClassify">
        <el-select style="width: 145px" v-model="queryParams.paperClassify" placeholder="请选择论文类型" clearable size="small">
          <el-option v-for="classify in paperClassify" :label="classify.dictLabel" :value="classify.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="论文类型" prop="paperType">
        <el-select style="width: 145px" v-model="queryParams.paperType" placeholder="请选择论文类型" clearable size="small">
          <el-option v-for="type in paperType" :label="type.dictLabel" :value="type.dictValue" />
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
          v-hasPermi="['project:thesis:query']"
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
          v-hasPermi="['project:thesis:add']"
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
          v-hasPermi="['project:thesis:edit']"
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
          v-hasPermi="['project:thesis:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:thesis:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="thesisList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="论文题目" align="center" prop="paperTitle" />
      <el-table-column label="论文字数" align="center" prop="paperWord" />
      <el-table-column label="论文完成时间" align="center" prop="paperFinished" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.paperFinished, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="论文引用文献数量" align="center" prop="paperReference" />
      <el-table-column label="论文状态" align="center" prop="paperStatus" :formatter="getPaperStatus" />
      <el-table-column label="论文分类" align="center" prop="paperClassify" :formatter="getPaperClassify" />
      <el-table-column label="论文类型" align="center" prop="paperType" :formatter="getPaperType" />
      <el-table-column label="操作" align="center" width="250" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:thesis:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:thesis:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-upload"
            @click="handleUpload(scope.row)"
            v-hasPermi="['project:thesis:edit']"
          >上传论文</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-download"
            @click="handleDownload(scope.row)"
            v-hasPermi="['project:thesis:list']"
          >下载论文</el-button>
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

    <!-- 添加或修改论文相关信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-position="left" label-width="135px">
        <el-form-item label="论文题目" prop="paperTitle">
          <el-input v-model="form.paperTitle" placeholder="请输入论文题目" />
        </el-form-item>
        <el-form-item label="论文字数" prop="paperWord">
          <el-input type="number" v-model="form.paperWord" placeholder="请输入论文字数" />
        </el-form-item>
        <el-form-item label="完成时间" prop="paperFinished">
          <el-date-picker clearable size="small"
            v-model="form.paperFinished"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择论文完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="论文引用文献数量" prop="paperReference">
          <el-input type="number" v-model="form.paperReference" placeholder="请输入论文引用文献数量" />
        </el-form-item>
        <el-form-item label="论文状态" prop="paperStatus">
          <el-select v-model="form.paperStatus" placeholder="请选择论文状态">
            <el-option v-for="status in paperStatus" :label="status.dictLabel" :value="status.dictValue" :key="status.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="论文分类" prop="paperClassify">
          <el-select v-model="form.paperClassify" placeholder="请选择论文分类">
            <el-option v-for="classify in paperClassify" :label="classify.dictLabel" :value="classify.dictValue" :key="classify.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="论文类型" prop="paperType">
          <el-radio-group v-model="form.paperType">
            <el-radio v-for="type in paperType" :label="type.dictValue" >{{type.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--论文上传-->
    <el-dialog
      title="论文上传"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <el-upload
        class="upload-demo"
        ref="upload"
        drag
        :limit="1"
        :action="upload.uploadPath+'?id='+upload.id"
        :headers="upload.headers"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="true"
        :multiple="false"
        accept=".docx,.doc,.pdf">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传docx/dox/pdf文件</div>
      </el-upload>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleClose">取 消</el-button>
        <el-button type="primary" @click="handleSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { listThesis, getThesis, delThesis, addThesis, updateThesis, exportThesis,importThesisFile } from "@/api/project/thesis";
import { getToken } from "@/utils/auth"
export default {
  name: "Thesis",
  components: {
  },
  data() {
    return {
      //论文上传到服务器地址
      filePath:'',
      // 论文上参数
      upload: {
        // 是否禁用上传
        isUploading: false,
        // 论文记录id
        id: 0,
        // 设置上传的请求头部id
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        uploadPath:process.env.VUE_APP_BASE_API+"/project/thesis/uploadThesis"
      },
      dialogVisible: false,
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
      // 论文相关信息表格数据
      thesisList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        paperTitle: null,
        paperWord: null,
        paperFinished: null,
        paperReference: null,
        paperStatus: null,
        paperClassify: null,
        paperType: null,
      },
      // 表单参数
      form: {
      },
      downloadCount:0,
      // 表单校验
      rules: {
        paperTitle:{required:true,message: '请输入论文题目',trigger:'blur'},
        paperWord:{required:true,message: '请输入论文字数',trigger:'blur'},
        paperFinished:{required:true,message: '请选择论文完成时间',trigger:'blur'},
        paperReference:{required:true,message: '请输入论文引用数量',trigger:'blur'},
        paperStatus:{required:true,message: '请选则论文状态',trigger:'blur'},
        paperClassify:{required:true,message: '请选择论文分类',trigger:'blur'},
        paperType:{required:true,message: '请选择论文类型',trigger:'blur'}
      },
      paperStatus:[],
      paperClassify:[],
      paperType:[]
    };
  },
  created() {
    this.getList();
    this.getDicts('sys_thesis_paperStatus').then(response=>{
      this.paperStatus=response.data;
    })
    this.getDicts('sys_thesis_paperClassify').then(response=>{
      this.paperClassify=response.data;
    })
    this.getDicts('sys_thesis_paperType').then(response=>{
      this.paperType=response.data;
    })
  },
  methods: {
    //下载论文
    handleDownload(row){
      const id=row.id
      getThesis(id).then(response => {
        this.downloadCount=response.data.downloadCount;
        console.log(this.downloadCount)
        const filePath = response.data.paperPath;
        const fileName=response.data.paperTitle;
        if (!filePath) {
          this.msgInfo("尚未上传论文，请先上传！");
        }else{
          //实现论文网页下载
          let link = document.createElement('a'); //创建a标签
          link.style.display = 'none';  //将a标签隐藏
          link.href = filePath;  //给a标签添加下载链接
          link.setAttribute('download', fileName); // 要给a标签添加一个download属性，属性值就是文件名称 否则下载出来的文件是没有属性的
          document.body.appendChild(link);
          link.click(); //执行a标签
          //修改下载次数
          this.downloadCount=this.downloadCount+1;
          console.log(this.downloadCount)
          this.form.downloadCount=this.downloadCount;
          this.form.id=id;
          updateThesis(this.form).then(response => {
            this.getList();
          });
        }
      });
    },
    handleClose(){
      this.dialogVisible=false;
      this.$refs.upload.clearFiles();
    },
    //论文上传路径入库
    handleSubmit(){
      const form={};
      form.id=this.upload.id;
      form.paperPath=this.filePath;
      if (form.id != null) {
        updateThesis(form).then(response => {
          this.msgSuccess("修改成功");
          this.getList();
        });
      }
      this.$refs.upload.clearFiles();
      this.dialogVisible=false;
    },
    handleUpload(row){
      this.dialogVisible=true;
      this.upload.id=row.id;
    },
    /**处理论文上传*/
    handleFileUploadProgress(event, file, fileList){
      this.upload.isUploading = true;
    },
    //论文上传成功后续操作
    handleFileSuccess(response, file, fileList){
        this.upload.isUploading=false;
        this.filePath='http://localhost'+process.env.VUE_APP_BASE_API+response.data;
    },
    /** 查询论文相关信息列表 */
    getList() {
      this.loading = true;
      listThesis(this.queryParams).then(response => {
        this.thesisList = response.rows;
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
        id: null,
        paperTitle: null,
        paperWord: null,
        paperFinished: null,
        paperReference: null,
        paperStatus: null,
        paperClassify: null,
        paperType: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加论文相关信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getThesis(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改论文相关信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateThesis(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addThesis(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除论文相关信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delThesis(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有论文相关信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportThesis(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    getPaperType(row,column){
      switch (row.paperStatus) {
        case '1':
          return "初稿";
        case '2':
          return "定稿";
        case '3':
          return "发布/公开";
        case '4':
          return "私密";
      }
    },
    getPaperStatus(row,column){
      switch (row.paperType) {
        case '1':
          return "毕业论文";
        case '2':
          return "非毕业论文";
      }
    },
    getPaperClassify(row,column){
      switch (row.paperClassify) {
        case '1':
          return "工学";
        case '2':
          return "理学";
        case '3':
          return "农学";
        case '4':
          return "哲学";
        case '5':
          return "经济学";
        case '6':
          return "法学";
        case '7':
          return "教育学";
        case '8':
          return "文学";
        case '9':
          return "历史学";
        case '10':
          return "医学";
        case '11':
          return "军事学";
        case '12':
          return "管理学";
        case '13':
          return "艺术学";
        case '14':
          return "交叉学科";
      }
    }
  }
};
</script>
