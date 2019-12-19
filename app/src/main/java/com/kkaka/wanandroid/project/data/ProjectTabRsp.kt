package com.kkaka.wanandroid.project.data

data class ProjectTabRsp (
    val children : List<String>,
    val courseId : Int,
    val id : Int,
    val name : String,
    val order : Int,
    val parentChapterId : Int,
    val userControlSetTop : Boolean,
    val visible : Int
)