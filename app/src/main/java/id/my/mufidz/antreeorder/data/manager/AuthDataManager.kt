package id.my.mufidz.antreeorder.data.manager

import id.my.mufidz.antreeorder.data.ApiServices
import id.my.mufidz.antreeorder.models.ApiResponse
import id.my.mufidz.antreeorder.models.User
import id.my.mufidz.antreeorder.utils.DataResult
import id.my.mufidz.antreeorder.utils.awaitResult
import id.my.mufidz.antreeorder.utils.tryCatchData
import javax.inject.Inject

interface AuthDataManager {
    suspend fun register(user: User): DataResult<ApiResponse<String>>
    suspend fun login(user: User): DataResult<ApiResponse<User>>
}

class AuthDataManagerImpl @Inject constructor(private val apiServices: ApiServices) :
    AuthDataManager {

    override suspend fun register(user: User): DataResult<ApiResponse<String>> =
        tryCatchData(apiServices.setRegister(user).awaitResult())

    override suspend fun login(user: User): DataResult<ApiResponse<User>> =
        tryCatchData(apiServices.setLogin(user).awaitResult())
}