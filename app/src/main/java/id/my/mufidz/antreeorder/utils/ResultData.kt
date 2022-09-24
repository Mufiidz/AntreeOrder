package id.my.mufidz.antreeorder.utils

sealed class ResultData<out T> {
    data class Success<T>(val value: T) : ResultData<T>()
    data class Failed(val exception: Exception) : ResultData<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data = $value]"
            is Failed -> "Failed[exception = $exception]"
        }
    }
}
