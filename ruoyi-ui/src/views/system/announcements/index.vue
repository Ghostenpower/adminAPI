<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公告标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入公告标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="管理员ID" prop="adminId">
        <el-input
          v-model="queryParams.adminId"
          placeholder="请输入管理员ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生效时间" prop="startTime">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择生效时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="失效时间" prop="endTime">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择失效时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="优先级(0=普通,1=重要,2=紧急)" prop="priority">
        <el-input
          v-model="queryParams.priority"
          placeholder="请输入优先级(0=普通,1=重要,2=紧急)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目标受众(0=全部,1=用户,2=跑腿员,3=商家)" prop="targetAudience">
        <el-input
          v-model="queryParams.targetAudience"
          placeholder="请输入目标受众(0=全部,1=用户,2=跑腿员,3=商家)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdAt">
        <el-date-picker clearable
          v-model="queryParams.createdAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updatedAt">
        <el-date-picker clearable
          v-model="queryParams.updatedAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:announcements:add']"
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
          v-hasPermi="['system:announcements:edit']"
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
          v-hasPermi="['system:announcements:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:announcements:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="announcementsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="公告ID" align="center" prop="announcementId" />
      <el-table-column label="公告标题" align="center" prop="title" />
      <el-table-column label="公告内容" align="center" prop="content" />
      <el-table-column label="管理员ID" align="center" prop="adminId" />
      <el-table-column label="公告类型(1=系统公告,2=活动通知,3=规则变更)" align="center" prop="announcementType" />
      <el-table-column label="生效时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="失效时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="优先级(0=普通,1=重要,2=紧急)" align="center" prop="priority" />
      <el-table-column label="状态(0=草稿,1=已发布,2=已下线)" align="center" prop="status" />
      <el-table-column label="目标受众(0=全部,1=用户,2=跑腿员,3=商家)" align="center" prop="targetAudience" />
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:announcements:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:announcements:remove']"
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

    <!-- 添加或修改系统公告(仅管理员可发)对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公告标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入公告标题" />
        </el-form-item>
        <el-form-item label="公告内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="管理员ID" prop="adminId">
          <el-input v-model="form.adminId" placeholder="请输入管理员ID" />
        </el-form-item>
        <el-form-item label="生效时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择生效时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="失效时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择失效时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="优先级(0=普通,1=重要,2=紧急)" prop="priority">
          <el-input v-model="form.priority" placeholder="请输入优先级(0=普通,1=重要,2=紧急)" />
        </el-form-item>
        <el-form-item label="目标受众(0=全部,1=用户,2=跑腿员,3=商家)" prop="targetAudience">
          <el-input v-model="form.targetAudience" placeholder="请输入目标受众(0=全部,1=用户,2=跑腿员,3=商家)" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdAt">
          <el-date-picker clearable
            v-model="form.createdAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedAt">
          <el-date-picker clearable
            v-model="form.updatedAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
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
import { listAnnouncements, getAnnouncements, delAnnouncements, addAnnouncements, updateAnnouncements } from "@/api/system/announcements"

export default {
  name: "Announcements",
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
      // 系统公告(仅管理员可发)表格数据
      announcementsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        content: null,
        adminId: null,
        announcementType: null,
        startTime: null,
        endTime: null,
        priority: null,
        status: null,
        targetAudience: null,
        createdAt: null,
        updatedAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "公告标题不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "公告内容不能为空", trigger: "blur" }
        ],
        adminId: [
          { required: true, message: "管理员ID不能为空", trigger: "blur" }
        ],
        announcementType: [
          { required: true, message: "公告类型(1=系统公告,2=活动通知,3=规则变更)不能为空", trigger: "change" }
        ],
        startTime: [
          { required: true, message: "生效时间不能为空", trigger: "blur" }
        ],
        endTime: [
          { required: true, message: "失效时间不能为空", trigger: "blur" }
        ],
        priority: [
          { required: true, message: "优先级(0=普通,1=重要,2=紧急)不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态(0=草稿,1=已发布,2=已下线)不能为空", trigger: "change" }
        ],
        targetAudience: [
          { required: true, message: "目标受众(0=全部,1=用户,2=跑腿员,3=商家)不能为空", trigger: "blur" }
        ],
        createdAt: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updatedAt: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询系统公告(仅管理员可发)列表 */
    getList() {
      this.loading = true
      listAnnouncements(this.queryParams).then(response => {
        this.announcementsList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        announcementId: null,
        title: null,
        content: null,
        adminId: null,
        announcementType: null,
        startTime: null,
        endTime: null,
        priority: null,
        status: null,
        targetAudience: null,
        createdAt: null,
        updatedAt: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.announcementId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加系统公告(仅管理员可发)"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const announcementId = row.announcementId || this.ids
      getAnnouncements(announcementId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改系统公告(仅管理员可发)"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.announcementId != null) {
            updateAnnouncements(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addAnnouncements(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const announcementIds = row.announcementId || this.ids
      this.$modal.confirm('是否确认删除系统公告(仅管理员可发)编号为"' + announcementIds + '"的数据项？').then(function() {
        return delAnnouncements(announcementIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/announcements/export', {
        ...this.queryParams
      }, `announcements_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
