package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Merchants;

/**
 * 商家信息Service接口
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public interface IMerchantsService 
{
    /**
     * 查询商家信息
     * 
     * @param merchantId 商家信息主键
     * @return 商家信息
     */
    public Merchants selectMerchantsByMerchantId(Long merchantId);

    /**
     * 查询商家信息列表
     * 
     * @param merchants 商家信息
     * @return 商家信息集合
     */
    public List<Merchants> selectMerchantsList(Merchants merchants);

    /**
     * 新增商家信息
     * 
     * @param merchants 商家信息
     * @return 结果
     */
    public int insertMerchants(Merchants merchants);

    /**
     * 修改商家信息
     * 
     * @param merchants 商家信息
     * @return 结果
     */
    public int updateMerchants(Merchants merchants);

    /**
     * 批量删除商家信息
     * 
     * @param merchantIds 需要删除的商家信息主键集合
     * @return 结果
     */
    public int deleteMerchantsByMerchantIds(Long[] merchantIds);

    /**
     * 删除商家信息信息
     * 
     * @param merchantId 商家信息主键
     * @return 结果
     */
    public int deleteMerchantsByMerchantId(Long merchantId);
}
