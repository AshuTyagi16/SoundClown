package com.sasuke.soundclown.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sasuke.soundclown.data.model.CustomError
import com.sasuke.soundclown.data.model.Resource
import com.sasuke.soundclown.data.model.Track
import com.sasuke.soundclown.data.network.SpotifyRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) :
    ViewModel(), SpotifyRepository.OnGetTracksListener {


    private val _trackLiveData = MutableLiveData<Resource<Track>>()
    val trackLiveData: LiveData<Resource<Track>>
        get() = _trackLiveData

    fun getDemo() {
        _trackLiveData.postValue(Resource.loading())
        spotifyRepository.getTracks(this)
    }

    override fun onGetTracksSuccess(track: Track) {
        _trackLiveData.postValue(Resource.success(track))
    }

    override fun onGetTracksFailure(error: CustomError) {
        _trackLiveData.postValue(Resource.error(error))
    }
}