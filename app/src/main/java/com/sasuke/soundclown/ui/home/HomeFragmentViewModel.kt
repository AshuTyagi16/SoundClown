package com.sasuke.soundclown.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sasuke.soundclown.data.model.Category
import com.sasuke.soundclown.data.model.CustomError
import com.sasuke.soundclown.data.model.Resource
import com.sasuke.soundclown.data.network.SpotifyRepository
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(
    private val spotifyRepository: SpotifyRepository
) : ViewModel(), SpotifyRepository.OnGetAllCategoriesListener {


    private val _categoryLiveData = MutableLiveData<Resource<Category>>()
    val categoryLiveData: LiveData<Resource<Category>>
        get() = _categoryLiveData

    fun getCategories() {
        _categoryLiveData.postValue(Resource.loading())
        spotifyRepository.getAllCategories(this)
    }

    override fun onGetAllCategoriesSuccess(category: Category) {
        _categoryLiveData.postValue(Resource.success(category))
    }

    override fun onGetAllCategoriesFailure(error: CustomError) {
        _categoryLiveData.postValue(Resource.error(error))
    }
}