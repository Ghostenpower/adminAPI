package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Addresses;

/**
 * 地址信息Service接口
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public interface IAddressesService 
{
    /**
     * 查询地址信息
     * 
     * @param addressId 地址信息主键
     * @return 地址信息
     */
    public Addresses selectAddressesByAddressId(Long addressId);

    /**
     * 查询地址信息列表
     * 
     * @param addresses 地址信息
     * @return 地址信息集合
     */
    public List<Addresses> selectAddressesList(Addresses addresses);

    /**
     * 新增地址信息
     * 
     * @param addresses 地址信息
     * @return 结果
     */
    public int insertAddresses(Addresses addresses);

    /**
     * 修改地址信息
     * 
     * @param addresses 地址信息
     * @return 结果
     */
    public int updateAddresses(Addresses addresses);

    /**
     * 批量删除地址信息
     * 
     * @param addressIds 需要删除的地址信息主键集合
     * @return 结果
     */
    public int deleteAddressesByAddressIds(Long[] addressIds);

    /**
     * 删除地址信息信息
     * 
     * @param addressId 地址信息主键
     * @return 结果
     */
    public int deleteAddressesByAddressId(Long addressId);
}
