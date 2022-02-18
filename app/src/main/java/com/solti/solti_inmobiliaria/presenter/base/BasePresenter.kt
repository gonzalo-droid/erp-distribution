package com.solti.solti_inmobiliaria.presenter.base

import android.content.Context
import io.reactivex.disposables.Disposable

open class BasePresenter<V : BasePresenter.View> {

    protected var view: V? = null
    protected var disposable: Disposable? = null

    fun attachView(view: V) {
        this.view = view
    }

    open fun detachView() {
        try {
            view!!.hideLoading()
            view = null
            disposable?.dispose()
            disposable = null
        } catch (e: Exception) {
            view = null
            disposable?.dispose()
            disposable = null
        }
        //TODO ADD HIDELOADING???
    }

    interface View {
        fun getContext(): Context

        fun showLoading()

        fun hideLoading()

        fun customWifi()

        fun customTimeOut()

    }
}