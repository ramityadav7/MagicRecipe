package com.simpragma.magicrecipe.core

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class CoreFragment : Fragment() {
    val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}