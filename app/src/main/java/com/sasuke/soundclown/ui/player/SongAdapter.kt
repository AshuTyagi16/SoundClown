package com.sasuke.soundclown.ui.player

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.ItemPlaylist

class SongAdapter(private val glide: RequestManager) : RecyclerView.Adapter<SongViewHolder>(),
    SongViewHolder.OnItemClickListener {

    private lateinit var albums: ArrayList<ItemPlaylist>
    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_song, parent, false)
        val holder = SongViewHolder(view, glide)
        holder.setOnItemClickListener(this)
        return holder
    }

    override fun getItemCount(): Int {
        return if (::albums.isInitialized) albums.size else 0
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        if (::albums.isInitialized) {
            holder.setSong(albums[position])
        }
    }

    fun setSongs(list: ArrayList<ItemPlaylist>) {
        this.albums = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, itemPlaylist: ItemPlaylist)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onItemClick(position: Int, itemPlaylist: ItemPlaylist) {
        if (::onItemClickListener.isInitialized)
            onItemClickListener.onItemClick(position, itemPlaylist)
    }

}