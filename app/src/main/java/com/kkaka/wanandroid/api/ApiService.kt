package com.kkaka.wanandroid.api

import com.kkaka.common.https.BaseResponse
import com.kkaka.wanandroid.account.data.login.LoginRsp
import com.kkaka.wanandroid.account.data.regist.RegisterRsp
import com.kkaka.wanandroid.collect.data.CollectRsp
import com.kkaka.wanandroid.home.data.BannerRsp
import com.kkaka.wanandroid.home.data.HomeArticleRsp
import com.kkaka.wanandroid.nagivation.data.NagivationCategoryRsp
import com.kkaka.wanandroid.wechat.data.WeChatListRsp
import com.kkaka.wanandroid.wechat.data.WeChatNameRsp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
interface ApiService {

    /**
     * 首页文章
     */
    @GET("/article/list/{page}/json")
    fun getHomeArticle(@Path("page") page: Int): Observable<BaseResponse<HomeArticleRsp>>

    /**
     * 收藏
     */
    @POST("/lg/collect/{id}/json")
    fun collect(@Path("id") id: Int): Observable<BaseResponse<Any>>

    /**
     * 取消收藏
     */
    @POST("/lg/uncollect_originId/{id}/json")
    fun unCollect(@Path("id") id: Int): Observable<BaseResponse<Any>>

    /**
     * 登录
     */
    @POST("/user/login")
    fun getLogin(@Query("username") username: String,
                 @Query("password") password: String): Observable<BaseResponse<LoginRsp>>

    /**
     * 注册
     */
    @POST("/user/register")
    fun getRegister(@Query("username") username: String, @Query("password") password: String,
                    @Query("repassword") repassword: String): Observable<BaseResponse<RegisterRsp>>

    /**
     * 获取收藏
     */
    @GET("/lg/collect/list/{page}/json")
    fun getCollectArticle(@Path("page") page: Int): Observable<BaseResponse<CollectRsp>>

    /**
     * 取消收藏页收藏
     */
    @POST("/lg/uncollect/{id}/json")
    fun unMyCollect(@Path("id") id: Int, @Query("originId") originId: Int): Observable<BaseResponse<Any>>

    /**
     * 获取首页轮播图
     */
    @GET("/banner/json")
    fun getBanner(): Observable<BaseResponse<List<BannerRsp>>>

    /**
     * 获取微信头
     */
    @GET("/wxarticle/chapters/json")
    fun getWeChat(): Observable<BaseResponse<List<WeChatNameRsp>>>

    /**
     * 获取微信文章列表
     */
    @GET("/wxarticle/list/{id}/{page}/json")
    fun getWeChatList(@Path("id") id: Int, @Path("page") page: Int)
            : Observable<BaseResponse<WeChatListRsp>>

    /**
     * 导航页面数据
     */
    @GET("/navi/json")
    fun getCategory(): Observable<BaseResponse<List<NagivationCategoryRsp>>>
}