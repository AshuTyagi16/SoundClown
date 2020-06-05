package com.sasuke.soundclown.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sasuke.soundclown.data.model.BrowseCategory
import com.sasuke.soundclown.data.model.Category
import com.sasuke.soundclown.data.model.CustomError
import com.sasuke.soundclown.data.model.Resource
import com.sasuke.soundclown.data.network.SpotifyRepository
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) : ViewModel(),
    SpotifyRepository.OnGetBrowseCategoriesListener {


    private val _categoryLiveData = MutableLiveData<Resource<BrowseCategory>>()
    val categoryLiveData: LiveData<Resource<BrowseCategory>>
        get() = _categoryLiveData

    fun getCategories() {
        _categoryLiveData.postValue(Resource.loading())
        spotifyRepository.getBrowseCategories(this)
    }

    override fun onGetBrowseCategoriesSuccess(browseCategory: BrowseCategory) {
        _categoryLiveData.postValue(Resource.success(browseCategory))
    }

    override fun onGetBrowseCategoriesFailure(error: CustomError) {
        _categoryLiveData.postValue(Resource.error(error))
    }
}