package com.xalion.mplayer.presenter

import com.xalion.mplayer.ui.base.BaseView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BasePresenter<V : BaseView> : IBasePresenter<V>, CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        job.cancel()
        mView = null
    }
}