package com.kkaka.common.https

import com.kkaka.common.constant.Constant
import com.kkaka.common.utils.Preference
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

/**
 * @author Laizexin on 2019/11/28
 * @description Retrofit
 */
class RetrofitFactory private constructor(){

    private val retrofit : Retrofit

    fun <T> create(clz: Class<T>): T {
        return retrofit.create(clz)
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(initOkHttpClient())
            .build();
    }

    companion object {
        val instance by lazy {
            RetrofitFactory()
        }
    }

    private fun initOkHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .addNetworkInterceptor(LoggingInterceptor())
            .addInterceptor(initCookieIntercept())
            .addInterceptor(initLoginIntercept())
            .addInterceptor(initCommonInterceptor())
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    //在登录或者注册是 保存cookie 在下次登录时取出供自动登录使用
    private fun initCookieIntercept(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            val requestUrl = request.url().toString()
            val domain = request.url().host()
            //只保存登录或者注册
            if(requestUrl.contains(Constant.LOGIN_KEY) || requestUrl.contains(Constant.REGISTER_KEY)){
                val mCookie = response.headers(Constant.SET_COOKIE_KEY)
                mCookie?.let {
                    saveCookie(domain,parseCookie(it))
                }
            }
            response
        }
    }

    //自动登录
    private fun initLoginIntercept(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()
            val domain = request.url().host()

            if(domain.isNotEmpty()){
                val mCookie by Preference(domain,"")
                if(mCookie.isNotEmpty()){
                    builder.addHeader(Constant.COOKIE_NAME,mCookie)
                }
            }
            val response = chain.proceed(request)
            response
        }
    }

    private fun initCommonInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("charset", "UTF-8")
                .build()

            chain.proceed(request)
        }
    }

    private fun parseCookie(it: List<String>): String {
        if(it.isEmpty()){
            return ""
        }

        val stringBuilder = StringBuilder()

        it.forEach { cookie ->
            stringBuilder.append(cookie).append(";")
        }

        if(stringBuilder.isEmpty()){
            return ""
        }
        //末尾的";"去掉
        return stringBuilder.deleteCharAt(stringBuilder.length - 1).toString()
    }

    private fun saveCookie(domain: String?, parseCookie: String) {
        domain?.let {
            var resutl :String by Preference(it,parseCookie)
            resutl = parseCookie
        }
    }

}