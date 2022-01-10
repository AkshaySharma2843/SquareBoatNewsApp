package com.example.squareboatnewsapp.data.source

import android.util.Log
import androidx.annotation.Keep
import com.example.squareboatnewsapp.utils.common.Resource
import retrofit2.Response

/**
 * @Author: Akshay Sharma
 * @Date: 09/01/22
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.d("ERROR",message)
        return Resource.error("Network call has failed for a following reason: $message")
    }

}