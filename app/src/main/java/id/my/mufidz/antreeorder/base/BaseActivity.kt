package id.my.mufidz.antreeorder.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import id.my.mufidz.antreeorder.utils.viewBinding

abstract class BaseActivity<T : ViewBinding>(bindingInflater: (LayoutInflater) -> T) :
    AppCompatActivity() {
    protected val binding: T by viewBinding(bindingInflater)
    protected lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        context = binding.root.context
    }
}