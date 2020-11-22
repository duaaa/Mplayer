package com.xalion.mplayer.model

import android.net.Uri
import java.io.Serializable

data class Audio(
    val id: Long = 0,
    var audioId: Long = 0,
    var name: String = "",
    var uri: String = "",
    var albumId: Long = 0,
    var artistId: Long = 0,
    var albumUri: String = "",
) : Serializable