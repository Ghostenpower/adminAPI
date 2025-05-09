package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 跑腿员信息对象 couriers
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public class Couriers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 跑腿员ID */
    private Long courierId;

    /** 关联用户ID */
    @Excel(name = "关联用户ID")
    private Long userId;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 身份证正面照片URL */
    @Excel(name = "身份证正面照片URL")
    private String idCardFront;

    /** 身份证反面照片URL */
    @Excel(name = "身份证反面照片URL")
    private String idCardBack;

    /** 学生证照片URL */
    @Excel(name = "学生证照片URL")
    private String studentCard;

    /** 信用分(0-100) */
    @Excel(name = "信用分(0-100)")
    private Long creditScore;

    /** 完成订单总数 */
    @Excel(name = "完成订单总数")
    private Long totalOrders;

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

    public void setCourierId(Long courierId) 
    {
        this.courierId = courierId;
    }

    public Long getCourierId() 
    {
        return courierId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }

    public void setIdCardFront(String idCardFront) 
    {
        this.idCardFront = idCardFront;
    }

    public String getIdCardFront() 
    {
        return idCardFront;
    }

    public void setIdCardBack(String idCardBack) 
    {
        this.idCardBack = idCardBack;
    }

    public String getIdCardBack() 
    {
        return idCardBack;
    }

    public void setStudentCard(String studentCard) 
    {
        this.studentCard = studentCard;
    }

    public String getStudentCard() 
    {
        return studentCard;
    }

    public void setCreditScore(Long creditScore) 
    {
        this.creditScore = creditScore;
    }

    public Long getCreditScore() 
    {
        return creditScore;
    }

    public void setTotalOrders(Long totalOrders) 
    {
        this.totalOrders = totalOrders;
    }

    public Long getTotalOrders() 
    {
        return totalOrders;
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
            .append("courierId", getCourierId())
            .append("userId", getUserId())
            .append("idCard", getIdCard())
            .append("idCardFront", getIdCardFront())
            .append("idCardBack", getIdCardBack())
            .append("studentCard", getStudentCard())
            .append("creditScore", getCreditScore())
            .append("totalOrders", getTotalOrders())
            .append("status", getStatus())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
