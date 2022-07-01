package com.wangkm.jetnews.data.posts

import com.wangkm.jetnews.model.Post
import com.wangkm.jetnews.model.PostsFeed
import com.wangkm.jetnews.data.Result
import kotlinx.coroutines.flow.Flow

/**
 * @author: created by wangkm
 * @time: 2022/06/27 12:28
 * @desc：
 * @email: 1240413544@qq.com
 */

interface PostsRepository {

    /**
     * Get a specific JetNews post.
     */
    suspend fun getPost(postId: String?): Result<Post>

    /**
     * Get JetNews posts.
     */
    suspend fun getPostsFeed(): Result<PostsFeed>

    /**
     * Observe the current favorites
     */
    fun observeFavorites(): Flow<Set<String>>

    /**
     * Toggle a postId to be a favorite or not.
     */
    suspend fun toggleFavorite(postId: String)
}