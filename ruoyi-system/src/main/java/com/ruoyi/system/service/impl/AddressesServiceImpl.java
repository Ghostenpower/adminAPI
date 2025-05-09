package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AddressesMapper;
import com.ruoyi.system.domain.Addresses;
import com.ruoyi.system.service.IAddressesService;

/**
 * 地址信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
@Service
public class AddressesServiceImpl implements IAddressesService 
{
    @Autowired
    private AddressesMapper addressesMapper;

    /**
     * 查询地址信息
     * 
     * @param addressId 地址信息主键
     * @return 地址信息
     */
    @Override
    public Addresses selectAddressesByAddressId(Long addressId)
    {
        return addressesMapper.selectAddressesByAddressId(addressId);
    }

    /**
     * 查询地址信息列表
     * 
     * @param addresses 地址信息
     * @return 地址信息
     */
    @Override
    public List<Addresses> selectAddressesList(Addresses addresses)
    {
        return addressesMapper.selectAddressesList(addresses);
    }

    /**
     * 新增地址信息
     * 
     * @param addresses 地址信息
     * @return 结果
     */
    @Override
    public int insertAddresses(Addresses addresses)
    {
        return addressesMapper.insertAddresses(addresses);
    }

    /**
     * 修改地址信息
     * 
     * @param addresses 地址信息
     * @return 结果
     */
    @Override
    public int updateAddresses(Addresses addresses)
    {
        return addressesMapper.updateAddresses(addresses);
    }

    /**
     * 批量删除地址信息
     * 
     * @param addressIds 需要删除的地址信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressesByAddressIds(Long[] addressIds)
    {
        return addressesMapper.deleteAddressesByAddressIds(addressIds);
    }

    /**
     * 删除地址信息信息
     * 
     * @param addressId 地址信息主键
     * @return 结果
     */
    @Override
    public int deleteAddressesByAddressId(Long addressId)
    {
        return addressesMapper.deleteAddressesByAddressId(addressId);
    }
}
