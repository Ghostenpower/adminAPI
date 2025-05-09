package com.ruoyi.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Announcements;
import com.ruoyi.system.service.IAnnouncementsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 系统公告(仅管理员可发)Controller
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
@RestController
@RequestMapping("/system/announcements")
public class AnnouncementsController extends BaseController
{
    @Autowired
    private IAnnouncementsService announcementsService;

    /**
     * 查询系统公告(仅管理员可发)列表
     */
    @PreAuthorize("@ss.hasPermi('system:announcements:list')")
    @GetMapping("/list")
    public TableDataInfo list(Announcements announcements)
    {
        startPage();
        List<Announcements> list = announcementsService.selectAnnouncementsList(announcements);
        return getDataTable(list);
    }

    /**
     * 导出系统公告(仅管理员可发)列表
     */
    @PreAuthorize("@ss.hasPermi('system:announcements:export')")
    @Log(title = "系统公告(仅管理员可发)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Announcements announcements)
    {
        List<Announcements> list = announcementsService.selectAnnouncementsList(announcements);
        ExcelUtil<Announcements> util = new ExcelUtil<Announcements>(Announcements.class);
        util.exportExcel(response, list, "系统公告(仅管理员可发)数据");
    }

    /**
     * 获取系统公告(仅管理员可发)详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:announcements:query')")
    @GetMapping(value = "/{announcementId}")
    public AjaxResult getInfo(@PathVariable("announcementId") Long announcementId)
    {
        return success(announcementsService.selectAnnouncementsByAnnouncementId(announcementId));
    }

    /**
     * 新增系统公告(仅管理员可发)
     */
    @PreAuthorize("@ss.hasPermi('system:announcements:add')")
    @Log(title = "系统公告(仅管理员可发)", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Announcements announcements)
    {
        return toAjax(announcementsService.insertAnnouncements(announcements));
    }

    /**
     * 修改系统公告(仅管理员可发)
     */
    @PreAuthorize("@ss.hasPermi('system:announcements:edit')")
    @Log(title = "系统公告(仅管理员可发)", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Announcements announcements)
    {
        return toAjax(announcementsService.updateAnnouncements(announcements));
    }

    /**
     * 删除系统公告(仅管理员可发)
     */
    @PreAuthorize("@ss.hasPermi('system:announcements:remove')")
    @Log(title = "系统公告(仅管理员可发)", businessType = BusinessType.DELETE)
	@DeleteMapping("/{announcementIds}")
    public AjaxResult remove(@PathVariable Long[] announcementIds)
    {
        return toAjax(announcementsService.deleteAnnouncementsByAnnouncementIds(announcementIds));
    }
}
