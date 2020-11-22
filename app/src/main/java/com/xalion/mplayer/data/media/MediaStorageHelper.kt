package com.xalion.mplayer.data.media

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.xalion.mplayer.model.Audio
import com.xalion.mplayer.model.Video


class MediaStorageHelper(context: Context, mediaType: Int) : IMediaStorageHelper
{
    var contentResolver : ContentResolver? = null
    var externalContentUri : Uri? = null
    var externalAlbumContentUri : Uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
    var sortOrder : String? = null
    var displayName : String? = null
    var title : String? = null
    var columnId : String? = null
    var artistId : String? = null
    var albumId : String? = null

    init
    {
        contentResolver = context.contentResolver
        if(mediaType == 1){
            externalContentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            displayName = MediaStore.Video.Media.DISPLAY_NAME
            title = MediaStore.Video.Media.TITLE
            columnId = MediaStore.Video.Media._ID
            sortOrder = ""
        }
        if(mediaType == 2){
            externalContentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            displayName = MediaStore.Audio.Media.DISPLAY_NAME
            title = MediaStore.Audio.Media.TITLE
            columnId = MediaStore.Audio.Media._ID
            albumId = MediaStore.Audio.Albums._ID
            artistId = MediaStore.Audio.Media.ARTIST_ID
        }
    }

    override fun getCount(): Int {
        var count = 0
        val cursor: Cursor? = contentResolver?.query(
            externalContentUri!!,
            null,
            null,
            null,
            null
        )
        if(cursor != null){
            count = cursor.count
            cursor.close()
        }

       return count
    }

    override fun getAllIds(pageNo: Int) : ArrayList<Long>?{
        var audios : ArrayList<Long>? = ArrayList<Long>()
        val cursor: Cursor? = externalContentUri?.let {
            contentResolver?.query(
                it,
                arrayOf(columnId),
                null,
                null,
                null
            )
        }
        if(cursor != null){
            while (cursor.moveToNext()) {
                val id: Long = cursor.getLong(cursor.getColumnIndex(columnId))
                audios?.add(id)
            }
          cursor.close()
        }
        else
        {
            audios = null
        }
       return audios

    }

    override fun getAllAudios(pageNo: Int) : ArrayList<Audio>?{
        var audios : ArrayList<Audio>? = ArrayList<Audio>()
        val cursor: Cursor? = externalContentUri?.let {
            contentResolver?.query(
                it,
                arrayOf(columnId,displayName,albumId,artistId),
                null,
                null,
                null
            )
        }
        if(cursor != null){
            while (cursor.moveToNext()) {
                val audio = getAudioInfo(cursor)
                if (audio != null) {
                    audios?.add(audio)
                }
            }
            cursor.close()
        }
        else
        {
            audios = null
        }
        return audios

    }

    override fun getAllVideos(pageNo: Int): ArrayList<Video>? {
        TODO("Not yet implemented")
    }

    override fun getUri(id: Long): Uri {
        return ContentUris.withAppendedId(externalContentUri!!, id)
    }

    override fun getAudio(id: Long): Audio? {
        var uri = ContentUris.withAppendedId(externalContentUri!!, id)
        var audio : Audio? = null
        val cursor: Cursor? = uri?.let {
            contentResolver?.query(
                it,
                arrayOf(columnId,displayName,albumId,artistId),
                null,
                null,
                null
            )
        }
        if(cursor != null){
            audio = getAudioInfo(cursor)
            cursor.close()
        }
        return audio
    }

    override fun getVideo(id: Long): Video {
        TODO("Not yet implemented")
    }

    fun getAudioInfo(cursor: Cursor?): Audio? {
        var audio : Audio? = null
        if(cursor != null){
            val id: Long = cursor.getLong(cursor.getColumnIndex(columnId))
            val displayName: String = cursor.getString(cursor.getColumnIndex(displayName))
            val albumId: Long = cursor.getLong(cursor.getColumnIndex(albumId))
            val artistId: Long = cursor.getLong(cursor.getColumnIndex(artistId))

            val uri: String = ContentUris.withAppendedId(externalContentUri!!, id).toString()
            val albumUri: String = ContentUris.withAppendedId(externalAlbumContentUri!!, albumId).toString()

            audio = Audio(0, id, displayName, uri, albumId, artistId, albumUri)
        }

        return audio
    }


}