package com.xalion.mplayer.contract

import com.xalion.mplayer.model.Audio
import com.xalion.mplayer.presenter.BasePresenter
import com.xalion.mplayer.presenter.IBasePresenter
import com.xalion.mplayer.ui.base.BaseView
import kotlinx.coroutines.CoroutineScope

object AudioContract {

    interface View : BaseView {
        fun onAudiosLoaded(lstAudio : ArrayList<Audio>)
        fun onAudioIdsLoaded(lstIds : ArrayList<Long>?)
        fun showRepository()
        fun showReloadButton()
    }

    interface Presenter : IBasePresenter<View> {
        fun loadAudios(pageNo: Int)
    }
}