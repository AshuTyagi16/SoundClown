package com.sasuke.soundclown.di.modules.fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.sasuke.soundclown.di.mapkey.ViewModelKey
import com.sasuke.soundclown.di.scopes.PerFragmentScope
import com.sasuke.soundclown.ui.base.ItemDecorator
import com.sasuke.soundclown.ui.home.CategoryAdapter
import com.sasuke.soundclown.ui.home.HomeFragmentViewModel
import com.sasuke.soundclown.util.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class HomeFragmentModule {

    companion object {
        @Provides
        @PerFragmentScope
        fun adapter(glide: RequestManager): CategoryAdapter {
            return CategoryAdapter(glide)
        }

        @Provides
        @PerFragmentScope
        fun layoutManager(context: Context): GridLayoutManager {
            return GridLayoutManager(context, Constants.CATEGORY_SPAN_COUNT)
        }

        @Provides
        @PerFragmentScope
        fun itemDecorator(): ItemDecorator {
            return ItemDecorator(20, 20)
        }
    }

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    abstract fun bindHomeFragmentViewModel(homeFragmentViewModel:HomeFragmentViewModel): ViewModel
}