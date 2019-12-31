package com.kkaka.wanandroid.common

import android.content.Context
import android.os.Environment
import java.io.File
import java.math.BigDecimal

/**
 * @author Laizexin on 2019/12/31
 * @description
 */
object CacheManager {

    /**
     * 获取缓存大小
     */
    fun getCacheSize(context: Context) : String{
        var cacheSize = getFolderSize(context.cacheDir)
        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
            cacheSize += getFolderSize(context.externalCacheDir)
        }
        return getFormatSize(cacheSize.toDouble())
    }

    /**
     * 清除缓存
     */
    fun clearCache(context: Context){
        deleteDir(context.cacheDir)
        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
            deleteDir(context.externalCacheDir)
        }
    }

    /**
     * 删除文件夹
     */
    private fun deleteDir(file: File?) : Boolean{
        file?.let {
            if(it.isDirectory){
                val list = it.list()
                list.forEachIndexed { index, _ ->
                    if(!deleteDir(File(it, list[index]))){
                        return false
                    }
                }
            }
            return it.delete()
        }
        return false
    }

    /**
     * 获取文件夹大小
     */
    private fun getFolderSize(file : File?) : Long{
        file?.let {
            var size : Long = 0
            val fileList = it.listFiles()
            for (item in fileList) {
                if(item.isDirectory){
                    size += getFolderSize(item)
                }else{
                    size += item.length()
                }
            }
            return size
        }
        return 0
    }

    private fun getFormatSize(size: Double): String {
        val kiloByte = size / 1024
        if (kiloByte < 1) {
            return size.toString() + "B"
        }

        val megaByte = kiloByte / 1024
        if (megaByte < 1) {
            val result1 = BigDecimal(java.lang.Double.toString(kiloByte))
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB"
        }
        val gigaByte = megaByte / 1024
        if (gigaByte < 1) {
            val result2 = BigDecimal(java.lang.Double.toString(megaByte))
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB"
        }
        val teraBytes = gigaByte / 1024
        if (teraBytes < 1) {
            val result3 = BigDecimal(java.lang.Double.toString(gigaByte))
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB"
        }
        val result4 = BigDecimal(teraBytes)
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"
    }
}