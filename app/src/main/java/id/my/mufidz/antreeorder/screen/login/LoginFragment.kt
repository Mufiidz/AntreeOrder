package id.my.mufidz.antreeorder.screen.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.my.mufidz.antreeorder.R
import id.my.mufidz.antreeorder.base.BaseFragment
import id.my.mufidz.antreeorder.databinding.FragmentLoginBinding
import id.my.mufidz.antreeorder.intention.AuthAction
import id.my.mufidz.antreeorder.models.User
import id.my.mufidz.antreeorder.utils.setErrorNot
import id.my.mufidz.antreeorder.utils.viewBinding
import id.my.mufidz.antreeorder.viewmodel.AuthViewModel
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, AuthViewModel>(R.layout.fragment_login) {

    override val viewModel: AuthViewModel by viewModels()

    override val binding: FragmentLoginBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnLogin.setOnClickListener {
                onLoginClick()
            }
            btnTxtRegister.setOnClickListener { intent(R.id.registerFragment) }
        }

        with(viewModel) {
            viewState.observe {
                Timber.d(it.toString())
                loading(it.isLoading)
                it.message.takeIf { msg -> msg.isNotEmpty() }
                    ?.apply { snackbar(this, !it.isSuccess) }
            }
        }
    }

    private fun onLoginClick() {
        with(binding) {
            val username = edtUsername.text?.trim().toString()
            val password = edtPassword.text?.trim().toString()

            textfieldUsername.setErrorNot(username.isEmpty())
            textfieldPassword.setErrorNot(password.isEmpty())

            val isProcess = username.isNotEmpty() && password.isNotEmpty()

            if (isProcess) {
                loading()
                User(username = username, password = password).also {
                    viewModel.execute(AuthAction.Login(it))
                    Timber.d(it.toString())
                }
            }
        }
    }
}