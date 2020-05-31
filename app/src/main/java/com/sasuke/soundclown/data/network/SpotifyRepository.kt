package com.sasuke.soundclown.data.network

import com.sasuke.soundclown.data.model.Category
import com.sasuke.soundclown.data.model.CustomError
import com.sasuke.soundclown.data.model.Playlist
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

    fun getPlaylistsForCategory(onGetPlaylistsForCategoryListener: OnGetPlaylistsForCategoryListener) {
        spotifyService.getPlaylistsForCategory().enqueue(object : ApiCallback<Playlist>() {
            override fun success(response: Playlist) {
                onGetPlaylistsForCategoryListener.onGetPlaylistsForCategorySuccess(response)
            }

            override fun failure(error: CustomError) {
                onGetPlaylistsForCategoryListener.onGetPlaylistsForCategoryFailure(error)
            }

        })
    }

    fun getAllCategories(onGetAllCategoriesListener: OnGetAllCategoriesListener) {
        spotifyService.getAllCategories().enqueue(object : ApiCallback<Category>() {
            override fun success(response: Category) {
                onGetAllCategoriesListener.onGetAllCategoriesSuccess(response)
            }

            override fun failure(error: CustomError) {
                onGetAllCategoriesListener.onGetAllCategoriesFailure(error)
            }

        })
    }

    interface OnGetTracksListener {
        fun onGetTracksSuccess(track: Track)
        fun onGetTracksFailure(error: CustomError)
    }

    interface OnGetPlaylistsForCategoryListener {
        fun onGetPlaylistsForCategorySuccess(playlist: Playlist)
        fun onGetPlaylistsForCategoryFailure(error: CustomError)
    }

    interface OnGetAllCategoriesListener {
        fun onGetAllCategoriesSuccess(category: Category)
        fun onGetAllCategoriesFailure(error: CustomError)
    }
}