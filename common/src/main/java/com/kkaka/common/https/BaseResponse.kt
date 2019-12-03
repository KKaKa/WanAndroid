package com.kkaka.common.https

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
open class BaseResponse<T>(var data: T, var errorCode: Int = -1, var errorMsg: String = "")
