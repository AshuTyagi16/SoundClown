package com.sasuke.soundclown.util

import com.google.gson.Gson
import com.sasuke.soundclown.data.exception.NoConnectivityException
import com.sasuke.soundclown.data.model.CustomError
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

abstract class ApiCallback<T> : retrofit2.Callback<T> {

    final override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            response.body()?.let { body ->
                success(body)
            } ?: run {
                createError(response)
            }
        } else {
            createError(response)
        }
    }

    final override fun onFailure(call: Call<T>, t: Throwable) {
        Timber.e(t.cause)
        var error = CustomError()
        if (t is NoConnectivityException) {
            error = CustomError(com.sasuke.soundclown.data.model.Error(message = t.message!!))
            failure(error)
        } else if (t is SSLHandshakeException) {
            error =
                CustomError(com.sasuke.soundclown.data.model.Error(message = "Internet not working properly"))
            failure(error)
        } else if (t is UnknownHostException) {
            error =
                CustomError(com.sasuke.soundclown.data.model.Error(message = "Internet not working properly"))
            failure(error)
        } else
            failure(error)
    }

    private fun createError(response: Response<T>) {
        response.errorBody()?.let { errorBody ->
            try {
                val error = Gson().fromJson<CustomError>(
                    errorBody.string(),
                    CustomError::class.java
                )
                failure(error)
            } catch (e: Exception) {
                failure(CustomError(com.sasuke.soundclown.data.model.Error(message = e.localizedMessage)))
            }
        } ?: run { failure(CustomError(com.sasuke.soundclown.data.model.Error())) }
    }

    abstract fun success(response: T)

    abstract fun failure(error: CustomError)

}
