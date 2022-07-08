package com.wangkm.jetcaster.data

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author: created by wangkm
 * @time: 2022/07/07 12:18
 * @desc：
 * @email: 1240413544@qq.com
 */

@Entity(
    tableName = "podcasts",
    indices = [
        Index("uri", unique = true)
    ]
)
@Immutable
data class Podcast(
    @PrimaryKey @ColumnInfo(name = "uri") val uri: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "author") val author: String? = null,
    @ColumnInfo(name = "image_url") val imageUrl: String? = null,
    @ColumnInfo(name = "copyright") val copyright: String? = null
)
