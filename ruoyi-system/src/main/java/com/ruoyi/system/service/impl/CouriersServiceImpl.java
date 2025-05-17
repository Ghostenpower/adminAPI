package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CouriersMapper;
import com.ruoyi.system.domain.Couriers;
import com.ruoyi.system.service.ICouriersService;

/**
 * 跑腿员信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
@Service
public class CouriersServiceImpl implements ICouriersService 
{
    @Autowired
    private CouriersMapper couriersMapper;

    /**
     * 查询跑腿员信息
     * 
     * @param courierId 跑腿员信息主键
     * @return 跑腿员信息
     */
    @Override
    public Couriers selectCouriersByCourierId(Long courierId)
    {
        return couriersMapper.selectCouriersByCourierId(courierId);
    }

    /**
     * 查询跑腿员信息列表
     * 
     * @param couriers 跑腿员信息
     * @return 跑腿员信息
     */
    @Override
    public List<Couriers> selectCouriersList(Couriers couriers)
    {
        return couriersMapper.selectCouriersList(couriers);
    }

    /**
     * 新增跑腿员信息
     * 
     * @param couriers 跑腿员信息
     * @return 结果
     */
    @Override
    public int insertCouriers(Couriers couriers)
    {
        return couriersMapper.insertCouriers(couriers);
    }

    /**
     * 修改跑腿员信息
     * 
     * @param couriers 跑腿员信息
     * @return 结果
     */
    @Override
    public int updateCouriers(Couriers couriers)
    {
        return couriersMapper.updateCouriers(couriers);
    }

    /**
     * 批量删除跑腿员信息
     * 
     * @param courierIds 需要删除的跑腿员信息主键
     * @return 结果
     */
    @Override
    public int deleteCouriersByCourierIds(Long[] courierIds)
    {
        return couriersMapper.deleteCouriersByCourierIds(courierIds);
    }

    /**
     * 删除跑腿员信息信息
     * 
     * @param courierId 跑腿员信息主键
     * @return 结果
     */
    @Override
    public int deleteCouriersByCourierId(Long courierId)
    {
        return couriersMapper.deleteCouriersByCourierId(courierId);
    }

    @Override
    public List<Couriers> riderReviewList(Couriers couriers) {
        return couriersMapper.riderReviewList(couriers);
    }

    @Override
    public int riderReviewListAccept(Couriers couriers) {
        return couriersMapper.riderReviewListAccept(couriers);
    }

    @Override
    public int riderReviewListReject(Couriers couriers) {
        return couriersMapper.riderReviewListReject(couriers);
    }
}
