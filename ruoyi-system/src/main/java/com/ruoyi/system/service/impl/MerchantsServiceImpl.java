package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MerchantsMapper;
import com.ruoyi.system.domain.Merchants;
import com.ruoyi.system.service.IMerchantsService;

/**
 * 商家信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
@Service
public class MerchantsServiceImpl implements IMerchantsService 
{
    @Autowired
    private MerchantsMapper merchantsMapper;

    /**
     * 查询商家信息
     * 
     * @param merchantId 商家信息主键
     * @return 商家信息
     */
    @Override
    public Merchants selectMerchantsByMerchantId(Long merchantId)
    {
        return merchantsMapper.selectMerchantsByMerchantId(merchantId);
    }

    /**
     * 查询商家信息列表
     * 
     * @param merchants 商家信息
     * @return 商家信息
     */
    @Override
    public List<Merchants> selectMerchantsList(Merchants merchants)
    {
        return merchantsMapper.selectMerchantsList(merchants);
    }

    /**
     * 新增商家信息
     * 
     * @param merchants 商家信息
     * @return 结果
     */
    @Override
    public int insertMerchants(Merchants merchants)
    {
        return merchantsMapper.insertMerchants(merchants);
    }

    /**
     * 修改商家信息
     * 
     * @param merchants 商家信息
     * @return 结果
     */
    @Override
    public int updateMerchants(Merchants merchants)
    {
        return merchantsMapper.updateMerchants(merchants);
    }

    /**
     * 批量删除商家信息
     * 
     * @param merchantIds 需要删除的商家信息主键
     * @return 结果
     */
    @Override
    public int deleteMerchantsByMerchantIds(Long[] merchantIds)
    {
        return merchantsMapper.deleteMerchantsByMerchantIds(merchantIds);
    }

    /**
     * 删除商家信息信息
     * 
     * @param merchantId 商家信息主键
     * @return 结果
     */
    @Override
    public int deleteMerchantsByMerchantId(Long merchantId)
    {
        return merchantsMapper.deleteMerchantsByMerchantId(merchantId);
    }
}
