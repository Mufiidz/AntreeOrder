package id.my.mufidz.antreeorder.models

import kotlinx.serialization.Serializable

@Serializable
sealed class BaseResponse {
    abstract var code: Int
    abstract var message: String
}

@Serializable
data class ApiResponse<T>(
    override var code: Int,
    override var message: String,
    var data: T? = null,
) : BaseResponse()

@Serializable
data class ErrorResponse(
    override var code: Int,
    override var message: String,
    var path: String = "",
    var timestamp: String = ""
) : BaseResponse()
