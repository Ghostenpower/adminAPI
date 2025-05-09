import request from '@/utils/request'

// 查询跑腿任务列表
export function listTasks(query) {
  return request({
    url: '/system/tasks/list',
    method: 'get',
    params: query
  })
}

// 查询跑腿任务详细
export function getTasks(taskId) {
  return request({
    url: '/system/tasks/' + taskId,
    method: 'get'
  })
}

// 新增跑腿任务
export function addTasks(data) {
  return request({
    url: '/system/tasks',
    method: 'post',
    data: data
  })
}

// 修改跑腿任务
export function updateTasks(data) {
  return request({
    url: '/system/tasks',
    method: 'put',
    data: data
  })
}

// 删除跑腿任务
export function delTasks(taskId) {
  return request({
    url: '/system/tasks/' + taskId,
    method: 'delete'
  })
}
