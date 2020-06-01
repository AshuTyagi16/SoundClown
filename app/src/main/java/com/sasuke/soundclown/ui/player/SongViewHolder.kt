package com.sasuke.soundclown.ui.player

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.ItemPlaylist
import kotlinx.android.synthetic.main.cell_song.view.*

class SongViewHolder(itemView: View, private val glide: RequestManager) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var onItemClickListener: OnItemClickListener

    fun setSong(itemPlaylist: ItemPlaylist) {
        glide.load(itemPlaylist.imageList[0].url).into(itemView.ivSongCover)
        itemView.tvSongName.text = itemPlaylist.name
        itemView.tvArtistName.text = itemPlaylist.owner.displayName

        itemView.setOnClickListener {
            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    it.context,
                    R.color.white_translucent
                )
            )
            if (::onItemClickListener.isInitialized)
                onItemClickListener.onItemClick(adapterPosition, itemPlaylist)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, itemPlaylist: ItemPlaylist)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}