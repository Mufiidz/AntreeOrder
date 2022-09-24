package id.my.mufidz.antreeorder.customview

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.core.view.isVisible
import id.my.mufidz.antreeorder.databinding.DialogLoadingBinding

class DialogLoading(context: Context) : Dialog(context) {

    fun show(isLoading: Boolean, message: String?) {
        if (isLoading) setupDialog(message) else dismiss()
    }

    private fun setupDialog(message: String?): Dialog {
        val binding = DialogLoadingBinding.inflate(LayoutInflater.from(context))
        with(binding) {
            tvMessageLoading.apply {
                isVisible = !message.isNullOrEmpty()
                text = message
            }
        }

        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        show()
        return this
    }
}