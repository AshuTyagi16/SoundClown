package com.sasuke.soundclown.ui.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sasuke.soundclown.data.model.CustomError
import com.sasuke.soundclown.data.model.PlaylistResponse
import com.sasuke.soundclown.data.model.Resource
import com.sasuke.soundclown.data.network.SpotifyRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) :
    ViewModel(),
    SpotifyRepository.OnGetPlaylistsForCategoryListener {

    private val _playlistLiveData = MutableLiveData<Resource<PlaylistResponse>>()
    val playlistLiveData: LiveData<Resource<PlaylistResponse>>
        get() = _playlistLiveData

    fun getPlaylistsForCategory(categoryId: String) {
        _playlistLiveData.postValue(Resource.loading())
        spotifyRepository.getPlaylistsForCategory(categoryId, this)
    }

    override fun onGetPlaylistsForCategorySuccess(playlistResponse: PlaylistResponse) {

        _playlistLiveData.postValue(Resource.success(playlistResponse))
    }

    override fun onGetPlaylistsForCategoryFailure(error: CustomError) {
        _playlistLiveData.postValue(Resource.error(error))
    }
}