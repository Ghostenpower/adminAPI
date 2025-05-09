import request from '@/utils/request'

// 查询地址信息列表
export function listAddresses(query) {
  return request({
    url: '/system/addresses/list',
    method: 'get',
    params: query
  })
}

// 查询地址信息详细
export function getAddresses(addressId) {
  return request({
    url: '/system/addresses/' + addressId,
    method: 'get'
  })
}

// 新增地址信息
export function addAddresses(data) {
  return request({
    url: '/system/addresses',
    method: 'post',
    data: data
  })
}

// 修改地址信息
export function updateAddresses(data) {
  return request({
    url: '/system/addresses',
    method: 'put',
    data: data
  })
}

// 删除地址信息
export function delAddresses(addressId) {
  return request({
    url: '/system/addresses/' + addressId,
    method: 'delete'
  })
}
