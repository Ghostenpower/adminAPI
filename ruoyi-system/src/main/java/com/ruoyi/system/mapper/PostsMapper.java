package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Posts;

/**
 * 动态Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
public interface PostsMapper 
{
    /**
     * 查询动态
     * 
     * @param postId 动态主键
     * @return 动态
     */
    public Posts selectPostsByPostId(Long postId);

    /**
     * 查询动态列表
     * 
     * @param posts 动态
     * @return 动态集合
     */
    public List<Posts> selectPostsList(Posts posts);

    /**
     * 新增动态
     * 
     * @param posts 动态
     * @return 结果
     */
    public int insertPosts(Posts posts);

    /**
     * 修改动态
     * 
     * @param posts 动态
     * @return 结果
     */
    public int updatePosts(Posts posts);

    /**
     * 删除动态
     * 
     * @param postId 动态主键
     * @return 结果
     */
    public int deletePostsByPostId(Long postId);

    /**
     * 批量删除动态
     * 
     * @param postIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePostsByPostIds(Long[] postIds);
}
