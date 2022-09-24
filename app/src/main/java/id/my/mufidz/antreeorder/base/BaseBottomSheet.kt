package id.my.mufidz.antreeorder.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.my.mufidz.antreeorder.R

abstract class BaseBottomSheet<T: ViewBinding> : BottomSheetDialogFragment() {

    protected abstract val binding: T

    override fun getTheme(): Int =
        R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root
}