package com.wangkm.jetcaster.data

import androidx.compose.runtime.Immutable
import androidx.room.*

/**
 * @author: created by wangkm
 * @time: 2022/07/07 12:23
 * @desc：
 * @email: 1240413544@qq.com
 */

@Entity(
    tableName = "podcast_followed_entries",
    foreignKeys = [
        ForeignKey(
            entity = Podcast::class,
            parentColumns = ["uri"],
            childColumns = ["podcast_uri"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("podcast_uri", unique = true)
    ]
)
@Immutable
data class PodcastFollowedEntry(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "podcast_uri") val podcastUri: String
)

