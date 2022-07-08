package com.wangkm.jetcaster.data

import com.wangkm.jetcaster.data.room.PodcastFollowedEntryDao
import com.wangkm.jetcaster.data.room.PodcastsDao
import com.wangkm.jetcaster.data.room.TransactionRunner
import kotlinx.coroutines.flow.Flow

/**
 * @author: created by wangkm
 * @time: 2022/07/07 12:19
 * @desc：
 * @email: 1240413544@qq.com
 */

class PodcastStore(
    private val podcastDao: PodcastsDao,
    private val podcastFollowedEntryDao: PodcastFollowedEntryDao,
    private val transactionRunner: TransactionRunner
) {
    /**
     * Return a flow containing the [Podcast] with the given [uri].
     */
    fun podcastWithUri(uri: String): Flow<Podcast> {
        return podcastDao.podcastWithUri(uri)
    }

    /**
     * Returns a flow containing the entire collection of podcasts, sorted by the last episode
     * publish date for each podcast.
     */
    fun podcastsSortedByLastEpisode(
        limit: Int = Int.MAX_VALUE
    ): Flow<List<PodcastWithExtraInfo>> {
        return podcastDao.podcastsSortedByLastEpisode(limit)
    }

    /**
     * Returns a flow containing a list of all followed podcasts, sorted by the their last
     * episode date.
     */
    fun followedPodcastsSortedByLastEpisode(
        limit: Int = Int.MAX_VALUE
    ): Flow<List<PodcastWithExtraInfo>> {
        return podcastDao.followedPodcastsSortedByLastEpisode(limit)
    }

    private suspend fun followPodcast(podcastUri: String) {
        podcastFollowedEntryDao.insert(PodcastFollowedEntry(podcastUri = podcastUri))
    }

    suspend fun togglePodcastFollowed(podcastUri: String) = transactionRunner {
        if (podcastFollowedEntryDao.isPodcastFollowed(podcastUri)) {
            unfollowPodcast(podcastUri)
        } else {
            followPodcast(podcastUri)
        }
    }

    suspend fun unfollowPodcast(podcastUri: String) {
        podcastFollowedEntryDao.deleteWithPodcastUri(podcastUri)
    }

    /**
     * Add a new [Podcast] to this store.
     *
     * This automatically switches to the main thread to maintain thread consistency.
     */
    suspend fun addPodcast(podcast: Podcast) {
        podcastDao.insert(podcast)
    }

    suspend fun isEmpty(): Boolean = podcastDao.count() == 0
}


