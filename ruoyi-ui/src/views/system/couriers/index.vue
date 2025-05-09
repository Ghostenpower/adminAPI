<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="关联用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入关联用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="idCard">
        <el-input
          v-model="queryParams.idCard"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证正面照片URL" prop="idCardFront">
        <el-input
          v-model="queryParams.idCardFront"
          placeholder="请输入身份证正面照片URL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证反面照片URL" prop="idCardBack">
        <el-input
          v-model="queryParams.idCardBack"
          placeholder="请输入身份证反面照片URL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生证照片URL" prop="studentCard">
        <el-input
          v-model="queryParams.studentCard"
          placeholder="请输入学生证照片URL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="信用分(0-100)" prop="creditScore">
        <el-input
          v-model="queryParams.creditScore"
          placeholder="请输入信用分(0-100)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="完成订单总数" prop="totalOrders">
        <el-input
          v-model="queryParams.totalOrders"
          placeholder="请输入完成订单总数"
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
          v-hasPermi="['system:couriers:add']"
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
          v-hasPermi="['system:couriers:edit']"
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
          v-hasPermi="['system:couriers:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:couriers:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="couriersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="跑腿员ID" align="center" prop="courierId" />
      <el-table-column label="关联用户ID" align="center" prop="userId" />
      <el-table-column label="身份证号" align="center" prop="idCard" />
      <el-table-column label="身份证正面照片URL" align="center" prop="idCardFront" />
      <el-table-column label="身份证反面照片URL" align="center" prop="idCardBack" />
      <el-table-column label="学生证照片URL" align="center" prop="studentCard" />
      <el-table-column label="信用分(0-100)" align="center" prop="creditScore" />
      <el-table-column label="完成订单总数" align="center" prop="totalOrders" />
      <el-table-column label="状态(0待审核,1已通过,2已拒绝)" align="center" prop="status" />
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
            v-hasPermi="['system:couriers:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:couriers:remove']"
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

    <!-- 添加或修改跑腿员信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入关联用户ID" />
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="form.idCard" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="身份证正面照片URL" prop="idCardFront">
          <el-input v-model="form.idCardFront" placeholder="请输入身份证正面照片URL" />
        </el-form-item>
        <el-form-item label="身份证反面照片URL" prop="idCardBack">
          <el-input v-model="form.idCardBack" placeholder="请输入身份证反面照片URL" />
        </el-form-item>
        <el-form-item label="学生证照片URL" prop="studentCard">
          <el-input v-model="form.studentCard" placeholder="请输入学生证照片URL" />
        </el-form-item>
        <el-form-item label="信用分(0-100)" prop="creditScore">
          <el-input v-model="form.creditScore" placeholder="请输入信用分(0-100)" />
        </el-form-item>
        <el-form-item label="完成订单总数" prop="totalOrders">
          <el-input v-model="form.totalOrders" placeholder="请输入完成订单总数" />
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
import { listCouriers, getCouriers, delCouriers, addCouriers, updateCouriers } from "@/api/system/couriers"

export default {
  name: "Couriers",
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
      // 跑腿员信息表格数据
      couriersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        idCard: null,
        idCardFront: null,
        idCardBack: null,
        studentCard: null,
        creditScore: null,
        totalOrders: null,
        status: null,
        createdAt: null,
        updatedAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "关联用户ID不能为空", trigger: "blur" }
        ],
        idCard: [
          { required: true, message: "身份证号不能为空", trigger: "blur" }
        ],
        creditScore: [
          { required: true, message: "信用分(0-100)不能为空", trigger: "blur" }
        ],
        totalOrders: [
          { required: true, message: "完成订单总数不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态(0待审核,1已通过,2已拒绝)不能为空", trigger: "change" }
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
    /** 查询跑腿员信息列表 */
    getList() {
      this.loading = true
      listCouriers(this.queryParams).then(response => {
        this.couriersList = response.rows
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
        courierId: null,
        userId: null,
        idCard: null,
        idCardFront: null,
        idCardBack: null,
        studentCard: null,
        creditScore: null,
        totalOrders: null,
        status: null,
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
      this.ids = selection.map(item => item.courierId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加跑腿员信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const courierId = row.courierId || this.ids
      getCouriers(courierId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改跑腿员信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.courierId != null) {
            updateCouriers(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addCouriers(this.form).then(response => {
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
      const courierIds = row.courierId || this.ids
      this.$modal.confirm('是否确认删除跑腿员信息编号为"' + courierIds + '"的数据项？').then(function() {
        return delCouriers(courierIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/couriers/export', {
        ...this.queryParams
      }, `couriers_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
