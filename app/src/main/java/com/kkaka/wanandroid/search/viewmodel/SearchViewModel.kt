package com.kkaka.wanandroid.search.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.kkaka.common.constant.Constant
import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.common.article.viewmodel.ArticleViewModel
import com.kkaka.wanandroid.search.data.HotSearchRsp
import com.kkaka.wanandroid.search.data.SearchRepository
import com.kkaka.wanandroid.search.data.SearchResultRsp
import com.kkaka.wanandroid.search.data.db.Record
import com.kkaka.wanandroid.search.data.db.RecordDatabase

/**
 * @author Laizexin on 2019/12/11
 * @description
 */
class SearchViewModel(application: Application) : ArticleViewModel<SearchRepository>(application){

    val mSearchData : MutableLiveData<BaseResponse<SearchResultRsp>> = MutableLiveData()
    val mHotKeyData : MutableLiveData<BaseResponse<List<HotSearchRsp>>> = MutableLiveData()
    val mSearchHistory : MutableLiveData<List<Record>> = MutableLiveData()

    var database = RecordDatabase(application)

    fun search(page: Int, str: String){
        mRespository.search(page,str,mSearchData)
    }

    fun getHotSearch() {
        mRespository.getHotSearch(mHotKeyData)
    }

    fun getHistorySearch() {
        mSearchHistory.postValue(database.recordDao().getAll())
    }

    fun addHistorySearch(str: String) {
        val datas = database.recordDao().getAll()
        /**
         * 这里筛选 如果存在关键字了 则删除能筛选到的关键字记录
         * 如果不存在 判断现在记录个数 如果 超过了最大限制 则取出最后一条(指数组中)删除
         */

        val data = datas.filter { record ->
            return@filter record.name == str
        }.getOrElse(0) {
            return@getOrElse if (datas.size >= Constant.SEARCH_HISTORY_MAX) datas.get(0) else Record(
                -1,
                ""
            )
        }

        database.recordDao().delete(data)
        database.recordDao().insert(Record(0,str))
        mSearchHistory.postValue(database.recordDao().getAll())
    }

    fun clearHistorySearch(){
        val datas = database.recordDao().getAll()
        datas.forEach {
            database.recordDao().delete(it)
        }
        mSearchHistory.postValue(database.recordDao().getAll())
    }

}