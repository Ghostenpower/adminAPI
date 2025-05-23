import request from '@/utils/request'

// 查询系统公告(仅管理员可发)列表
export function listAnnouncements(query) {
  return request({
    url: '/system/announcements/list',
    method: 'get',
    params: query
  })
}

// 查询系统公告(仅管理员可发)详细
export function getAnnouncements(announcementId) {
  return request({
    url: '/system/announcements/' + announcementId,
    method: 'get'
  })
}

// 新增系统公告(仅管理员可发)
export function addAnnouncements(data) {
  return request({
    url: '/system/announcements',
    method: 'post',
    data: data
  })
}

// 修改系统公告(仅管理员可发)
export function updateAnnouncements(data) {
  return request({
    url: '/system/announcements',
    method: 'put',
    data: data
  })
}

// 删除系统公告(仅管理员可发)
export function delAnnouncements(announcementId) {
  return request({
    url: '/system/announcements/' + announcementId,
    method: 'delete'
  })
}
