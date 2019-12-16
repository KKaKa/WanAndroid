package com.kkaka.wanandroid.search.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Laizexin on 2019/12/16
 * @description
 */
@Entity(tableName = "search_history")
data class Record (
    @PrimaryKey(autoGenerate = true)
    var id : Int,

    @ColumnInfo(name = "name")
    var name :String
)