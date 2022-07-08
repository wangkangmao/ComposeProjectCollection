package com.wangkm.jetcaster.data

import com.wangkm.jetcaster.data.room.EpisodesDao
import kotlinx.coroutines.flow.Flow

/**
 * @author: created by wangkm
 * @time: 2022/07/07 12:24
 * @desc：
 * @email: 1240413544@qq.com
 */

class EpisodeStore(
    private val episodesDao: EpisodesDao
) {
    /**
     * Returns a flow containing the episode given [episodeUri].
     */
    fun episodeWithUri(episodeUri: String): Flow<Episode> {
        return episodesDao.episode(episodeUri)
    }

    /**
     * Returns a flow containing the list of episodes associated with the podcast with the
     * given [podcastUri].
     */
    fun episodesInPodcast(
        podcastUri: String,
        limit: Int = Integer.MAX_VALUE
    ): Flow<List<Episode>> {
        return episodesDao.episodesForPodcastUri(podcastUri, limit)
    }

    /**
     * Add a new [Episode] to this store.
     *
     * This automatically switches to the main thread to maintain thread consistency.
     */
    suspend fun addEpisodes(episodes: Collection<Episode>) = episodesDao.insertAll(episodes)

    suspend fun isEmpty(): Boolean = episodesDao.count() == 0
}
