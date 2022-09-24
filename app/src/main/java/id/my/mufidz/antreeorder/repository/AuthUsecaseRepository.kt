package id.my.mufidz.antreeorder.repository

import id.my.mufidz.antreeorder.models.User
import id.my.mufidz.antreeorder.usecase.LoginUseCase
import id.my.mufidz.antreeorder.usecase.RegisterUseCase
import javax.inject.Inject

interface AuthUsecaseRepository {
    suspend fun register(user: User) : RegisterUseCase.UseCaseResult
    suspend fun login(user: User) : LoginUseCase.UseCaseResult
}

class AuthUseCaseRepositoryImpl @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
) : AuthUsecaseRepository {

    override suspend fun register(user: User) : RegisterUseCase.UseCaseResult =
        registerUseCase.getResult(user)

    override suspend fun login(user: User) : LoginUseCase.UseCaseResult =
        loginUseCase.getResult(user)
}