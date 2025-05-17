<template>
    <div class="app-container">
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
            <div class="flex-container">
                <el-form-item label="跑腿员ID" prop="courier_id">
                    <el-input v-model="formData.courier_id" placeholder="请输入跑腿员ID" show-word-limit clearable
                        :style="{ width: '200px' }"></el-input>
                </el-form-item>
                <el-form-item label="用户ID" prop="user_id">
                    <el-input v-model="formData.user_id" placeholder="请输入用户ID" clearable :style="{ width: '200px' }">
                    </el-input>
                </el-form-item>
                <el-form-item size="large" class="button-item">
                    <el-button type="primary" @click="handleQuery">查找</el-button>
                    <el-button @click="resetForm">重置</el-button>
                </el-form-item>
            </div>
        </el-form>

        <!-- 表格容器 -->
        <div class="table-container">
            <!-- 结果展示区域 -->
            <el-table 
                :data="tableData" 
                style="width: 100%;"
                v-loading="loading">
                <el-table-column prop="courierId" label="跑腿员ID" min-width="120"></el-table-column>
                <el-table-column prop="userId" label="用户ID" min-width="120"></el-table-column>
                <el-table-column label="身份证正面" min-width="200">
                    <template slot-scope="scope">
                        <el-image 
                            v-if="scope.row.idCardFront"
                            :src="scope.row.idCardFront" 
                            :preview-src-list="[scope.row.idCardFront]"
                            fit="contain"
                            class="preview-image">
                        </el-image>
                        <span v-else>无图片</span>
                    </template>
                </el-table-column>
                <el-table-column label="身份证反面" min-width="200">
                    <template slot-scope="scope">
                        <el-image 
                            v-if="scope.row.idCardBack"
                            :src="scope.row.idCardBack" 
                            :preview-src-list="[scope.row.idCardBack]"
                            fit="contain"
                            class="preview-image">
                        </el-image>
                        <span v-else>无图片</span>
                    </template>
                </el-table-column>
                <el-table-column label="学生证" min-width="200">
                    <template slot-scope="scope">
                        <el-image 
                            v-if="scope.row.studentCard"
                            :src="scope.row.studentCard" 
                            :preview-src-list="[scope.row.studentCard]"
                            fit="contain"
                            class="preview-image">
                        </el-image>
                        <span v-else>无图片</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作" min-width="160" fixed="right">
                    <template slot-scope="scope">
                        <el-button
                            size="mini"
                            type="success"
                            @click="handleApprove(scope.row)">通过</el-button>
                        <el-button
                            size="mini"
                            type="danger"
                            @click="handleReject(scope.row)">不通过</el-button>
                    </template>
                </el-table-column>
                <template slot="empty">
                    <div class="empty-block">
                        <span class="empty-text">暂无数据</span>
                    </div>
                </template>
            </el-table>

            <!-- 分页组件 -->
            <el-pagination
                v-if="total > 0"
                :current-page="queryParams.pageNum"
                :page-sizes="[10, 20, 50, 100]"
                :page-size="queryParams.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                style="margin-top: 20px; display: flex; justify-content: flex-end;">
            </el-pagination>
        </div>
    </div>
</template>
<script>
import { listCouriersView, acceptRiderReview, rejectRiderReview } from '@/api/system/courierView'

export default {
    components: {},
    props: [],
    data() {
        return {
            formData: {
                courier_id: '',
                user_id: undefined,
            },
            rules: {
                courier_id: [],
                user_id: []
            },
            tableData: [],
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                courier_id: undefined,
                user_id: undefined
            },
            // 总条数
            total: 0,
            loading: false
        }
    },
    computed: {},
    watch: {},
    created() { },
    mounted() {
        this.getList()
    },
    methods: {
        // 查询列表
        getList() {
            this.loading = true
            listCouriersView(this.queryParams)
                .then(response => {
                    if (response.code === 200) {
                        this.tableData = response.rows || []
                        this.total = response.total
                        this.$message.success('查询成功')
                    } else {
                        this.$message.error(response.msg || '查询失败')
                    }
                })
                .catch(error => {
                    console.error('查询出错:', error)
                    this.$message.error('查询出错，请稍后重试')
                })
                .finally(() => {
                    this.loading = false
                })
        },
        // 搜索按钮操作
        handleQuery() {
            this.queryParams.pageNum = 1
            this.queryParams.courier_id = parseInt(this.formData.courier_id) || undefined
            this.queryParams.user_id = parseInt(this.formData.user_id) || undefined
            this.getList()
        },
        // 重置按钮操作
        resetForm() {
            this.$refs['elForm'].resetFields()
            this.formData = {
                courier_id: '',
                user_id: undefined
            }
            this.queryParams = {
                pageNum: 1,
                pageSize: 10,
                courier_id: undefined,
                user_id: undefined
            }
            this.getList()
        },
        // 每页条数改变
        handleSizeChange(val) {
            this.queryParams.pageSize = val
            this.getList()
        },
        // 当前页改变
        handleCurrentChange(val) {
            this.queryParams.pageNum = val
            this.getList()
        },
        // 通过操作
        handleApprove(row) {
            this.$confirm('确认通过该跑腿员的审核?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                return acceptRiderReview(row.courierId)
            }).then(response => {
                if (response.code === 200) {
                    this.$message.success('审核通过成功')
                    // 重新获取列表数据
                    return this.getList()
                } else {
                    this.$message.error(response.msg || '操作失败')
                    return Promise.reject(new Error(response.msg || '操作失败'))
                }
            }).catch(error => {
                if (error.message !== '操作失败') {
                    console.error('操作出错:', error)
                    this.$message.error('操作失败，请稍后重试')
                }
            })
        },
        // 不通过操作
        handleReject(row) {
            this.$confirm('确认拒绝该跑腿员的审核?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                return rejectRiderReview(row.courierId)
            }).then(response => {
                if (response.code === 200) {
                    this.$message.success('审核拒绝成功')
                    // 重新获取列表数据
                    return this.getList()
                } else {
                    this.$message.error(response.msg || '操作失败')
                    return Promise.reject(new Error(response.msg || '操作失败'))
                }
            }).catch(error => {
                if (error.message !== '操作失败') {
                    console.error('操作出错:', error)
                    this.$message.error('操作失败，请稍后重试')
                }
            })
        }
    }
}
</script>
<style>
.app-container {
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.table-container {
    width: 100%;
    max-width: 1200px;
    margin: 0 auto;
    overflow-x: auto;
}

.el-form {
    width: 100%;
    max-width: 1200px;
    margin: 0 auto 20px;
}

.flex-container {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    gap: 20px;
}

.button-item {
    margin-bottom: 0;
    margin-left: 20px;
}

.button-item .el-form-item__content {
    margin-left: 0 !important;
}

.el-form-item {
    margin-bottom: 0;
}

/* 图片预览样式 */
.preview-image {
    width: 120px;
    height: 80px;
    border-radius: 4px;
    cursor: pointer;
    background-color: #f5f7fa;
}

.el-image {
    border-radius: 4px;
    cursor: pointer;
}

.empty-block {
    min-height: 60px;
    text-align: center;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.empty-text {
    color: #909399;
    font-size: 14px;
}

.el-table {
    min-height: 200px;
    background-color: #fff;
    border-radius: 4px;
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
    table-layout: fixed;
}

/* 调整操作列按钮的布局 */
.el-table .el-button + .el-button {
    margin-left: 8px;
}
</style>