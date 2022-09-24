package id.my.mufidz.antreeorder.intention

import id.my.mufidz.antreeorder.base.ViewAction
import id.my.mufidz.antreeorder.base.ViewState
import id.my.mufidz.antreeorder.models.User

data class AuthViewState(
    val message: String = "",
    val isSuccess: Boolean = false,
    val user: User = User(),
    val isLoading : Boolean = true
) : ViewState

sealed class AuthAction : ViewAction {
    data class Register(val user: User) : AuthAction()
    data class Login(val user: User) : AuthAction()
}