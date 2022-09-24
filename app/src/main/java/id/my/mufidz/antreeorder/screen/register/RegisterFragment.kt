package id.my.mufidz.antreeorder.screen.register

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.my.mufidz.antreeorder.R
import id.my.mufidz.antreeorder.base.BaseFragment
import id.my.mufidz.antreeorder.databinding.FragmentRegisterBinding
import id.my.mufidz.antreeorder.models.User
import id.my.mufidz.antreeorder.intention.AuthAction
import id.my.mufidz.antreeorder.utils.setErrorNot
import id.my.mufidz.antreeorder.utils.viewBinding
import id.my.mufidz.antreeorder.viewmodel.AuthViewModel
import timber.log.Timber

@AndroidEntryPoint
class RegisterFragment :
    BaseFragment<FragmentRegisterBinding, AuthViewModel>(R.layout.fragment_register) {

    private var password = ""

    override val viewModel: AuthViewModel by viewModels()

    override val binding: FragmentRegisterBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            edtPassword.doAfterTextChanged {
                password = it?.trim().toString()
                val isError = password.isEmpty() || password.length < 8
                textfieldPassword.setErrorNot(isError, "Password min length 8")
            }
            edtConfirmPassword.doAfterTextChanged {
                val confirmPassword = it?.trim().toString()
                textfieldConfirmPassword.setErrorNot(
                    confirmPassword != password,
                    "Password doesn't same"
                )
            }
            btnRegister.setOnClickListener {
                onRegisterClick()
            }
            btnTxtLogin.setOnClickListener { intent(R.id.loginFragment, isFromLeft = false) }
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

    private fun onRegisterClick() {
        with(binding) {
            val name = edtName.text?.trim().toString()
            val username = edtUsername.text?.trim().toString()
            val password = edtPassword.text?.trim().toString()
            val confirmPassword = edtConfirmPassword.text?.trim().toString()

            textfieldName.setErrorNot(name.isEmpty())
            textfieldUsername.setErrorNot(username.isEmpty())
            textfieldPassword.setErrorNot(password.isEmpty())

            val isProcess = name.isNotEmpty() &&
                    username.isNotEmpty() &&
                    password.isNotEmpty() &&
                    confirmPassword == password

            if (isProcess) {
                loading()
                User(name = name, username = username, password = password).also {
                    viewModel.execute(AuthAction.Register(it))
                }
            }
        }
    }
}