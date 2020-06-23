package com.sasuke.soundclown.ui.tracks_playlist

import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sasuke.soundclown.R
import com.sasuke.soundclown.data.model.playlist_tracks.ItemPlaylistTrack
import kotlinx.android.synthetic.main.cell_song.view.*

class PlaylistTrackViewHolder(itemView: View, private val glide: RequestManager) :
    RecyclerView.ViewHolder(itemView) {

    private val requestOptions = RequestOptions().override(Target.SIZE_ORIGINAL)

    fun setItem(itemPlaylistTrack: ItemPlaylistTrack) {
        glide.load(itemPlaylistTrack.track.playlistTrackAlbum.imageList[0])
            .apply(requestOptions)
//            .placeholder(R.drawable.ic_music_placeholder)
            .into(itemView.ivSongCover)

        itemView.tvSongName.text = itemPlaylistTrack.track.name

        var singerName = ""
        itemPlaylistTrack.track.artists.forEach {
            singerName = it.name + ","
        }
        itemView.tvArtistName.text = singerName
    }
}