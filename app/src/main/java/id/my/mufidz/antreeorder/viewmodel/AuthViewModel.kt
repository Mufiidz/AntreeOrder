package id.my.mufidz.antreeorder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.mufidz.antreeorder.base.ActionResult
import id.my.mufidz.antreeorder.base.BaseViewModel
import id.my.mufidz.antreeorder.intention.AuthAction
import id.my.mufidz.antreeorder.intention.AuthViewState
import id.my.mufidz.antreeorder.repository.AuthUsecaseRepository
import id.my.mufidz.antreeorder.usecase.LoginUseCase
import id.my.mufidz.antreeorder.usecase.RegisterUseCase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUsecaseRepository: AuthUsecaseRepository
) : BaseViewModel<AuthViewState, AuthAction>(AuthViewState()) {

    override fun ActionResult.renderViewState(): AuthViewState =
        when (this) {
            is RegisterUseCase.UseCaseResult -> mapResult()
            is LoginUseCase.UseCaseResult -> mapResult()
            else -> currentViewState()
        }

    override fun handleAction(action: AuthAction): LiveData<ActionResult> =
        liveData(viewModelScope.coroutineContext) {
            when (action) {
                is AuthAction.Login -> {
                    val user = action.user
                    delay(250)
                    coroutineScope {
                        launch {
                            emit(authUsecaseRepository.login(user))
                        }
                    }
                }
                is AuthAction.Register -> {
                    val user = action.user
                    delay(250)
                    coroutineScope {
                        launch {
                            emit(authUsecaseRepository.register(user))
                        }
                    }
                }
            }
        }

    private fun RegisterUseCase.UseCaseResult.mapResult(): AuthViewState = when (this) {
        is RegisterUseCase.UseCaseResult.Success -> currentViewState().copy(
            isLoading = false,
            isSuccess = true,
            message = message
        )
        is RegisterUseCase.UseCaseResult.Failed -> currentViewState().copy(
            isLoading = false,
            isSuccess = false,
            message = message
        )
    }

    private fun LoginUseCase.UseCaseResult.mapResult(): AuthViewState = when (this) {
        is LoginUseCase.UseCaseResult.Success -> currentViewState().copy(
            isLoading = false,
            user = user,
            isSuccess = true,
            message = message
        )
        is LoginUseCase.UseCaseResult.Failed -> currentViewState().copy(
            isLoading = false,
            isSuccess = false,
            message = message
        )
    }
}