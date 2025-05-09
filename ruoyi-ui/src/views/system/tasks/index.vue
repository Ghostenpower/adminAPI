<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="发布用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入发布用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入任务标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="取件地点" prop="pickupLocation">
        <el-input
          v-model="queryParams.pickupLocation"
          placeholder="请输入取件地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="送达地点" prop="deliveryLocation">
        <el-input
          v-model="queryParams.deliveryLocation"
          placeholder="请输入送达地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务截止时间" prop="deadline">
        <el-date-picker clearable
          v-model="queryParams.deadline"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择任务截止时间">
        </el-date-picker>
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
      <el-form-item label="取件坐标" prop="pickupCoordinates">
        <el-input
          v-model="queryParams.pickupCoordinates"
          placeholder="请输入取件坐标"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="送达坐标" prop="deliveryCoordinates">
        <el-input
          v-model="queryParams.deliveryCoordinates"
          placeholder="请输入送达坐标"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务金额" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入任务金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:tasks:add']"
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
          v-hasPermi="['system:tasks:edit']"
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
          v-hasPermi="['system:tasks:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:tasks:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tasksList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务ID" align="center" prop="taskId" />
      <el-table-column label="发布用户ID" align="center" prop="userId" />
      <el-table-column label="任务类型(1=代取快递,2=事务代办,3=物品代购,4=文件打印,5=其他)" align="center" prop="taskType" />
      <el-table-column label="任务标题" align="center" prop="title" />
      <el-table-column label="任务详细描述" align="center" prop="description" />
      <el-table-column label="取件地点" align="center" prop="pickupLocation" />
      <el-table-column label="送达地点" align="center" prop="deliveryLocation" />
      <el-table-column label="任务截止时间" align="center" prop="deadline" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deadline, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态(0=待接单,1=已接单,2=进行中,3=已完成,4=已取消)" align="center" prop="status" />
      <el-table-column label="支付状态(0=未支付,1=已支付,2=已退款)" align="center" prop="paymentStatus" />
      <el-table-column label="备注信息" align="center" prop="remark" />
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
      <el-table-column label="图片列表url(逗号分隔)" align="center" prop="imagesUrl" />
      <el-table-column label="取件坐标" align="center" prop="pickupCoordinates" />
      <el-table-column label="送达坐标" align="center" prop="deliveryCoordinates" />
      <el-table-column label="任务金额" align="center" prop="price" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:tasks:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:tasks:remove']"
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

    <!-- 添加或修改跑腿任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="发布用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入发布用户ID" />
        </el-form-item>
        <el-form-item label="任务标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入任务标题" />
        </el-form-item>
        <el-form-item label="任务详细描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="取件地点" prop="pickupLocation">
          <el-input v-model="form.pickupLocation" placeholder="请输入取件地点" />
        </el-form-item>
        <el-form-item label="送达地点" prop="deliveryLocation">
          <el-input v-model="form.deliveryLocation" placeholder="请输入送达地点" />
        </el-form-item>
        <el-form-item label="任务截止时间" prop="deadline">
          <el-date-picker clearable
            v-model="form.deadline"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择任务截止时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注信息" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注信息" />
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
        <el-form-item label="图片列表url(逗号分隔)" prop="imagesUrl">
          <el-input v-model="form.imagesUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="取件坐标" prop="pickupCoordinates">
          <el-input v-model="form.pickupCoordinates" placeholder="请输入取件坐标" />
        </el-form-item>
        <el-form-item label="送达坐标" prop="deliveryCoordinates">
          <el-input v-model="form.deliveryCoordinates" placeholder="请输入送达坐标" />
        </el-form-item>
        <el-form-item label="任务金额" prop="price">
          <el-input v-model="form.price" placeholder="请输入任务金额" />
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
import { listTasks, getTasks, delTasks, addTasks, updateTasks } from "@/api/system/tasks"

export default {
  name: "Tasks",
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
      // 跑腿任务表格数据
      tasksList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        taskType: null,
        title: null,
        description: null,
        pickupLocation: null,
        deliveryLocation: null,
        deadline: null,
        status: null,
        paymentStatus: null,
        createdAt: null,
        updatedAt: null,
        imagesUrl: null,
        pickupCoordinates: null,
        deliveryCoordinates: null,
        price: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "发布用户ID不能为空", trigger: "blur" }
        ],
        taskType: [
          { required: true, message: "任务类型(1=代取快递,2=事务代办,3=物品代购,4=文件打印,5=其他)不能为空", trigger: "change" }
        ],
        title: [
          { required: true, message: "任务标题不能为空", trigger: "blur" }
        ],
        description: [
          { required: true, message: "任务详细描述不能为空", trigger: "blur" }
        ],
        pickupLocation: [
          { required: true, message: "取件地点不能为空", trigger: "blur" }
        ],
        deliveryLocation: [
          { required: true, message: "送达地点不能为空", trigger: "blur" }
        ],
        deadline: [
          { required: true, message: "任务截止时间不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态(0=待接单,1=已接单,2=进行中,3=已完成,4=已取消)不能为空", trigger: "change" }
        ],
        paymentStatus: [
          { required: true, message: "支付状态(0=未支付,1=已支付,2=已退款)不能为空", trigger: "change" }
        ],
        remark: [
          { required: true, message: "备注信息不能为空", trigger: "blur" }
        ],
        createdAt: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updatedAt: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
        imagesUrl: [
          { required: true, message: "图片列表url(逗号分隔)不能为空", trigger: "blur" }
        ],
        pickupCoordinates: [
          { required: true, message: "取件坐标不能为空", trigger: "blur" }
        ],
        deliveryCoordinates: [
          { required: true, message: "送达坐标不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "任务金额不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询跑腿任务列表 */
    getList() {
      this.loading = true
      listTasks(this.queryParams).then(response => {
        this.tasksList = response.rows
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
        taskId: null,
        userId: null,
        taskType: null,
        title: null,
        description: null,
        pickupLocation: null,
        deliveryLocation: null,
        deadline: null,
        status: null,
        paymentStatus: null,
        remark: null,
        createdAt: null,
        updatedAt: null,
        imagesUrl: null,
        pickupCoordinates: null,
        deliveryCoordinates: null,
        price: null
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
      this.ids = selection.map(item => item.taskId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加跑腿任务"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const taskId = row.taskId || this.ids
      getTasks(taskId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改跑腿任务"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.taskId != null) {
            updateTasks(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addTasks(this.form).then(response => {
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
      const taskIds = row.taskId || this.ids
      this.$modal.confirm('是否确认删除跑腿任务编号为"' + taskIds + '"的数据项？').then(function() {
        return delTasks(taskIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/tasks/export', {
        ...this.queryParams
      }, `tasks_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
