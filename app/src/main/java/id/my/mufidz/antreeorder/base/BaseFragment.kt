package id.my.mufidz.antreeorder.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.viewbinding.ViewBinding
import id.my.mufidz.antreeorder.customview.DialogLoading
import id.my.mufidz.antreeorder.utils.slideLeftRightAnim
import id.my.mufidz.antreeorder.utils.slideRightLeftAnim
import id.my.mufidz.antreeorder.utils.snackbar

abstract class BaseFragment<T : ViewBinding, VM : ViewModel>(layoutId: Int) : Fragment(layoutId) {

    protected abstract val viewModel: VM

    protected abstract val binding: T

    private lateinit var dialogLoading: DialogLoading

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogLoading = DialogLoading(binding.root.context)
    }

    protected fun <T : ViewState> LiveData<T>.observe(callbackValue: (T) -> Unit) =
        observe(viewLifecycleOwner, callbackValue)

    protected fun snackbar(
        message: String,
        isError: Boolean = false,
        txtAction: String? = null,
        action: View.OnClickListener? = null
    ) {
        binding.root.snackbar(message, isError, txtAction, action)
    }

    protected fun intent(id: Int, args: Bundle? = null, isFromLeft: Boolean = true) {
        findNavController().navigate(
            id,
            args,
            navOptions { if (isFromLeft) slideLeftRightAnim() else slideRightLeftAnim() })
    }

    protected fun loading(isLoading: Boolean = true, message: String? = null) {
        dialogLoading.show(isLoading, message)
    }

}