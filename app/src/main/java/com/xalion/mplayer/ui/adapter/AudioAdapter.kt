package com.xalion.mplayer.ui.adapter

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xalion.mplayer.R
import com.xalion.mplayer.model.Audio


class AudioAdapter(context: Context, audioList: ArrayList<Audio>) :
    RecyclerView.Adapter<AudioAdapter.viewHolder>() {

    var context = context
    var audioList = audioList


    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var ImageV: ImageView

        init {
            title = itemView.findViewById<View>(R.id.flower_text) as TextView
            ImageV = itemView.findViewById<View>(R.id.flower_image) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false)
        return AudioAdapter.viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.title.setText(audioList.get(position).name)
        val url: String = audioList.get(position).albumUri
        try {
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(Uri.parse(url).path)
            val cover = retriever.embeddedPicture
            if (cover != null) {
                // use glide to load the album art
                Glide
                    .with(context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.ImageV)
            }

        }
        catch (ex: Exception)
        {

        }


        //holder.ImageV.setImageURI(audioList.get(position).albumid)
    }

    override fun getItemCount(): Int {
        return audioList.size
    }
}