package com.sasuke.soundclown.data.network

import com.sasuke.soundclown.data.model.CustomError
import com.sasuke.soundclown.data.model.Track
import com.sasuke.soundclown.util.ApiCallback


class SpotifyRepository(private val spotifyService: SpotifyService) {

    fun getTracks(onGetTracksListener: OnGetTracksListener) {
        spotifyService.getTracks().enqueue(object : ApiCallback<Track>() {
            override fun success(response: Track) {
                onGetTracksListener.onGetTracksSuccess(response)
            }

            override fun failure(error: CustomError) {
                onGetTracksListener.onGetTracksFailure(error)
            }

        })
    }

    interface OnGetTracksListener {
        fun onGetTracksSuccess(track: Track)
        fun onGetTracksFailure(error: CustomError)
    }
}