package id.my.mufidz.antreeorder.usecase

import id.my.mufidz.antreeorder.base.ActionResult
import id.my.mufidz.antreeorder.base.BaseUseCase
import id.my.mufidz.antreeorder.data.manager.AuthDataManager
import id.my.mufidz.antreeorder.models.ApiResponse
import id.my.mufidz.antreeorder.models.User
import id.my.mufidz.antreeorder.utils.DataResult
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authDataManager: AuthDataManager
) : BaseUseCase<User, DataResult<ApiResponse<String>>, RegisterUseCase.UseCaseResult>() {


    override suspend fun execute(param: User): DataResult<ApiResponse<String>> =
        authDataManager.register(param)

    override suspend fun DataResult<ApiResponse<String>>.transformToUseCaseResult(): UseCaseResult =
        when (this) {
            is DataResult.Success -> UseCaseResult.Success(data.data ?: "Success")
            is DataResult.Error -> UseCaseResult.Failed(message)
        }

    sealed class UseCaseResult : ActionResult {
        data class Success(val message: String) : UseCaseResult()
        data class Failed(val message: String) : UseCaseResult()
    }
}

class LoginUseCase @Inject constructor(
    private val authDataManager: AuthDataManager
) : BaseUseCase<User, DataResult<ApiResponse<User>>, LoginUseCase.UseCaseResult>() {

    override suspend fun execute(param: User): DataResult<ApiResponse<User>> =
        authDataManager.login(param)

    override suspend fun DataResult<ApiResponse<User>>.transformToUseCaseResult(): UseCaseResult =
        when (this) {
            is DataResult.Success -> UseCaseResult.Success(data.data ?: User(), data.message)
            is DataResult.Error -> UseCaseResult.Failed(message)
        }

    sealed class UseCaseResult : ActionResult {
        data class Success(val user: User, val message: String) : UseCaseResult()
        data class Failed(val message: String) : UseCaseResult()
    }
}