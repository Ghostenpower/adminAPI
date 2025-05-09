package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 系统公告(仅管理员可发)对象 announcements
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public class Announcements extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    private Long announcementId;

    /** 公告标题 */
    @Excel(name = "公告标题")
    private String title;

    /** 公告内容 */
    @Excel(name = "公告内容")
    private String content;

    /** 管理员ID */
    @Excel(name = "管理员ID")
    private Long adminId;

    /** 公告类型(1=系统公告,2=活动通知,3=规则变更) */
    @Excel(name = "公告类型(1=系统公告,2=活动通知,3=规则变更)")
    private Integer announcementType;

    /** 生效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 失效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "失效时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 优先级(0=普通,1=重要,2=紧急) */
    @Excel(name = "优先级(0=普通,1=重要,2=紧急)")
    private Integer priority;

    /** 状态(0=草稿,1=已发布,2=已下线) */
    @Excel(name = "状态(0=草稿,1=已发布,2=已下线)")
    private Integer status;

    /** 目标受众(0=全部,1=用户,2=跑腿员,3=商家) */
    @Excel(name = "目标受众(0=全部,1=用户,2=跑腿员,3=商家)")
    private Integer targetAudience;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setAnnouncementId(Long announcementId) 
    {
        this.announcementId = announcementId;
    }

    public Long getAnnouncementId() 
    {
        return announcementId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setAdminId(Long adminId) 
    {
        this.adminId = adminId;
    }

    public Long getAdminId() 
    {
        return adminId;
    }

    public void setAnnouncementType(Integer announcementType) 
    {
        this.announcementType = announcementType;
    }

    public Integer getAnnouncementType() 
    {
        return announcementType;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setPriority(Integer priority) 
    {
        this.priority = priority;
    }

    public Integer getPriority() 
    {
        return priority;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setTargetAudience(Integer targetAudience) 
    {
        this.targetAudience = targetAudience;
    }

    public Integer getTargetAudience() 
    {
        return targetAudience;
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
            .append("announcementId", getAnnouncementId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("adminId", getAdminId())
            .append("announcementType", getAnnouncementType())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("priority", getPriority())
            .append("status", getStatus())
            .append("targetAudience", getTargetAudience())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
