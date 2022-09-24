package id.my.mufidz.antreeorder.utils

import timber.log.Timber

fun <T> tryCatchData(response: ResultData<T>): DataResult<T> = runCatching {
    Timber.d(response.toString())
    when (response) {
        is ResultData.Failed -> DataResult.Error(response.exception.localizedMessage.orEmpty())
        is ResultData.Success -> DataResult.Success(response.value)
    }
}.getOrElse { e ->
    Timber.d("ERROR : $e")
    DataResult.Error(e.localizedMessage ?: "ERROR")
}