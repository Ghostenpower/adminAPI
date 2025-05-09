package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TasksMapper;
import com.ruoyi.system.domain.Tasks;
import com.ruoyi.system.service.ITasksService;

/**
 * 跑腿任务Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
@Service
public class TasksServiceImpl implements ITasksService 
{
    @Autowired
    private TasksMapper tasksMapper;

    /**
     * 查询跑腿任务
     * 
     * @param taskId 跑腿任务主键
     * @return 跑腿任务
     */
    @Override
    public Tasks selectTasksByTaskId(Long taskId)
    {
        return tasksMapper.selectTasksByTaskId(taskId);
    }

    /**
     * 查询跑腿任务列表
     * 
     * @param tasks 跑腿任务
     * @return 跑腿任务
     */
    @Override
    public List<Tasks> selectTasksList(Tasks tasks)
    {
        return tasksMapper.selectTasksList(tasks);
    }

    /**
     * 新增跑腿任务
     * 
     * @param tasks 跑腿任务
     * @return 结果
     */
    @Override
    public int insertTasks(Tasks tasks)
    {
        return tasksMapper.insertTasks(tasks);
    }

    /**
     * 修改跑腿任务
     * 
     * @param tasks 跑腿任务
     * @return 结果
     */
    @Override
    public int updateTasks(Tasks tasks)
    {
        return tasksMapper.updateTasks(tasks);
    }

    /**
     * 批量删除跑腿任务
     * 
     * @param taskIds 需要删除的跑腿任务主键
     * @return 结果
     */
    @Override
    public int deleteTasksByTaskIds(Long[] taskIds)
    {
        return tasksMapper.deleteTasksByTaskIds(taskIds);
    }

    /**
     * 删除跑腿任务信息
     * 
     * @param taskId 跑腿任务主键
     * @return 结果
     */
    @Override
    public int deleteTasksByTaskId(Long taskId)
    {
        return tasksMapper.deleteTasksByTaskId(taskId);
    }
}
