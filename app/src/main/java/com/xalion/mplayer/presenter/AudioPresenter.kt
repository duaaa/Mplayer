package com.xalion.mplayer.presenter

import com.xalion.mplayer.contract.AudioContract
import com.xalion.mplayer.data.media.MediaStorageHelper
import kotlinx.coroutines.*

class AudioPresenter : BasePresenter<AudioContract.View>(), AudioContract.Presenter {
    var mediaHelper: MediaStorageHelper? = null

    override fun attachView(view: AudioContract.View) {
        super.attachView(view)
        mediaHelper = mView?.getContext()?.let {
            MediaStorageHelper(it, 2)
        }
    }


    override fun loadAudios(pageNo: Int) {
        launch {
            val songs = mediaHelper?.getAllAudios(pageNo)
            withContext(Dispatchers.Main) {
                if (songs != null) {
                    mView?.onAudiosLoaded(songs)
                }
            }
        }
    }
}