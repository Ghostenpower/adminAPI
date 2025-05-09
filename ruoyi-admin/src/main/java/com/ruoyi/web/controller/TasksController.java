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
import com.ruoyi.system.domain.Tasks;
import com.ruoyi.system.service.ITasksService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 跑腿任务Controller
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
@RestController
@RequestMapping("/system/tasks")
public class TasksController extends BaseController
{
    @Autowired
    private ITasksService tasksService;

    /**
     * 查询跑腿任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:tasks:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tasks tasks)
    {
        startPage();
        List<Tasks> list = tasksService.selectTasksList(tasks);
        return getDataTable(list);
    }

    /**
     * 导出跑腿任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:tasks:export')")
    @Log(title = "跑腿任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Tasks tasks)
    {
        List<Tasks> list = tasksService.selectTasksList(tasks);
        ExcelUtil<Tasks> util = new ExcelUtil<Tasks>(Tasks.class);
        util.exportExcel(response, list, "跑腿任务数据");
    }

    /**
     * 获取跑腿任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tasks:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(tasksService.selectTasksByTaskId(taskId));
    }

    /**
     * 新增跑腿任务
     */
    @PreAuthorize("@ss.hasPermi('system:tasks:add')")
    @Log(title = "跑腿任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tasks tasks)
    {
        return toAjax(tasksService.insertTasks(tasks));
    }

    /**
     * 修改跑腿任务
     */
    @PreAuthorize("@ss.hasPermi('system:tasks:edit')")
    @Log(title = "跑腿任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tasks tasks)
    {
        return toAjax(tasksService.updateTasks(tasks));
    }

    /**
     * 删除跑腿任务
     */
    @PreAuthorize("@ss.hasPermi('system:tasks:remove')")
    @Log(title = "跑腿任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(tasksService.deleteTasksByTaskIds(taskIds));
    }
}
