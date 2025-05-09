package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地址信息对象 addresses
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public class Addresses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 地址ID */
    private Long addressId;

    /** 关联用户ID */
    @Excel(name = "关联用户ID")
    private Long userId;

    /** 地址类型(1=用户地址,2=商家地址) */
    @Excel(name = "地址类型(1=用户地址,2=商家地址)")
    private Integer addressType;

    /** 收件人姓名 */
    @Excel(name = "收件人姓名")
    private String recipientName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailedAddress;

    /** 是否默认地址(0=否,1=是) */
    @Excel(name = "是否默认地址(0=否,1=是)")
    private Integer isDefault;

    /** 状态(0=禁用,1=正常) */
    @Excel(name = "状态(0=禁用,1=正常)")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    /** 取件坐标（逗号分割） */
    @Excel(name = "取件坐标", readConverterExp = "逗=号分割")
    private String pickupCoordinates;

    /** 送达坐标（逗号分割） */
    @Excel(name = "送达坐标", readConverterExp = "逗=号分割")
    private String deliveryCoordinates;

    public void setAddressId(Long addressId) 
    {
        this.addressId = addressId;
    }

    public Long getAddressId() 
    {
        return addressId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setAddressType(Integer addressType) 
    {
        this.addressType = addressType;
    }

    public Integer getAddressType() 
    {
        return addressType;
    }

    public void setRecipientName(String recipientName) 
    {
        this.recipientName = recipientName;
    }

    public String getRecipientName() 
    {
        return recipientName;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setDetailedAddress(String detailedAddress) 
    {
        this.detailedAddress = detailedAddress;
    }

    public String getDetailedAddress() 
    {
        return detailedAddress;
    }

    public void setIsDefault(Integer isDefault) 
    {
        this.isDefault = isDefault;
    }

    public Integer getIsDefault() 
    {
        return isDefault;
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

    public void setPickupCoordinates(String pickupCoordinates) 
    {
        this.pickupCoordinates = pickupCoordinates;
    }

    public String getPickupCoordinates() 
    {
        return pickupCoordinates;
    }

    public void setDeliveryCoordinates(String deliveryCoordinates) 
    {
        this.deliveryCoordinates = deliveryCoordinates;
    }

    public String getDeliveryCoordinates() 
    {
        return deliveryCoordinates;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("addressId", getAddressId())
            .append("userId", getUserId())
            .append("addressType", getAddressType())
            .append("recipientName", getRecipientName())
            .append("phone", getPhone())
            .append("detailedAddress", getDetailedAddress())
            .append("isDefault", getIsDefault())
            .append("status", getStatus())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .append("pickupCoordinates", getPickupCoordinates())
            .append("deliveryCoordinates", getDeliveryCoordinates())
            .toString();
    }
}
