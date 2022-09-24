package id.my.mufidz.antreeorder.utils

import id.my.mufidz.antreeorder.models.ErrorResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.decodeFromString
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.resume

suspend fun <T : Any> Call<T>.awaitResult(): ResultData<T> {

    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                continuation.resumeWith(runCatching {
                    if (response.isSuccessful) {
                        val body = response.body()
                        Timber.tag("success").d(body.toString())
                        if (body == null) {
                            ResultData.Failed(NullPointerException("No Data"))
                        } else {
                            ResultData.Success(body)
                        }
                    } else {
                        ResultData.Failed(mapToHttpException(response))
                    }
                })
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (continuation.isCancelled) return
                continuation.resume(ResultData.Failed(mapToNetworkError(t)))
                Timber.d(t)
            }
        })

        continuation.invokeOnCancellation { runCatching { cancel() } }
    }
}

private fun <T> mapToHttpException(response: Response<T>): Exception {
    val httpException = HttpException(response)
    var errorResponse = ErrorResponse(httpException.code(), httpException.message())
    runCatching {
       errorResponse = response.errorBody()?.string()?.let {
           Utils.json.decodeFromString<ErrorResponse>(it)
        } ?: errorResponse
    }
    return Exception(errorResponse.message)
}

private fun mapToNetworkError(t: Throwable): Exception {
    return when (t) {
        is SocketTimeoutException -> SocketTimeoutException("Connection Timed Out")
        is UnknownHostException -> IOException("No Internet Connection")
        is ConnectException -> ConnectException("Failed to connect to server")
        else -> Exception(t.localizedMessage ?: "Some Unknown Error Occurred")
    }
}