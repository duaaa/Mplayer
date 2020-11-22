package com.xalion.mplayer.model

import android.net.Uri
import java.io.Serializable


data class Video(
    val id: Long = 0,
    val videoId: Long = 0,
    var name: String = "",
    var uri: String = ""
) : Serializable