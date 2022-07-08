package com.wangkm.jetcaster.data

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author: created by wangkm
 * @time: 2022/07/07 12:19
 * @desc：
 * @email: 1240413544@qq.com
 */

@Entity(
    tableName = "categories",
    indices = [
        Index("name", unique = true)
    ]
)
@Immutable
data class Category(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name") val name: String
)

