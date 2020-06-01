package com.sasuke.soundclown.data.network

import com.sasuke.soundclown.data.model.*
import com.sasuke.soundclown.util.ApiCallback


class SpotifyRepository(private val spotifyService: SpotifyService) {

    fun getBrowseCategories(
        onGetBrowseCategoriesListener: OnGetBrowseCategoriesListener
    ) {
        spotifyService.getBrowseCategories()
            .enqueue(object : ApiCallback<BrowseCategory>() {
                override fun success(response: BrowseCategory) {
                    onGetBrowseCategoriesListener.onGetBrowseCategoriesSuccess(response)
                }

                override fun failure(error: CustomError) {
                    onGetBrowseCategoriesListener.onGetBrowseCategoriesFailure(error)
                }

            })
    }
    fun getPlaylistsForCategory(
        categoryId: String,
        onGetPlaylistsForCategoryListener: OnGetPlaylistsForCategoryListener
    ) {
        spotifyService.getPlaylistsForCategory(categoryId)
            .enqueue(object : ApiCallback<PlaylistResponse>() {
                override fun success(response: PlaylistResponse) {
                    onGetPlaylistsForCategoryListener.onGetPlaylistsForCategorySuccess(response)
                }

                override fun failure(error: CustomError) {
                    onGetPlaylistsForCategoryListener.onGetPlaylistsForCategoryFailure(error)
                }

            })
    }
    fun getCategoryById(
        categoryId: String,
        onGetCategoryByIdListener: OnGetCategoryByIdListener
    ) {
        spotifyService.getCategoryById(categoryId)
            .enqueue(object : ApiCallback<ItemCategories>() {
                override fun success(response: ItemCategories) {
                    onGetCategoryByIdListener.onGetCategoryByIdSuccess(response)
                }

                override fun failure(error: CustomError) {
                    onGetCategoryByIdListener.onGetCategoryByIdFailure(error)
                }

            })
    }

    fun getFeaturedPlaylist(
        onGetFeaturedPlaylistListener: OnGetFeaturedPlaylistListener
    ) {
        spotifyService.getFeaturedPlaylist()
            .enqueue(object : ApiCallback<FeaturedPlaylist>() {
                override fun success(response: FeaturedPlaylist) {
                    onGetFeaturedPlaylistListener.onGetFeaturedPlaylistSuccess(response)
                }

                override fun failure(error: CustomError) {
                    onGetFeaturedPlaylistListener.onGetFeaturedPlaylistFailure(error)
                }

            })
    }

    fun getNewReleases(
        onGetNewReleasesListener: OnGetNewReleasesListener
    ) {
        spotifyService.getNewReleases()
            .enqueue(object : ApiCallback<NewReleases>() {
                override fun success(response: NewReleases) {
                    onGetNewReleasesListener.onGetNewReleasesSuccess(response)
                }

                override fun failure(error: CustomError) {
                    onGetNewReleasesListener.onGetNewReleasesFailure(error)
                }

            })
    }


//    fun getTracks(onGetTracksListener: OnGetTracksListener) {
//        spotifyService.getTracks().enqueue(object : ApiCallback<Track>() {
//            override fun success(response: Track) {
//                onGetTracksListener.onGetTracksSuccess(response)
//            }
//
//            override fun failure(error: CustomError) {
//                onGetTracksListener.onGetTracksFailure(error)
//            }
//
//        })
//    }

    //--------INTERFACES-------

    interface OnGetBrowseCategoriesListener {
        fun onGetBrowseCategoriesSuccess(browseCategory: BrowseCategory)
        fun onGetBrowseCategoriesFailure(error: CustomError)
    }

    interface OnGetPlaylistsForCategoryListener {
        fun onGetPlaylistsForCategorySuccess(playlistResponse: PlaylistResponse)
        fun onGetPlaylistsForCategoryFailure(error: CustomError)
    }

    interface OnGetCategoryByIdListener {
        fun onGetCategoryByIdSuccess(itemCategories: ItemCategories)
        fun onGetCategoryByIdFailure(error: CustomError)
    }

    interface OnGetFeaturedPlaylistListener {
        fun onGetFeaturedPlaylistSuccess(featuredPlaylist: FeaturedPlaylist)
        fun onGetFeaturedPlaylistFailure(error: CustomError)
    }

    interface OnGetNewReleasesListener {
        fun onGetNewReleasesSuccess(newReleases: NewReleases)
        fun onGetNewReleasesFailure(error: CustomError)
    }

    //    interface OnGetTracksListener {
//        fun onGetTracksSuccess(track: Track)
//        fun onGetTracksFailure(error: CustomError)
//    }
}
