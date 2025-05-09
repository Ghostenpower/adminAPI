package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Tasks;

/**
 * 跑腿任务Service接口
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public interface ITasksService 
{
    /**
     * 查询跑腿任务
     * 
     * @param taskId 跑腿任务主键
     * @return 跑腿任务
     */
    public Tasks selectTasksByTaskId(Long taskId);

    /**
     * 查询跑腿任务列表
     * 
     * @param tasks 跑腿任务
     * @return 跑腿任务集合
     */
    public List<Tasks> selectTasksList(Tasks tasks);

    /**
     * 新增跑腿任务
     * 
     * @param tasks 跑腿任务
     * @return 结果
     */
    public int insertTasks(Tasks tasks);

    /**
     * 修改跑腿任务
     * 
     * @param tasks 跑腿任务
     * @return 结果
     */
    public int updateTasks(Tasks tasks);

    /**
     * 批量删除跑腿任务
     * 
     * @param taskIds 需要删除的跑腿任务主键集合
     * @return 结果
     */
    public int deleteTasksByTaskIds(Long[] taskIds);

    /**
     * 删除跑腿任务信息
     * 
     * @param taskId 跑腿任务主键
     * @return 结果
     */
    public int deleteTasksByTaskId(Long taskId);
}
