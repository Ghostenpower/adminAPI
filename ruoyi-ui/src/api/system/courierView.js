import request from '@/utils/request'

// 查询跑腿员信息列表
export function listCouriersView(query) {
  return request({
    url: '/system/couriers/riderReviewList',
    method: 'post',
    data: {
      courier_id: query.courier_id,
      user_id: query.user_id,
      pageNum: query.pageNum || 1,
      pageSize: query.pageSize || 10
    }
  })
}

// /system/riderReviewList/accept通过，传入courierId(数字类型)
export function acceptRiderReview(courierId) {

  return request({
    url: '/system/couriers/riderReviewList/accept',
    method: 'post',
    data: {
      courierId: parseInt(courierId)
    }
  })
}

// /system/riderReviewList/reject拒绝，传入courierId(数字类型)
export function rejectRiderReview(courierId) {
  console.log(courierId)
  return request({
    url: '/system/couriers/riderReviewList/reject',
    method: 'post',
    data: {
      courierId: parseInt(courierId)
    }
  })
}