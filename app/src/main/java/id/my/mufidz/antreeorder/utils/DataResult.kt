package id.my.mufidz.antreeorder.utils

import id.my.mufidz.antreeorder.base.UseCaseResult

sealed class DataResult<out T>  : UseCaseResult(){
    data class Success<T>(val data: T) : DataResult<T>()
    data class Error(val message: String) : DataResult<Nothing>()
}