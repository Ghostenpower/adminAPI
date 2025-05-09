import request from '@/utils/request'

// 查询跑腿员信息列表
export function listCouriers(query) {
  return request({
    url: '/system/couriers/list',
    method: 'get',
    params: query
  })
}

// 查询跑腿员信息详细
export function getCouriers(courierId) {
  return request({
    url: '/system/couriers/' + courierId,
    method: 'get'
  })
}

// 新增跑腿员信息
export function addCouriers(data) {
  return request({
    url: '/system/couriers',
    method: 'post',
    data: data
  })
}

// 修改跑腿员信息
export function updateCouriers(data) {
  return request({
    url: '/system/couriers',
    method: 'put',
    data: data
  })
}

// 删除跑腿员信息
export function delCouriers(courierId) {
  return request({
    url: '/system/couriers/' + courierId,
    method: 'delete'
  })
}
