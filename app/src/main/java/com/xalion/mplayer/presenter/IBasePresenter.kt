package com.xalion.mplayer.presenter

import com.xalion.mplayer.ui.base.BaseView

interface IBasePresenter<in V : BaseView> {
    fun attachView(view: V)

    fun detachView()
}