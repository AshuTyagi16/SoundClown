package com.sasuke.soundclown.di.modules.fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.sasuke.soundclown.di.mapkey.ViewModelKey
import com.sasuke.soundclown.di.scopes.PerFragmentScope
import com.sasuke.soundclown.ui.base.ItemDecorator
import com.sasuke.soundclown.ui.tracks_playlist.PlaylistTrackAdapter
import com.sasuke.soundclown.ui.tracks_playlist.PlaylistTracksViewModel
import com.sasuke.soundclown.util.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class PlaylistTracksFragmentModule {
    companion object {
        @Provides
        @PerFragmentScope
        fun adapter(glide: RequestManager): PlaylistTrackAdapter {
            return PlaylistTrackAdapter(glide)
        }

        @Provides
        @PerFragmentScope
        fun itemDecorator(): ItemDecorator {
            return ItemDecorator( 0,10)
        }
    }

    @Binds
    @IntoMap
    @ViewModelKey(PlaylistTracksViewModel::class)
    abstract fun bindPlaylistTracksViewModel(playlistTracksViewModel: PlaylistTracksViewModel): ViewModel
}