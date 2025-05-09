package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Announcements;

/**
 * 系统公告(仅管理员可发)Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public interface AnnouncementsMapper 
{
    /**
     * 查询系统公告(仅管理员可发)
     * 
     * @param announcementId 系统公告(仅管理员可发)主键
     * @return 系统公告(仅管理员可发)
     */
    public Announcements selectAnnouncementsByAnnouncementId(Long announcementId);

    /**
     * 查询系统公告(仅管理员可发)列表
     * 
     * @param announcements 系统公告(仅管理员可发)
     * @return 系统公告(仅管理员可发)集合
     */
    public List<Announcements> selectAnnouncementsList(Announcements announcements);

    /**
     * 新增系统公告(仅管理员可发)
     * 
     * @param announcements 系统公告(仅管理员可发)
     * @return 结果
     */
    public int insertAnnouncements(Announcements announcements);

    /**
     * 修改系统公告(仅管理员可发)
     * 
     * @param announcements 系统公告(仅管理员可发)
     * @return 结果
     */
    public int updateAnnouncements(Announcements announcements);

    /**
     * 删除系统公告(仅管理员可发)
     * 
     * @param announcementId 系统公告(仅管理员可发)主键
     * @return 结果
     */
    public int deleteAnnouncementsByAnnouncementId(Long announcementId);

    /**
     * 批量删除系统公告(仅管理员可发)
     * 
     * @param announcementIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnnouncementsByAnnouncementIds(Long[] announcementIds);
}
