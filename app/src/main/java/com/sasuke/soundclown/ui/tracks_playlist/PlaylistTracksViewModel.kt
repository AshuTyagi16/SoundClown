package com.sasuke.soundclown.ui.tracks_playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sasuke.soundclown.data.model.network_models.CustomError
import com.sasuke.soundclown.data.model.network_models.Resource
import com.sasuke.soundclown.data.model.playlist_tracks.PlaylistTracksResponse
import com.sasuke.soundclown.data.network.SpotifyRepository
import javax.inject.Inject

class PlaylistTracksViewModel @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) : ViewModel(), SpotifyRepository.OnGetPlaylistTracksListener {

    private val _playlistTracksLiveData = MutableLiveData<Resource<PlaylistTracksResponse>>()
    val playlistTracksLiveData: LiveData<Resource<PlaylistTracksResponse>>
        get() = _playlistTracksLiveData

    fun getPlaylistTracks(playlistId: String) {
        _playlistTracksLiveData.postValue(Resource.loading())
        spotifyRepository.getPlaylistTracksById(playlistId, this)
    }

    override fun onGetPlaylistTracksSuccess(playlistTracksResponse: PlaylistTracksResponse) {
        _playlistTracksLiveData.postValue(Resource.success(playlistTracksResponse))
    }

    override fun onGetPlaylistTracksFailure(error: CustomError) {
        _playlistTracksLiveData.postValue(Resource.error(error))
    }
}