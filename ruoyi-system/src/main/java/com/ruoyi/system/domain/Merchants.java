package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商家信息对象 merchants
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public class Merchants extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商家ID */
    private Long merchantId;

    /** 关联用户ID */
    @Excel(name = "关联用户ID")
    private Long userId;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 店铺LOGO URL */
    @Excel(name = "店铺LOGO URL")
    private String shopLogo;

    /** 营业执照URL */
    @Excel(name = "营业执照URL")
    private String businessLicense;

    /** 店铺地址 */
    @Excel(name = "店铺地址")
    private String address;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 营业时间 */
    @Excel(name = "营业时间")
    private String businessHours;

    /** 店铺描述 */
    @Excel(name = "店铺描述")
    private String description;

    /** 商家类别 */
    @Excel(name = "商家类别")
    private String category;

    /** 状态(0待审核,1已通过,2已拒绝) */
    @Excel(name = "状态(0待审核,1已通过,2已拒绝)")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setMerchantId(Long merchantId) 
    {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() 
    {
        return merchantId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setShopName(String shopName) 
    {
        this.shopName = shopName;
    }

    public String getShopName() 
    {
        return shopName;
    }

    public void setShopLogo(String shopLogo) 
    {
        this.shopLogo = shopLogo;
    }

    public String getShopLogo() 
    {
        return shopLogo;
    }

    public void setBusinessLicense(String businessLicense) 
    {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicense() 
    {
        return businessLicense;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }

    public void setBusinessHours(String businessHours) 
    {
        this.businessHours = businessHours;
    }

    public String getBusinessHours() 
    {
        return businessHours;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("merchantId", getMerchantId())
            .append("userId", getUserId())
            .append("shopName", getShopName())
            .append("shopLogo", getShopLogo())
            .append("businessLicense", getBusinessLicense())
            .append("address", getAddress())
            .append("contactPhone", getContactPhone())
            .append("businessHours", getBusinessHours())
            .append("description", getDescription())
            .append("category", getCategory())
            .append("status", getStatus())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
