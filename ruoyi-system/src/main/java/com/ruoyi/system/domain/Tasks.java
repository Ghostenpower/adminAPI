package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 跑腿任务对象 tasks
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public class Tasks extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long taskId;

    /** 发布用户ID */
    @Excel(name = "发布用户ID")
    private Long userId;

    /** 任务类型(1=代取快递,2=事务代办,3=物品代购,4=文件打印,5=其他) */
    @Excel(name = "任务类型(1=代取快递,2=事务代办,3=物品代购,4=文件打印,5=其他)")
    private Integer taskType;

    /** 任务标题 */
    @Excel(name = "任务标题")
    private String title;

    /** 任务详细描述 */
    @Excel(name = "任务详细描述")
    private String description;

    /** 取件地点 */
    @Excel(name = "取件地点")
    private String pickupLocation;

    /** 送达地点 */
    @Excel(name = "送达地点")
    private String deliveryLocation;

    /** 任务截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;

    /** 状态(0=待接单,1=已接单,2=进行中,3=已完成,4=已取消) */
    @Excel(name = "状态(0=待接单,1=已接单,2=进行中,3=已完成,4=已取消)")
    private Integer status;

    /** 支付状态(0=未支付,1=已支付,2=已退款) */
    @Excel(name = "支付状态(0=未支付,1=已支付,2=已退款)")
    private Integer paymentStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    /** 图片列表url(逗号分隔) */
    @Excel(name = "图片列表url(逗号分隔)")
    private String imagesUrl;

    /** 取件坐标（逗号分割） */
    @Excel(name = "取件坐标", readConverterExp = "逗=号分割")
    private String pickupCoordinates;

    /** 送达坐标 */
    @Excel(name = "送达坐标")
    private String deliveryCoordinates;

    /** 任务金额（单位：元，精确到分） */
    @Excel(name = "任务金额", readConverterExp = "单=位：元，精确到分")
    private BigDecimal price;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setTaskType(Integer taskType) 
    {
        this.taskType = taskType;
    }

    public Integer getTaskType() 
    {
        return taskType;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setPickupLocation(String pickupLocation) 
    {
        this.pickupLocation = pickupLocation;
    }

    public String getPickupLocation() 
    {
        return pickupLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) 
    {
        this.deliveryLocation = deliveryLocation;
    }

    public String getDeliveryLocation() 
    {
        return deliveryLocation;
    }

    public void setDeadline(Date deadline) 
    {
        this.deadline = deadline;
    }

    public Date getDeadline() 
    {
        return deadline;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setPaymentStatus(Integer paymentStatus) 
    {
        this.paymentStatus = paymentStatus;
    }

    public Integer getPaymentStatus() 
    {
        return paymentStatus;
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

    public void setImagesUrl(String imagesUrl) 
    {
        this.imagesUrl = imagesUrl;
    }

    public String getImagesUrl() 
    {
        return imagesUrl;
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

    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .append("taskType", getTaskType())
            .append("title", getTitle())
            .append("description", getDescription())
            .append("pickupLocation", getPickupLocation())
            .append("deliveryLocation", getDeliveryLocation())
            .append("deadline", getDeadline())
            .append("status", getStatus())
            .append("paymentStatus", getPaymentStatus())
            .append("remark", getRemark())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .append("imagesUrl", getImagesUrl())
            .append("pickupCoordinates", getPickupCoordinates())
            .append("deliveryCoordinates", getDeliveryCoordinates())
            .append("price", getPrice())
            .toString();
    }
}
