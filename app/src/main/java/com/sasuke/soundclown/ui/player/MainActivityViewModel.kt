package com.sasuke.soundclown.ui.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sasuke.soundclown.data.model.CustomError
import com.sasuke.soundclown.data.model.Playlist
import com.sasuke.soundclown.data.model.Resource
import com.sasuke.soundclown.data.model.Track
import com.sasuke.soundclown.data.network.SpotifyRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) :
    ViewModel(), SpotifyRepository.OnGetTracksListener,
    SpotifyRepository.OnGetPlaylistsForCategoryListener {


    private val _trackLiveData = MutableLiveData<Resource<Track>>()
    val trackLiveData: LiveData<Resource<Track>>
        get() = _trackLiveData

    private val _playlistLiveData = MutableLiveData<Resource<Playlist>>()
    val playlistLiveData: LiveData<Resource<Playlist>>
        get() = _playlistLiveData

    fun getDemo() {
        _trackLiveData.postValue(Resource.loading())
        spotifyRepository.getTracks(this)
    }

    fun getPlaylistsForCategory() {
        _playlistLiveData.postValue(Resource.loading())
        spotifyRepository.getPlaylistsForCategory(this)
    }

    override fun onGetTracksSuccess(track: Track) {
        _trackLiveData.postValue(Resource.success(track))
    }

    override fun onGetTracksFailure(error: CustomError) {
        _trackLiveData.postValue(Resource.error(error))
    }

    override fun onGetPlaylistsForCategorySuccess(playlist: Playlist) {

        _playlistLiveData.postValue(Resource.success(playlist))
    }

    override fun onGetPlaylistsForCategoryFailure(error: CustomError) {
        _playlistLiveData.postValue(Resource.error(error))
    }
}