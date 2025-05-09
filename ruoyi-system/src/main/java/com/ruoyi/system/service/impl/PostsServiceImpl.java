package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PostsMapper;
import com.ruoyi.system.domain.Posts;
import com.ruoyi.system.service.IPostsService;

/**
 * 动态Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
@Service
public class PostsServiceImpl implements IPostsService 
{
    @Autowired
    private PostsMapper postsMapper;

    /**
     * 查询动态
     * 
     * @param postId 动态主键
     * @return 动态
     */
    @Override
    public Posts selectPostsByPostId(Long postId)
    {
        return postsMapper.selectPostsByPostId(postId);
    }

    /**
     * 查询动态列表
     * 
     * @param posts 动态
     * @return 动态
     */
    @Override
    public List<Posts> selectPostsList(Posts posts)
    {
        return postsMapper.selectPostsList(posts);
    }

    /**
     * 新增动态
     * 
     * @param posts 动态
     * @return 结果
     */
    @Override
    public int insertPosts(Posts posts)
    {
        return postsMapper.insertPosts(posts);
    }

    /**
     * 修改动态
     * 
     * @param posts 动态
     * @return 结果
     */
    @Override
    public int updatePosts(Posts posts)
    {
        return postsMapper.updatePosts(posts);
    }

    /**
     * 批量删除动态
     * 
     * @param postIds 需要删除的动态主键
     * @return 结果
     */
    @Override
    public int deletePostsByPostIds(Long[] postIds)
    {
        return postsMapper.deletePostsByPostIds(postIds);
    }

    /**
     * 删除动态信息
     * 
     * @param postId 动态主键
     * @return 结果
     */
    @Override
    public int deletePostsByPostId(Long postId)
    {
        return postsMapper.deletePostsByPostId(postId);
    }
}
