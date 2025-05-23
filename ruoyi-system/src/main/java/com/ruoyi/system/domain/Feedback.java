package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 意见反馈对象 feedback
 * 
 * @author ruoyi
 * @date 2025-05-23
 */
public class Feedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户建议 */
    @Excel(name = "用户建议")
    private String suggestion;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contact;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String imagesUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setSuggestion(String suggestion) 
    {
        this.suggestion = suggestion;
    }

    public String getSuggestion() 
    {
        return suggestion;
    }

    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }

    public void setImagesUrl(String imagesUrl) 
    {
        this.imagesUrl = imagesUrl;
    }

    public String getImagesUrl() 
    {
        return imagesUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("suggestion", getSuggestion())
            .append("contact", getContact())
            .append("imagesUrl", getImagesUrl())
            .append("createTime", getCreateTime())
            .toString();
    }
}
