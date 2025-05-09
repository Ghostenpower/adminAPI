import request from '@/utils/request'

// 查询商家信息列表
export function listMerchants(query) {
  return request({
    url: '/system/merchants/list',
    method: 'get',
    params: query
  })
}

// 查询商家信息详细
export function getMerchants(merchantId) {
  return request({
    url: '/system/merchants/' + merchantId,
    method: 'get'
  })
}

// 新增商家信息
export function addMerchants(data) {
  return request({
    url: '/system/merchants',
    method: 'post',
    data: data
  })
}

// 修改商家信息
export function updateMerchants(data) {
  return request({
    url: '/system/merchants',
    method: 'put',
    data: data
  })
}

// 删除商家信息
export function delMerchants(merchantId) {
  return request({
    url: '/system/merchants/' + merchantId,
    method: 'delete'
  })
}
