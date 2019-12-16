package com.kkaka.wanandroid.search.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author Laizexin on 2019/12/16
 * @description
 */

@Database(entities = [Record::class],version = 1)
abstract class RecordDatabase : RoomDatabase(){

    abstract fun recordDao(): RecordDao

    companion object {
        @Volatile private var instance: RecordDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            RecordDatabase::class.java,
            "wan_android_db")
            .allowMainThreadQueries()
            .build()
    }
}