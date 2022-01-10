package com.example.squareboatnewsapp.utils.strategy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.squareboatnewsapp.utils.common.Resource
import kotlinx.coroutines.Dispatchers

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */

fun<A> performNetworkOperation(networkCall : suspend () -> Resource<A>) : LiveData<Resource<A>> =
        liveData(Dispatchers.IO) {
            val response = networkCall.invoke()
            when (response.status) {
                Resource.Status.ERROR -> {
                    val data = MutableLiveData<Resource<A>>()
                    data.postValue(Resource.error(response.message ?: "error"))
                    emitSource(data)
                }
                Resource.Status.SUCCESS -> {
                    emit(Resource.success(response.data!!))
                }
                else -> throw IllegalStateException("No State For This")
            }
        }




