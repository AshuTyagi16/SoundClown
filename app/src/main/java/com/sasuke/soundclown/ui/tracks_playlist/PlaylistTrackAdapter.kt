package com.sasuke.soundclown.ui.tracks_playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.playlist_tracks.ItemPlaylistTrack

class PlaylistTrackAdapter(private val glide: RequestManager) :
    RecyclerView.Adapter<PlaylistTrackViewHolder>() {

    private lateinit var list:ArrayList<ItemPlaylistTrack>

    fun initPlaylistTrackList(itemPlaylistTrack: ArrayList<ItemPlaylistTrack>) {
        list = itemPlaylistTrack
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistTrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cell_song,parent,false)
        return PlaylistTrackViewHolder(view,glide)
    }

    override fun getItemCount(): Int {
        if (::list.isInitialized)
            return list.size
        return 0
    }

    override fun onBindViewHolder(holder: PlaylistTrackViewHolder, position: Int) {
        if (::list.isInitialized){
            holder.setItem(list[position])
        }
    }
}