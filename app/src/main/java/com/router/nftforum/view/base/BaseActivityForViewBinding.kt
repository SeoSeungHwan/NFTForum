package com.router.nftforum.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivityForViewBinding<T: ViewDataBinding>: AppCompatActivity() {
    lateinit var viewDataBinding: T
    abstract val layoutId: Int

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)

        viewDataBinding.lifecycleOwner = this

        init()
    }
}