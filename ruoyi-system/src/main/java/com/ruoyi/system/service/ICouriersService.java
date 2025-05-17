package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Couriers;

/**
 * 跑腿员信息Service接口
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public interface ICouriersService 
{
    /**
     * 查询跑腿员信息
     * 
     * @param courierId 跑腿员信息主键
     * @return 跑腿员信息
     */
    public Couriers selectCouriersByCourierId(Long courierId);

    /**
     * 查询跑腿员信息列表
     * 
     * @param couriers 跑腿员信息
     * @return 跑腿员信息集合
     */
    public List<Couriers> selectCouriersList(Couriers couriers);

    /**
     * 新增跑腿员信息
     * 
     * @param couriers 跑腿员信息
     * @return 结果
     */
    public int insertCouriers(Couriers couriers);

    /**
     * 修改跑腿员信息
     * 
     * @param couriers 跑腿员信息
     * @return 结果
     */
    public int updateCouriers(Couriers couriers);

    /**
     * 批量删除跑腿员信息
     * 
     * @param courierIds 需要删除的跑腿员信息主键集合
     * @return 结果
     */
    public int deleteCouriersByCourierIds(Long[] courierIds);

    /**
     * 删除跑腿员信息信息
     * 
     * @param courierId 跑腿员信息主键
     * @return 结果
     */
    public int deleteCouriersByCourierId(Long courierId);

    List<Couriers> riderReviewList(Couriers couriers);

    int riderReviewListAccept(Couriers couriers);

    int riderReviewListReject(Couriers couriers);
}
