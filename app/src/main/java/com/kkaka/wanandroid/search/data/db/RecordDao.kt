package com.kkaka.wanandroid.search.data.db

import android.arch.lifecycle.MutableLiveData
import androidx.room.*

/**
 * @author Laizexin on 2019/12/16
 * @description
 */
@Dao
interface RecordDao {

    @Query("SELECT * FROM search_history")
    fun getAll(): List<Record>

    @Insert
    fun insert(vararg record: Record)

    @Delete
    fun delete(record: Record?)

    @Update
    fun update(vararg record: Record)
}