package com.xalion.mplayer.data.media

import android.net.Uri
import com.xalion.mplayer.model.Audio
import com.xalion.mplayer.model.Video

interface IMediaStorageHelper
{
    fun getCount(): Int

    fun getAllAudios(pageNo: Int) : ArrayList<Audio>?

    fun getAllVideos(pageNo: Int) : ArrayList<Video>?

    fun getAllIds(pageNo: Int) : ArrayList<Long>?

    fun getUri(id : Long): Uri

    fun getAudio(id : Long): Audio?

    fun getVideo(id : Long): Video

}