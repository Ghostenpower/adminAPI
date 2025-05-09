package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AnnouncementsMapper;
import com.ruoyi.system.domain.Announcements;
import com.ruoyi.system.service.IAnnouncementsService;

/**
 * 系统公告(仅管理员可发)Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
@Service
public class AnnouncementsServiceImpl implements IAnnouncementsService 
{
    @Autowired
    private AnnouncementsMapper announcementsMapper;

    /**
     * 查询系统公告(仅管理员可发)
     * 
     * @param announcementId 系统公告(仅管理员可发)主键
     * @return 系统公告(仅管理员可发)
     */
    @Override
    public Announcements selectAnnouncementsByAnnouncementId(Long announcementId)
    {
        return announcementsMapper.selectAnnouncementsByAnnouncementId(announcementId);
    }

    /**
     * 查询系统公告(仅管理员可发)列表
     * 
     * @param announcements 系统公告(仅管理员可发)
     * @return 系统公告(仅管理员可发)
     */
    @Override
    public List<Announcements> selectAnnouncementsList(Announcements announcements)
    {
        return announcementsMapper.selectAnnouncementsList(announcements);
    }

    /**
     * 新增系统公告(仅管理员可发)
     * 
     * @param announcements 系统公告(仅管理员可发)
     * @return 结果
     */
    @Override
    public int insertAnnouncements(Announcements announcements)
    {
        return announcementsMapper.insertAnnouncements(announcements);
    }

    /**
     * 修改系统公告(仅管理员可发)
     * 
     * @param announcements 系统公告(仅管理员可发)
     * @return 结果
     */
    @Override
    public int updateAnnouncements(Announcements announcements)
    {
        return announcementsMapper.updateAnnouncements(announcements);
    }

    /**
     * 批量删除系统公告(仅管理员可发)
     * 
     * @param announcementIds 需要删除的系统公告(仅管理员可发)主键
     * @return 结果
     */
    @Override
    public int deleteAnnouncementsByAnnouncementIds(Long[] announcementIds)
    {
        return announcementsMapper.deleteAnnouncementsByAnnouncementIds(announcementIds);
    }

    /**
     * 删除系统公告(仅管理员可发)信息
     * 
     * @param announcementId 系统公告(仅管理员可发)主键
     * @return 结果
     */
    @Override
    public int deleteAnnouncementsByAnnouncementId(Long announcementId)
    {
        return announcementsMapper.deleteAnnouncementsByAnnouncementId(announcementId);
    }
}
