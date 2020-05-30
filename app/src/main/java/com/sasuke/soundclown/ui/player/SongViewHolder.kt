package com.sasuke.soundclown.ui.player

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.Item
import kotlinx.android.synthetic.main.cell_song.view.*

class SongViewHolder(itemView: View, private val glide: RequestManager) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var onItemClickListener: OnItemClickListener

    fun setSong(item: Item) {
        glide.load(item.images[0].url).into(itemView.ivSongCover)
        itemView.tvSongName.text = item.name
        itemView.tvArtistName.text = item.owner.display_name

        itemView.setOnClickListener {
            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    it.context,
                    R.color.white_translucent
                )
            )
            if (::onItemClickListener.isInitialized)
                onItemClickListener.onItemClick(adapterPosition, item)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: Item)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}