package com.sasuke.soundclown.ui.category_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sasuke.soundclown.data.model.CustomError
import com.sasuke.soundclown.data.model.PlaylistResponse
import com.sasuke.soundclown.data.model.Resource
import com.sasuke.soundclown.data.network.SpotifyRepository
import javax.inject.Inject

class CategoryDetailsViewModel @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) : ViewModel(), SpotifyRepository.OnGetPlaylistsForCategoryListener {

    private val _playlistLiveData = MutableLiveData<Resource<PlaylistResponse>>()
    val playlistLiveData: LiveData<Resource<PlaylistResponse>>
        get() = _playlistLiveData

    fun getCategoryPlaylist(categoryId: String) {
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