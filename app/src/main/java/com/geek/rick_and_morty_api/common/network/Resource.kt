package com.geek.android_trainee_task_2021_mira.common.network

class Resource<out T>(val status: Status, val data: T?, val msg: String?, val code: Int?) {

    companion object {
        fun<T> success(data: T?,code: Int): Resource<T> {
            return Resource(Status.SUCCESS,data,null,code)
        }

        fun<T> error(msg: String?,data: T?,code: Int): Resource<T> {
            return Resource(Status.ERROR,data,msg,code)
        }

        fun<T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING,data,null,null)
        }
    }
}