<template>
    <div class="app-container">
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>发布公告</span>
                </div>

                <el-form-item label="公告标题" prop="title">
                    <el-input v-model="form.title" placeholder="请输入公告标题"></el-input>
                </el-form-item>

                <el-form-item label="公告内容" prop="content">
                    <el-input type="textarea" v-model="form.content" :rows="6" placeholder="请输入公告内容"></el-input>
                </el-form-item>

                <el-form-item label="公告类型" prop="announcementType">
                    <el-select v-model="form.announcementType" placeholder="请选择公告类型">
                        <el-option label="系统公告" :value="1"></el-option>
                        <el-option label="活动通知" :value="2"></el-option>
                        <el-option label="规则变更" :value="3"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="生效时间" prop="startTime">
                    <el-date-picker v-model="form.startTime" type="date" placeholder="选择生效时间" value-format="yyyy-MM-dd"
                        style="width: 100%">
                    </el-date-picker>
                </el-form-item>

                <el-form-item label="结束时间" prop="endTime">
                    <el-date-picker v-model="form.endTime" type="date" placeholder="选择结束时间" value-format="yyyy-MM-dd"
                        style="width: 100%">
                    </el-date-picker>
                </el-form-item>

                <el-form-item label="优先级" prop="priority">
                    <el-select v-model="form.priority" placeholder="请选择优先级">
                        <el-option label="普通" :value="0"></el-option>
                        <el-option label="重要" :value="1"></el-option>
                        <el-option label="紧急" :value="2"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="状态" prop="status">
                    <el-select v-model="form.status" placeholder="请选择状态">
                        <el-option label="草稿" :value="0"></el-option>
                        <el-option label="已发布" :value="1"></el-option>
                        <el-option label="已下线" :value="2"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="目标受众" prop="targetAudience">
                    <el-select v-model="form.targetAudience" placeholder="请选择目标受众">
                        <el-option label="全部" :value="0"></el-option>
                        <el-option label="用户" :value="1"></el-option>
                        <el-option label="跑腿员" :value="2"></el-option>
                        <el-option label="商家" :value="3"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="submitForm">发布</el-button>
                    <el-button @click="resetForm">重置</el-button>
                </el-form-item>
            </el-card>
        </el-form>
    </div>
</template>

<script>
import { addAnnouncements } from '@/api/system/announcements'

export default {
    name: 'Announce',
    data() {
        // 验证结束时间大于开始时间
        const validateEndTime = (rule, value, callback) => {
            if (value && this.form.startTime && value < this.form.startTime) {
                callback(new Error('结束时间必须大于开始时间'))
            } else {
                callback()
            }
        }
        return {
            // 表单参数
            form: {
                title: '',
                content: '',
                announcementType: undefined,
                startTime: '',
                endTime: '',
                priority: undefined,
                status: 0, // 默认为草稿状态
                targetAudience: undefined,
                adminId: 0  // 添加 adminId 字段，默认为 0
            },
            // 表单校验
            rules: {
                title: [
                    { required: true, message: '请输入公告标题', trigger: 'blur' },
                    { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
                ],
                content: [
                    { required: true, message: '请输入公告内容', trigger: 'blur' }
                ],
                announcementType: [
                    { required: true, message: '请选择公告类型', trigger: 'change' }
                ],
                startTime: [
                    { required: true, message: '请选择生效时间', trigger: 'change' }
                ],
                endTime: [
                    { required: true, message: '请选择结束时间', trigger: 'change' },
                    { validator: validateEndTime, trigger: 'change' }
                ],
                priority: [
                    { required: true, message: '请选择优先级', trigger: 'change' }
                ],
                status: [
                    { required: true, message: '请选择状态', trigger: 'change' }
                ],
                targetAudience: [
                    { required: true, message: '请选择目标受众', trigger: 'change' }
                ]
            }
        }
    },
    methods: {
        // 提交表单
        submitForm() {
            this.$refs['form'].validate(valid => {
                if (valid) {
                    this.$modal.loading('正在提交中...')
                    addAnnouncements(this.form).then(response => {
                        if (response.code === 200) {
                            this.$modal.msgSuccess('新增成功')
                            this.resetForm()
                        } else {
                            this.$modal.msgError(response.msg || '新增失败')
                        }
                    }).catch(() => {
                        this.$modal.msgError('系统错误，请稍后重试')
                    }).finally(() => {
                        this.$modal.closeLoading()
                    })
                }
            })
        },
        // 重置表单
        resetForm() {
            this.$refs['form'].resetFields()
        }
    }
}
</script>

<style>
.app-container {
    padding: 20px;
}

.box-card {
    width: 100%;
    max-width: 800px;
    margin: 0 auto;
}

.el-form-item {
    margin-bottom: 25px;
}

.el-select {
    width: 100%;
}

.el-textarea__inner {
    font-family: Arial, sans-serif;
}

/* 调整卡片标题样式 */
.box-card .el-card__header {
    padding: 15px 20px;
    border-bottom: 1px solid #ebeef5;
    background-color: #f5f7fa;
}

.clearfix {
    font-size: 18px;
    font-weight: bold;
    color: #303133;
}
</style>
